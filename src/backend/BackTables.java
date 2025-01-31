package backend;


import symbolsTable.SymbolsTable;
import symbolsTable.Type.TipoSubyacente;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackTables {

    private SymbolsTable symbolsTable;

    static final String BASE_PATH = "src\\output\\";
    private String Filename = "Backend_Tables.txt";
    // TV
    private HashMap<String, Var> varTable;
    // TP
    private ArrayList<Proc> procTable;
    // TE
    private ArrayList<Etiq> labelTable;

    private static int tmp_n = 0;

    public BackTables(SymbolsTable symbolsTable) {
        this.symbolsTable = symbolsTable;
        this.varTable = new HashMap<>();
        this.procTable = new ArrayList<>();
        this.labelTable = new ArrayList<>();
    }
    public BackTables(SymbolsTable symbolsTable, String filename) {
        this.symbolsTable = symbolsTable;
        this.varTable = new HashMap<>();
        this.procTable = new ArrayList<>();
        this.labelTable = new ArrayList<>();
        this.Filename = filename +"\\"+ this.Filename;
    }


    // Adding a new VARIABLE into the table
    public String addVar(String varname, int size, TipoSubyacente type, boolean isParam) {
        int scope = symbolsTable.getActualScope();
        int idProcedure = getLastProcedureId();
        String name = varname + "_" + idProcedure + "_" + scope;
        Proc proc = procTable.get(idProcedure);
        int offset = (proc.getMemorySize() + size);
        if (isParam) {
            offset = proc.getOffset() + 16;
            // We update its parent size
            proc.setParamsOffset(offset);
        } else {
            // We update its parent size
            proc.setMemorySize(proc.getMemorySize() + size);
        }
        // We add the variable into the table
        varTable.put(name, new Var(name, idProcedure, offset, size, type, isParam));

        return name;
    }

    // STRING VARIABLE
    public String addStrVar(String varname, int size, String value) {
        int idParent = getLastProcedureId();
        int scope = symbolsTable.getActualScope();
        String name = varname + "_" + idParent + "_" + scope;
        // We add the variable into the table
        varTable.put(name, new Var(name, idParent, size, value));

        return name;
    }

    // TMP STRING VARIABLE
    public String addTempStrVar(int size, String value) {
        String name = "T" + tmp_n;
        tmp_n++;
        int idParent = getLastProcedureId();
        // We add the variable into the table
        varTable.put(name, new Var(name, idParent, size, value));

        return name;
    }

    public String addTempVar(int size, TipoSubyacente type) {
        String name = "T" + tmp_n;
        tmp_n++;
        int idParent = getLastProcedureId();
        // We add the variable into the table
        Proc proc = procTable.get(idParent);
        int offset = (proc.getMemorySize() + size);
        proc.setMemorySize(offset);
        // We add the variable into the table
        varTable.put(name, new Var(name, idParent, offset, size, type, false));
        return name;
    }

    // Adding a new PROCEDURE into the table
    public String addProc(String procName, int params, int size, TipoSubyacente type) {
        String name = "PROC_" + procName;
        // We add the new procedure
        procTable.add(new Proc(name, params, size, type));
        return name;
    }

    public String addMain() {
        String name = "PROC_main";
        this.procTable.add(new Proc(name, 0, 0, TipoSubyacente.TS_NULL));
        return name;
    }

    public String addLabel() {
        String label = "LABEL_" + labelTable.size();

        labelTable.add(new Etiq(label));

        return label;
    }

    public void storeTables() {
        String result = "---------------------------------------------\n"
                + "---------------- TABLES INFO ----------------\n"
                + "---------------------------------------------\n";

        result += "Variables:\n";
        for (String name : varTable.keySet()) {
            result += varTable.get(name) + "\n";
        }

        result += "\nProcedures:\n";
        for (Proc procedure : procTable) {
            result += procedure + "\n";
        }

        result += "\nEtiquetas:\n";
        for (Etiq label : labelTable) {
            result += label + "\n";
        }

        try {
            // File Writter
            File file = new File(BASE_PATH + Filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(result);
            writer.close();
        } catch (IOException ex) {
            System.out.println(" ERROR WRITING BACKEND TABLES");
            Logger.getLogger(BackTables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getVarAssembler(String name) {
        return this.varTable.get(name).getDirection();
    }

    public Var getVariable(String name) {
        return this.varTable.get(name);
    }

    public Collection<Var> getVariables() {
        return this.varTable.values();
    }

    public Proc getProcedure(String proc) {
        for (Proc procedure : this.procTable) {
            if (procedure.getName().equals(proc)) {
                return procedure;
            }
        }
        return null;
    }

    private int getLastProcedureId() {
        return procTable.get(procTable.size() - 1).getVarNumber();
    }
}
