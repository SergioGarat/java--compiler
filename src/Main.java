import errores.CompilerError;
import errores.SymTabError;
import java_cup.runtime.ComplexSymbolFactory;
import jflex.exceptions.SilentExit;
import lexico.Lexico;
import sintactico.Parser;
import symbolsTable.SymbolsTable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String WORK_DIR = USER_DIR + "\\src\\";

    private static final String LEX_DIR = WORK_DIR + "lexico\\";
    private static final String JFLEX_FILE = LEX_DIR + "lexico.flex";

    private static final String CUP_DIR = WORK_DIR + "sintactico\\";
    private static final String CUP_FILE = CUP_DIR + "sintactico.cup";

    private static final String OUTPUT_DIR = WORK_DIR + "output\\";

    //todo: Aquí se debe cambiar el archivo que se quiere escoger para compilar
    public static String fileToCompile = "example1.txt";

    public static void main(String[] args) throws IOException {
        generateJavaFiles();

        if (args.length != 0) {
            fileToCompile = args[0];
        }

        String fname = fileToCompile;
        int pos = fname.lastIndexOf(".");
        if (pos > 0) {
            fname = fname.substring(0, pos);
        }
        cleanOutputFiles(fname, true);
        executeCompiler(fname);
    }


    private static void generateJavaFiles() {
        try {
            generateFlexFile();
            System.out.println("Léxico generado");
        } catch (Exception e) {
            System.out.println("ERROR GENERANDO LOS ARCHIVOS DEL ANALISIS LÉXICO: " + e.getMessage());
        }
        try {
            generateCupFile();
            System.out.println("Parser generado");
        } catch (Exception e) {
            System.out.println("ERROR GENERANDO LOS ARCHIVOS DEL ANALISIS SINTÁCTICO: " + e.getMessage());
        }
    }

    private static void generateFlexFile() throws SilentExit {
        jflex.Main.generate(new String[]{JFLEX_FILE});
    }

    private static void generateCupFile() throws Exception {
        String[] commands = {/* "-dump_grammar", */ "-locations", "-parser", "Parser", CUP_FILE};
        java_cup.Main.main(commands);
        // generates on WorkDir folder Parser.java and ParserSym.java

        // MOVE FILES
        Path parser_o = Paths.get("Parser.java");
        Path parser_d = Paths.get(CUP_DIR + "Parser.java");

        // move parser
        Files.deleteIfExists(parser_d);
        Files.move(parser_o, parser_d);

        Path sym_o = Paths.get("ParserSym.java");
        Path sym_d = Paths.get(CUP_DIR + "ParserSym.java");

        // move parser symbols
        Files.deleteIfExists(sym_d);
        Files.move(sym_o, sym_d);
    }

    private static void closeFiles() throws IOException {
        Lexico.closeFile(0, 0);
        SymbolsTable.closeFiles();
    }

    private static void closeErrorFiles() {
        SymTabError.closeFile();
        CompilerError.closeFiles();
    }


    private static void executeCompiler(String fname) throws IOException {
        try {
            cleanOutputFiles(fname, false);
            Reader reader = new BufferedReader(new FileReader(WORK_DIR + "examples\\" + fname + ".txt"));
            ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
            Lexico scanner = new Lexico(reader, symbolFactory, fname);
            Parser parser = new Parser(scanner, symbolFactory, fname);
            parser.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            closeFiles();
            closeErrorFiles();
        }
    }

    private static void cleanOutputFiles(String startName, boolean full) {
        System.out.println("Deleting contents of the output folder.");
        File outputDir = new File(OUTPUT_DIR + "\\" + startName);

        if (outputDir.isDirectory()) {
            File[] files = outputDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (full || file.getName().startsWith(startName)) {
                        if (file.delete()) {
                            System.out.println("Deleted file: " + file.getName());
                        } else {
                            System.out.println("Could not delete file: " + file.getName());
                        }
                    }
                }
            }
        }
        outputDir.mkdir();
    }
}
