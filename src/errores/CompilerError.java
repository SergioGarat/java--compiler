package errores;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CompilerError extends Exception {
    private static BufferedWriter outLexical = null;
    private static BufferedWriter outSyntax = null;
    private static BufferedWriter outSemantic = null;
    private static String BASE_PATH = "src\\output\\";
    private final String LEXICAL_FILE = "lexicalError.txt";
    private final String SYNTAX_FILE = "syntaxError.txt";
    private final String SEMANTIC_FILE = "semanticError.txt";
    private ErrorType type;


    public enum ErrorType {
        LEXICAL, SYNTAX, SEMANTIC
    }

    public CompilerError(String message, ErrorType type, String filename) {
        super(message);
        this.type = type;
        BufferedWriter out = getBufferedWriter(type);

        if (out == null) {
            try {
                out = switch (type) {
                    case LEXICAL -> {
                        outLexical = new BufferedWriter(new FileWriter(BASE_PATH + filename + "\\" + LEXICAL_FILE));
                        yield outLexical;
                    }
                    case SYNTAX -> {
                        outSyntax = new BufferedWriter(new FileWriter(BASE_PATH + filename + "\\" + SYNTAX_FILE));
                        yield outSyntax;
                    }
                    case SEMANTIC -> {
                        outSemantic = new BufferedWriter(new FileWriter(BASE_PATH + filename + "\\" + SEMANTIC_FILE));
                        yield outSemantic;
                    }
                };
            } catch (IOException e) {
                System.out.println("ERROR CREANDO EL ARCHIVO DE ERROR: " + e.getMessage());
            }
        }
        try {
            out.write(message + "\n");
        } catch (IOException e) {
            System.out.println("ERROR ESCRIBIENDO EN EL ARCHIVO DE ERROR: " + e.getMessage());
        }
    }


    public CompilerError(ComplexSymbol s, ArrayList<String> expectedName, boolean recovered, String filename) {
        this(buildSyntaxErrorMessage(s, expectedName), ErrorType.SYNTAX, filename);
    }

    private static String buildSyntaxErrorMessage(ComplexSymbol s, ArrayList<String> expectedName) {
        String from = s.xleft.getLine() + ":" + s.xleft.getColumn();
        String to = s.xright.getLine() + ":" + s.xright.getColumn();
        String error = "Syntax Error: " + (s.value != null ? s.value : "") + " spanning from " + from + " to " + to;
        if (expectedName != null && !expectedName.isEmpty()) {
            error += "\t\n Token/s Expected: ";
            for (String token : expectedName) {
                error += token + ", ";
            }
            error = error.substring(0, error.length() - 2);
        }
        return error;
    }

    private BufferedWriter getBufferedWriter(ErrorType type) {
        return switch (type) {
            case LEXICAL -> outLexical;
            case SYNTAX -> outSyntax;
            case SEMANTIC -> outSemantic;
        };
    }

    public static void closeFiles() {
        closeFile(outLexical);
        closeFile(outSyntax);
        closeFile(outSemantic);
    }

    private static void closeFile(BufferedWriter out) {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}