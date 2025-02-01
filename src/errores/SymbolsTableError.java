package errores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SymbolsTableError extends Exception {
    private static BufferedWriter out;
    private static final String PATH = "src\\output\\symbolTableError.txt";

    public SymbolsTableError(String error) {
        super(error);
        try {
            if (out == null) {
                out = new BufferedWriter(new FileWriter(PATH, true));
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
