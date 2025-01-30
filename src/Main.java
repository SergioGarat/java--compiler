import java_cup.runtime.ComplexSymbolFactory;
import jflex.exceptions.SilentExit;
import lexico.Lexico;
import sintactico.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
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

    public static void main(String[] args) {
        generateJavaFiles();
        //Aquí se debe cambiar el archivo que se quiere escoger para compilar


        String[] files = {"example2.txt"};
        if (args.length != 0) {
            files = new String[]{args[0]};
        }

        cleanOutputFiles("", true);
        for(String file : files){
            executeCompiler(file);
        }

    }



    private static void generateJavaFiles() {
        try {
            generateFlexFile();
            System.out.println("Léxico generado");
        } catch (Exception e) {
            System.out.println("ERROR GENERANDO EL ARCHIVO: " + e.getMessage());
        }
        try {
            generateCupFile();
            System.out.println("Parser generado");
        } catch (Exception e) {
            e.printStackTrace();
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

    private static void executeCompiler(String file) {
        try {
            String fname = file;
            int pos = fname.lastIndexOf(".");
            if (pos > 0) {
                fname = fname.substring(0, pos);
            }

            cleanOutputFiles(fname, false);
            Reader reader = new BufferedReader(new FileReader(WORK_DIR + "examples\\" + file ));
            ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
            Lexico scanner = new Lexico(reader, symbolFactory, fname);
            Parser parser = new Parser(scanner, symbolFactory, fname);
            parser.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void cleanOutputFiles(String startname, boolean full) {
        System.out.println("Borramos el contenido de la carpeta output.");
        File output_dir = new File(OUTPUT_DIR);
        if (output_dir.isDirectory()) {
            for (File file : output_dir.listFiles()) {
                if(full || file.getName().startsWith(startname)) {

                    if(file.delete()){
                        System.out.println("Borrado archivo: " + file.getName());
                    }else{
                        System.out.println("El archivo: " + file.getName()+ " no pudo ser borrado");
                    }
                }
            }
        }

    }
}
