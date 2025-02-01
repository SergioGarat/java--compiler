package backend;


import symbolsTable.SymbolsTable;
import symbolsTable.Type.TipoSubyacente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackTables {

    private SymbolsTable tableSymbols;

    static final String BASE_PATH = "src\\output\\";
    private String Filename = "BackTables.txt";
    private ArrayList<Proc> tableProcs;
    private ArrayList<Etiq> tableLbls;
    private HashMap<String, Var> tableVars;

    private static int cntr = 0;


    public BackTables(SymbolsTable tableSymbols, String filename) {
        this.tableProcs = new ArrayList<>();
        this.tableLbls = new ArrayList<>();
        this.tableVars = new HashMap<>();
        this.tableSymbols = tableSymbols;
        this.Filename = filename +"\\"+ this.Filename;
    }

    public BackTables(SymbolsTable tableSymbols) {
        this.tableProcs = new ArrayList<>();
        this.tableLbls = new ArrayList<>();
        this.tableVars = new HashMap<>();
        this.tableSymbols = tableSymbols;
    }

    public Var getVar(String name) {
        return this.tableVars.get(name);
    }

    public String getVarAssembler(String name) {
        return this.tableVars.get(name).getDirection();
    }

    public Collection<Var> getAllVars() {
        return this.tableVars.values();
    }

    public Proc getProc(String procName) {
        return tableProcs.stream()
                         .filter(procedure -> procedure.getName().equals(procName))
                         .findFirst()
                         .orElse(null);
    }
    

    // Adding variable to table
    public String addVar(String nameVar, int size, TipoSubyacente type, boolean isParam) {
        int scope = tableSymbols.getActualScope();
        int procId = tableProcs.get(tableProcs.size() - 1).getVarNumber();

        String name = nameVar + "_" + procId + "_" + scope;
        Proc proc = tableProcs.get(procId);

        int offset = isParam ? proc.getOffset() + 16 : proc.getMemorySize() + size;

        if (isParam) {
            proc.setParamsOffset(offset);
        } else {
            proc.setMemorySize(offset);
        }

        tableVars.put(name, new Var(name, procId, offset, size, type, isParam));

        return name;
    }

    // String var
    public String addStrVar(String nameVar, int size, String value) {
        int scope = tableSymbols.getActualScope();
        int procId = tableProcs.get(tableProcs.size() - 1).getVarNumber();

        String name = nameVar + "_" + procId + "_" + scope;

        tableVars.put(name, new Var(name, procId, size, value));

        return name;
    }

    // Temporal String var
    public String addTempStrVar(int size, String value) {
        int procId = tableProcs.get(tableProcs.size() - 1).getVarNumber();

        String name = "T" + cntr;
        cntr++;

        tableVars.put(name, new Var(name, procId, size, value));

        return name;
    }

    // Temporal var
    public String addTempVar(int size, TipoSubyacente type) {
        int procId = tableProcs.get(tableProcs.size() - 1).getVarNumber();
        Proc proc = tableProcs.get(procId);

        String name = "T" + cntr;
        cntr++;

        int offset = proc.getMemorySize() + size;
        proc.setMemorySize(offset);

        tableVars.put(name, new Var(name, procId, offset, size, type, false));

        return name;
    }

    // Procedure
    public String addProc(String procName, int params, int size, TipoSubyacente type) {
        String name = "PROC_" + procName;

        tableProcs.add(new Proc(name, params, size, type));

        return name;
    }

    // Main
    public String addMain() {
        String name = "PROC_main";

        this.tableProcs.add(new Proc(name, 0, 0, TipoSubyacente.TS_NULL));

        return name;
    }

    // Label
    public String addLabel() {
        String label = "ETIQ_" + tableLbls.size();

        tableLbls.add(new Etiq(label));

        return label;
    }


    public void storeTables() {
        StringBuilder result = new StringBuilder();
        result.append("---------------------------------------------\n")
              .append("---------------- TABLES INFO ----------------\n")
              .append("---------------------------------------------\n\n");
    
        result.append("Variables:\n");
        tableVars.values().forEach(var -> result.append(var).append("\n"));
    
        result.append("\nProcedures:\n");
        tableProcs.forEach(proc -> result.append(proc).append("\n"));
    
        result.append("\nEtiquetas:\n");
        tableLbls.forEach(label -> result.append(label).append("\n"));
    
        try {
            File file = new File(BASE_PATH + Filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(result.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(BackTables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
