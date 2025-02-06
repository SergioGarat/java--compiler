package errores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SymTabError extends Exception {
    private static BufferedWriter out;
    private static final String dir = "src\\output\\";
    private static final String filename = "symbolTableError.txt";

    public SymTabError(String error, String filenameDir) {
        super(error);
        var errorOutput = dir + filenameDir + "\\" + filename;
        try {
            if (out == null) {
                out = new BufferedWriter(new FileWriter(errorOutput, true));
            }
            out.write(error);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeFile() {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
