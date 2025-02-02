package core;

import symbolsTable.Type;

public class Declarations extends SymbolBase {

    private String idVar;
    private String val;
    private Type type;

    public Declarations() {
        super("Declarations", 0);
    }

    public Declarations(String idVar, Type type, String val) {
        super("Declarations", 0);
        this.idVar = idVar;
        this.val = val;
        this.type = type;
    }

    public Declarations(String idVar, Type type) {
        super("Declarations", 0);
        this.idVar = idVar;
        this.type = type;
    }

    public String getVarId() {
        return this.idVar;
    }

    public String getValue() {
        return this.val;
    }

    public Type getType() {
        return this.type;
    }

}
