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
    private final String PATH_LEXICAL = "src\\output\\lexicalError.txt";
    private final String PATH_SYNTAX = "src\\output\\syntaxError.txt";
    private final String PATH_SEMANTIC = "src\\output\\semanticError.txt";
    private ErrorType type;


    public enum ErrorType {
        LEXICAL, SYNTAX, SEMANTIC
    }

    public CompilerError(String message, ErrorType type) {
        super(message);
        this.type = type;
        BufferedWriter out = getBufferedWriter(type);

        if (out == null) {
            try {
                switch (type) {
                    case LEXICAL:
                        outLexical = new BufferedWriter(new FileWriter(PATH_LEXICAL));
                        out = outLexical;
                        break;
                    case SYNTAX:
                        outSyntax = new BufferedWriter(new FileWriter(PATH_SYNTAX));
                        out = outSyntax;
                        break;
                    case SEMANTIC:
                        outSemantic = new BufferedWriter(new FileWriter(PATH_SEMANTIC));
                        out = outSemantic;
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public CompilerError(ComplexSymbol s, ArrayList<String> expectedName, boolean recovered) {
        this(buildSyntaxErrorMessage(s, expectedName), ErrorType.SYNTAX);
    }

    private static String buildSyntaxErrorMessage(ComplexSymbol s, ArrayList<String> expectedName) {
        String from = s.xleft.getLine() + ":" + s.xleft.getColumn();
        String to = s.xright.getLine() + ":" + s.xright.getColumn();
        String error = "Syntax Error: " + (s.value != null ? s.value : "") + " spanning from " + from + " to " + to;
        if (expectedName != null && expectedName.size() > 0) {
            error += "\t\n Token/s Expected: ";
            for (String token : expectedName) {
                error += token + " ";
            }
        }
        return error;
    }

    private BufferedWriter getBufferedWriter(ErrorType type) {
        switch (type) {
            case LEXICAL:
                return outLexical;
            case SYNTAX:
                return outSyntax;
            case SEMANTIC:
                return outSemantic;
            default:
                return null;
        }
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