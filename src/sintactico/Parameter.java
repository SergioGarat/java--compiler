package sintactico;

import symbolsTable.Type;

public class Parameter {
    private String varId;
    private Type type;
    private int size;

    public Parameter(String varId, Type type, int size) {
        this.varId = varId;
        this.type = type;
        this.size = size;
    }

    public String getVarId() {
        return this.varId;
    }

    public int getSize() {
        return this.size;
    }

    public Type getType() {
        return this.type;
    }
}
