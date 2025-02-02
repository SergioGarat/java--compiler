package errores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SymTabError extends Exception {
    private static BufferedWriter out;
    private static final String dir = "src\\output\\symbolTableError.txt";

    public SymTabError(String error) {
        super(error);
        try {
            if (out == null) {
                out = new BufferedWriter(new FileWriter(dir, true));
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
