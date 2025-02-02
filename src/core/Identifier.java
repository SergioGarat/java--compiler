package core;


import symbolsTable.Type.Tipo;

public class Identifier extends SymbolBase {

    private String identifier;
    private Tipo tipo;
    private Object val;
    private boolean isConstant;
    private String name;

    public Identifier(String identifier, Tipo tipo, String name) {
        super("Identifier", 0);
        this.identifier = identifier;
        this.tipo = tipo;
        this.name = name;
        this.isConstant = false;
    }

    public Identifier(String identifier, Tipo tipo, String name, Object val) {
        super("Identifier", 0);
        this.identifier = identifier;
        this.tipo = tipo;
        this.name = name;
        this.isConstant = true;
        this.val = val;
    }

    public String getId() {
        return this.identifier;
    }

    public String getTypeName() {
        return this.name;
    }

    public Object getValue() {
        return this.val;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }
}
