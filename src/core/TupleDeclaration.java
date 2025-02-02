package core;

public class TupleDeclaration extends SymbolBase {

    private String idName;
    private FieldList fieldList;
    private int size;

    public TupleDeclaration(String idName, FieldList fieldList, int size) {
        super("TupleDeclaration", 0);
        this.idName = idName;
        this.fieldList = fieldList;
        this.size = size;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TupleDeclaration() {
        super("TupleDeclaration", 0);
    }
}
