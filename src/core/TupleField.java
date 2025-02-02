package core;

import symbolsTable.Type;

public class TupleField extends SymbolBase {

    private String idName;
    private Type type;

    public TupleField(String idName, Type type) {
        super("TupleDeclaration", 0);
        this.idName = idName;
        this.type = type;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TupleField() {
        super("TupleDeclaration", 0);
    }
}
