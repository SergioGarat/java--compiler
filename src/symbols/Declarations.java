package symbols;

import symbolsTable.Type;

public class Declarations extends SymbolBase {

    private Type type;
    private String var_id;

    // if string declaration
    private String value;

    public Declarations() {
        super("SymbolDeclarations", 0);
    }

    public Declarations(String var_id, Type type) {
        super("SymbolDeclarations", 0);
        this.type = type;
        this.var_id = var_id;
    }

    public Declarations(String var_id, Type type, String value) {
        super("SymbolDeclarations", 0);
        this.type = type;
        this.var_id = var_id;
        this.value = value;
    }

    public Type getType() {
        return this.type;
    }

    public String getVarId() {
        return this.var_id;
    }

    public String getValue() {
        return this.value;
    }
}
