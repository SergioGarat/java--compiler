package symbolsTable;

public class ExpandInfo {

    private String identifier;
    private Type dataType;
    private int scope;

    public ExpandInfo(Type dataType, String identifier, int scope) {
        this.dataType = dataType;
        this.identifier = identifier;
        this.scope = scope;
    }

    public ExpandInfo(Descriptor description) {
        this.dataType = description.getType();
        this.identifier = description.getId();
        this.scope = description.getScope();
    }

    public String getId() {
        return this.identifier;
    }

    public Type getType() {
        return this.dataType;
    }

    public int getScope() {
        return this.scope;
    }

    @Override
    public String toString() {
        return "ID : " + this.identifier + " TIPO: " + this.dataType.getTipo() + " TIPO SUBYACENTE: " + this.dataType.getTipoSubyacente() + " SCOPE: " + this.scope;
    }
}
