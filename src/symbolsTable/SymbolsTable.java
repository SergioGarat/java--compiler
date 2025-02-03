package symbolsTable;

import errores.SymTabError;
import symbolsTable.Type.Tipo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SymbolsTable {

    private int scope;
    private ArrayList<Integer> scopeTable;
    private HashMap<String, Descriptor> descriptionTable;
    private ArrayList<ExpandInfo> expansionTable;
    private static BufferedWriter out;
    private final String SYMBOLS_TABLE_PATH = "src\\output\\";
    private String filename = "SymbolsTableData.txt";

    public SymbolsTable() {
        reset();
        initializeFileWriter();
        saveTableInFile(null);
    }

    public SymbolsTable(String filename) {
        this.filename = filename + "\\" + "SymbolsTableData.txt";
        reset();
        initializeFileWriter();
        saveTableInFile(null);
    }

    private void initializeFileWriter() {
        try {
            out = new BufferedWriter(new FileWriter(SYMBOLS_TABLE_PATH + filename));
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private void handleIOException(IOException e) {
        System.err.println("Error initializing file writer: " + e.getMessage());
        e.printStackTrace();
    }

    public void add(String id, Type type) throws SymTabError {
        Descriptor oldDescription = descriptionTable.get(id);

        if (oldDescription != null && oldDescription.getScope() <= scope) {
            handleExistingDescription(id, oldDescription);
        }

        Descriptor newDesc = new Descriptor(id, type, scope);
        descriptionTable.put(id, newDesc);
        saveTableInFile("ADD : " + id);
    }

    private void handleExistingDescription(String id, Descriptor oldDescription) throws SymTabError {
        if (oldDescription.getScope() == scope) {
            throw new SymTabError(id + " cannot be added because it already exists in the current scope");
        }
        if (oldDescription.getType().getTipo() == Tipo.dfun) {
            throw new SymTabError(id + " cannot be added because it is a function");
        }
        if (oldDescription.getType().getTipo() == Tipo.dtype) {
            throw new SymTabError(id + " cannot be added because it is a reserved word");
        }

        // Move oldDescription to expansionTable
        int expIndex = scopeTable.get(scope);
        scopeTable.set(scope, expIndex + 1);
        ExpandInfo exp = new ExpandInfo(oldDescription);
        expansionTable.add(expIndex, exp);
    }

    public void addParam(String idFun, String idParamBack, String idParam, Type type) throws SymTabError {
        Descriptor funDes = descriptionTable.get(idFun);

        if (funDes == null || funDes.getType().getTipo() != Tipo.dfun) {
            throw new SymTabError(idFun + " is not a function or does not exist.");
        }

        int idxe = funDes.getFirst();
        int idxep = -1;

        while (idxe != -1 && !expansionTable.get(idxe).getId().equals(idParam)) {
            idxep = idxe;
            idxe = ((ParamsDescriptor) expansionTable.get(idxe)).getNext();
        }

        if (idxe != -1) {
            throw new SymTabError(idParam + " already exists as function param");
        }

        idxe = scopeTable.get(scope);
        scopeTable.set(scope, idxe + 1);
        ParamsDescriptor exp = new ParamsDescriptor(type, idFun, idParamBack, idParam, -1, -1);
        expansionTable.add(idxe, exp);

        updateFunctionParams(idFun, funDes, idxe, idxep);
        saveTableInFile("ADD PARAM: " + idParamBack + " function : " + idFun);
    }

    private void updateFunctionParams(String idFun, Descriptor funDes, int idxe, int idxep) {
        if (idxep == -1) {
            funDes.setFirst(idxe);
            descriptionTable.put(idFun, funDes);
        } else {
            ParamsDescriptor expP = (ParamsDescriptor) expansionTable.get(idxep);
            expP.setNext(idxe);
            expansionTable.set(idxep, expP);
        }
    }

    public Type get(String id) throws SymTabError {
        if (!descriptionTable.containsKey(id)) {
            return getParamTypeFromExpansion(id);
        }
        return descriptionTable.get(id).getType();
    }

    private Type getParamTypeFromExpansion(String id) throws SymTabError {
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

    public int getNumParams(String idFun) throws SymTabError {
        Descriptor funDes = descriptionTable.get(idFun);
        int count = 0;

        if (funDes == null || funDes.getType().getTipo() != Tipo.dfun) {
            throw new SymTabError(idFun + " is not a function");
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

        if (funDes == null || funDes.getType().getTipo() != Tipo.dfun) {
            throw new SymTabError(idFun + " is not a function");
        }

        int idxe = funDes.getFirst();
        int pos = index;

        while (idxe != -1 && pos > 0) {
            idxe = ((ParamsDescriptor) expansionTable.get(idxe)).getNext();
            pos--;
        }

        if (idxe == -1) {
            throw new SymTabError(idFun + " has no param at index " + index);
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
            throw new SymTabError("Compiler error: out of scope 1");
        }

        this.scope--;
        cleanUpScope();
        this.scopeTable.remove(this.scopeTable.size() - 1);
        saveTableInFile("LEAVE BLOCK : decrease scope");
    }

    private void cleanUpScope() {
        ArrayList<String> keys = new ArrayList<>(descriptionTable.keySet());
        for (String key : keys) {
            if (descriptionTable.get(key).getScope() > this.scope) {
                descriptionTable.remove(key);
            }
        }

        int first = scopeTable.get(scope + 1) - 1;
        int last = scopeTable.get(scope);
        for (int i = first; i >= last; i--) {
            if (expansionTable.get(i).getScope() != -1) {
                Descriptor des = new Descriptor(expansionTable.remove(i));
                descriptionTable.put(des.getId(), des);
            }
        }
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
        StringBuilder result = new StringBuilder();
        if (action != null) {
            result.append("-----------------------------------------------\n")
                  .append("----------------- ACTION DONE -----------------\n")
                  .append("\t").append(action).append("\n")
                  .append("-----------------------------------------------\n\n");
        }

        appendTableData(result);

        try {
            out.write(result.toString());
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private void appendTableData(StringBuilder result) {
        result.append("-----------------------------------------------\n")
              .append("------------- SYMBOLS TABLE -------------- \n")
              .append("-----------------------------------------------\n\n")
              .append("--------------- SCOPE INFO : ").append(this.scope).append(" ----------------\n");

        for (int i = 0; i < this.scopeTable.size(); i++) {
            result.append("scope:").append(i).append(", pointing at: ").append(scopeTable.get(i)).append(" value\n");
        }

        appendDescriptionTableData(result);
        appendExpansionTableData(result);
    }

    private void appendDescriptionTableData(StringBuilder result) {
        result.append("-----------------------------------------------\n")
              .append("-------------- DESCRIPTION TABLE --------------\n\n");
        for (String key : this.descriptionTable.keySet()) {
            result.append(descriptionTable.get(key).toString()).append("\n");
        }
        result.append("\n-----------------------------------------------\n\n");
    }

    private void appendExpansionTableData(StringBuilder result) {
        result.append("-----------------------------------------------\n")
              .append("------------- EXPANSION TABLE -----------------\n\n");
        for (ExpandInfo expansion : this.expansionTable) {
            result.append(expansion.toString()).append("\n");
        }
        result.append("\n-----------------------------------------------\n\n")
              .append("-------------- END SYMBOLS TABLE --------------\n\n");
    }

    public int getActualScope() {
        return this.scope;
    }

    public void closeSymbolsTableFiles() {
        try {
            out.close();
        } catch (IOException e) {
            handleIOException(e);
        }
    }
}
