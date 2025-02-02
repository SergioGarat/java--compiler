package sintactico;

import symbolsTable.Type;

public class Parameter {
    private String idVar;
    private Type dataType;
    private int n;

    public Parameter(String idVar, Type type, int size) {
        this.idVar = idVar;
        this.dataType = type;
        this.n = size;
    }

    public String getVarId() {
        return this.idVar;
    }

    public Type getType() {
        return this.dataType;
    }

    public int getSize() {
        return this.n;
    }

}
