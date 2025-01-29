package symbols;


import symbolsTable.Type.Tipo;

public class Identifier extends SymbolBase {

    private String id;
    private Tipo tipo;
    private String typeName;

    private Object value;
    private boolean isConst;

    public Identifier(String id, Tipo tipo, String typeName) {
        super("Symbol Identifier Value", 0);
        this.id = id;
        this.tipo = tipo;
        this.typeName = typeName;
        this.isConst = false;
    }

    public Identifier(String id, Tipo tipo, String typeName, Object value) {
        super("Symbol Identifier Value", 0);
        this.id = id;
        this.tipo = tipo;
        this.typeName = typeName;
        this.isConst = true;
        this.value = value;
    }

    public String getId() {
        return this.id;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean getIsConst() {
        return this.isConst;
    }
}
