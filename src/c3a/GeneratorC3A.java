package c3a;

import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratorC3A {
    private static final String dir = "src\\output\\";
    private String FileName = "c3_code.txt";
    private ArrayList<InstructionC3A> instructionList;

    public GeneratorC3A() {
        this.instructionList = new ArrayList<>();
    }

    public GeneratorC3A(String filename) {
        this.FileName = filename + "\\" + "c3_code.txt";
        this.instructionList = new ArrayList<>();
    }

    // Add a new instruction
    public void generarC3A(InstructionC3A.Code opCode, String op1, String op2, String dest) {
        InstructionC3A inst = new InstructionC3A(opCode, op1, op2, dest);
        instructionList.add(inst);
    }

    public ArrayList<InstructionC3A> getAllC3DirInstr() {
        return instructionList;
    }

    public void guardarC3Dir() {
        StringBuilder contenido = new StringBuilder();
        contenido.append("-----------------------------------------------\n")
                 .append("---------------- C3@ Code ----------------\n")
                 .append("-----------------------------------------------\n");
        
        // Add instructions to the StringBuilder
        for (InstructionC3A instruccion : instructionList) {
            contenido.append(instruccion).append("\n\n");
        }
        
        File archivo = new File(dir + FileName);
        archivo.getParentFile().mkdirs();
    
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            escritor.write(contenido.toString());
        } catch (IOException ex) {
            Logger.getLogger(GeneratorC3A.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}