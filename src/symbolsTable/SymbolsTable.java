package symbolsTable;

import errores.SymTabError;
import symbolsTable.Type.Tipo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SymbolsTable {

    /*
    TODO: SE PUEDE REFACTORIZAR LAS TABLAS PARA QUE QUEDE DISTINTO
     */
    private int scope;
    private ArrayList<Integer> scopeTable;
    private HashMap<String, Descriptor> descriptionTable;
    private ArrayList<ExpandInfo> expansionTable;
    private static BufferedWriter out;
    private final String SYMBOLS_TABLE_PATH = "src\\output\\";
    private String filename = "SymbolsTableData.txt";
    public SymbolsTable() {
        reset();
        try {
            out = new BufferedWriter(new FileWriter(SYMBOLS_TABLE_PATH + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Before deleting the symbols table we do write one last time
        saveTableInFile(null);
    }

    public SymbolsTable(String filename) {
        this.filename = filename + "\\" + "SymbolsTableData.txt";
        reset();
        try {
            out = new BufferedWriter(new FileWriter(SYMBOLS_TABLE_PATH + this.filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Before deleting the symbols table we do write one last time
        saveTableInFile(null);
    }

    public void add(String id, Type type) throws SymTabError {
        Descriptor oldDescription = descriptionTable.get(id);

        // if oldDes.scope > scope can override
        if (oldDescription != null && oldDescription.getScope() <= scope) {
            if (oldDescription.getScope() == scope) {
                throw new SymTabError(id + "cannot be added because it already exists in actual scope");
            }
            if (oldDescription.getType().getTipo() == Tipo.dfun) {
                throw new SymTabError(id + "cannot be added because it already exists and is a function");
            }
            if (oldDescription.getType().getTipo() == Tipo.dtype) {
                throw new SymTabError(id + "cannot be added because it is a reserved word");
            }
            // move oldDescription to expansionTable
            int expIndex = scopeTable.get(scope);
            scopeTable.set(scope, expIndex + 1);
            ExpandInfo exp = new ExpandInfo(oldDescription);
            expansionTable.add(expIndex, exp);
        }
        // write into description table
        Descriptor newDesc = new Descriptor(id, type, scope);
        descriptionTable.put(id, newDesc);
        // We've just put data inside the table, so we are writing it
        saveTableInFile("ADD : " + id);
    }

    public void addParam(String idFun, String idParamBack, String idParam, Type type) throws SymTabError {
        Descriptor funDes = descriptionTable.get(idFun);
        // CHECK TYPE
        if (funDes == null) {
            throw new SymTabError(idFun + "does not exist.");

        }
        if (funDes.getType().getTipo() != Tipo.dfun) {
            throw new SymTabError(idFun + "is not a function");
        }

        int idxe = funDes.getFirst();
        int idxep = -1;

        while (idxe != -1 && !expansionTable.get(idxe).getId().equals(idParam)) {
            idxep = idxe;
            idxe = ((ExpandInfo) expansionTable.get(idxe)).getNext();
        }

        if (idxe != -1) {
            throw new SymTabError(idParam + "already exists as function param");
        }

        idxe = scopeTable.get(scope);
        scopeTable.set(scope, idxe + 1);
        ExpandInfo exp = new ExpandInfo(type, idFun, idParamBack, idParam, -1, -1);
        expansionTable.add(idxe, exp);
        if (idxep == -1) {
            funDes.setFirst(idxe);
            descriptionTable.put(idFun, funDes);
        } else {
            ParamsDescriptor expP = (ParamsDescriptor) expansionTable.get(idxep);
            expP.setNext(idxe);
            expansionTable.set(idxep, expP);
        }
        // We've just put data inside the table, so we are writing it
        saveTableInFile("ADD PARAM: " + idParamBack + " function : " + idFun);
    }

    public Type get(String id) throws SymTabError {
        if (!descriptionTable.containsKey(id)) {
            //first check if is param inside expansion
            for (ExpandInfo expansion : expansionTable) {
                if (expansion instanceof ParamsDescriptor) {
                    ParamsDescriptor param = (ParamsDescriptor) expansion;
                    if (param.getParamId().equals(id)) {
                        return param.getType();
                    }
                }
            }
            throw new SymTabError("Unknown id: " + id);
        }
        return descriptionTable.get(id).getType();
    }

    public int getNumParams(String idFun) throws SymTabError {
        Descriptor funDes = descriptionTable.get(idFun);
        int count = 0;
        // CHECK TYPE
        if (funDes == null) {
            throw new SymTabError(idFun + "function not found");

        }
        if (funDes.getType().getTipo() != Tipo.dfun) {
            throw new SymTabError(idFun + "is not a function");
        }

        int idxe = funDes.getFirst();

        while (idxe != -1) {
            count++;
            idxe = ((ParamsDescriptor) expansionTable.get(idxe)).getNext();
        }

        return count;
    }

    public Type getParam(String idFun, int index) throws SymTabError {
        Descriptor funDes = descriptionTable.get(idFun);
        // CHECK TYPE
        if (funDes == null) {
            throw new SymTabError(idFun + "function not found");

        }
        if (funDes.getType().getTipo() != Tipo.dfun) {
            throw new SymTabError(idFun + "is not a function");
        }

        int idxe = funDes.getFirst();
        int pos = index;

        while (idxe != -1 && pos > 0) {
            idxe = ((ParamsDescriptor) expansionTable.get(idxe)).getNext();
            pos--;
        }

        if (idxe == -1) {
            throw new SymTabError(idFun + "has" + " param at index" + index + " does not exist");
        }

        return expansionTable.get(idxe).getType();
    }

    public void enterBlock() {
        scopeTable.add(scope + 1, scopeTable.get(scope));
        scope += 1;
        saveTableInFile("ENTER BLOCK : increase scope");
    }

    public void leaveBlock() throws SymTabError {
        if (scope == 1) {
            throw new SymTabError("Compiler error : out of scope 1");
        }
        this.scope--;
        // remove out of scope variables, or
        // iterate over hashmap
        ArrayList<String> keys = new ArrayList<>(descriptionTable.keySet());
        for (String key : keys) {
            if (descriptionTable.get(key).getScope() > this.scope) {
                descriptionTable.remove(key);
            }
        }
        // move from expansion to description
        int first = scopeTable.get(scope + 1) - 1;
        int last = scopeTable.get(scope);

        for (int i = first; i >= last; i--) {
            // not move coplex types like params
            if (expansionTable.get(i).getScope() != -1) {
                Descriptor des = new Descriptor(expansionTable.remove(i));
                descriptionTable.put(des.getId(), des);
            }
        }

        this.scopeTable.remove(this.scopeTable.size() - 1);
        // We are decreasing the block's level and deleting data
        saveTableInFile("LEAVE BLOCK : decrease scope");
    }

    public void reset() {
        scopeTable = new ArrayList<>();
        descriptionTable = new HashMap<>();
        expansionTable = new ArrayList<>();
        scopeTable.add(0);
        scopeTable.add(0);
        scope = 1;
    }

    public void saveTableInFile(String action) {
        // We ensure that the .txt file is deleted every time we launch the whole compiler (?)
        // This method is called everytime something ocurres to the Symbols Table

        // Header
        String result = "";
        if (action != null) {
            result = "-----------------------------------------------\n"
                    + "----------------- ACTION DONE -----------------\n"
                    + "\t" + action + "\n"
                    + "-----------------------------------------------\n\n";
        }
        String header_bottom =
                "-----------------------------------------------\n"
                        + "------------- SYMBOLS TABLE DATA -------------- \n"
                        + "-----------------------------------------------\n\n";

        result += header_bottom;
        // Scope table data
        result += "-----------------------------------------------\n";
        result += "--------------- SCOPE INFO : " + this.scope + " ----------------\n";

        for (int i = 0; i < this.scopeTable.size(); i++) {
            result += "scope:" + i + ", pointing at: " + scopeTable.get(i) + " value\n";
        }
        result += "-----------------------------------------------\n\n";

        // Description table data
        result += "-----------------------------------------------\n";
        result += "-------------- DESCRIPTION TABLE --------------\n\n";
        for (String key : this.descriptionTable.keySet()) {
            Descriptor desc = this.descriptionTable.get(key);
            result += desc.toString() + "\n";
        }
        result += "\n-----------------------------------------------\n\n";

        // Expansion table data
        result += "-----------------------------------------------\n";
        result += "------------- EXPANSION TABLE -----------------\n\n";
        for (ExpandInfo expansion : this.expansionTable) {
            result += expansion.toString() + "\n";
        }
        result += "\n-----------------------------------------------\n\n";
        result += "-------------- END SYMBOLS TABLE --------------\n\n";

        // Write in a file
        try {
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getActualScope() {
        return this.scope;
    }

    public void closeSymbolsTableFiles() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


