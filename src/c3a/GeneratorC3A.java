package c3a;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratorC3A {
    private static final String PATH = "src\\output\\c3_code.txt";
    private ArrayList<InstructionC3A> instructions;

    // private Optimizer optimizer; NOT IMPLEMENTED
    private BufferedWriter writer;

    public GeneratorC3A() {
        this.instructions = new ArrayList<>();
    }

    // Add a new instruction
    public void generateC3aInstr(InstructionC3A.Code opCode, String op1, String op2, String dest) {
        InstructionC3A inst = new InstructionC3A(opCode, op1, op2, dest);
        instructions.add(inst);
    }


    public void savec3aInFile() {
        String result = "-----------------------------------------------\n"
                + "---------------- C3@ code list"
                + " ----------------\n"
                + "-----------------------------------------------\n";
        for (InstructionC3A instruction : instructions) {
            result += instruction + "\n\n";
        }
        try {
            File file = new File(PATH);

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(result);
            writer.close();
        } catch (IOException ex) {
            System.out.println("ERROR WRITING C3@");
            Logger.getLogger(GeneratorC3A.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<InstructionC3A> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<InstructionC3A> instructions) {
        this.instructions = instructions;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
}