package symbolsTable;

import symbolsTable.Type.Tipo;

public class Descriptor {

    private String identifier;
    private Type dataType;
    private int scope;
    private int firstInstruction = -1;

    public Descriptor(String identifier, Type dataType, int scope) {
        this.identifier = identifier;
        this.dataType = dataType;
        this.scope = scope;
    }

    public Descriptor(ExpandInfo exp) {
        this(exp.getId(), exp.getType(), exp.getScope());
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

    public int getFirst() {
        return this.firstInstruction;
    }

    public void setFirst(int first) {
        this.firstInstruction = first;
    }

    @Override
    public String toString() {
        String result = "identifier : " + this.identifier + " TIPO: " + this.dataType.getTipo() + " TIPO SUBYACENTE: " + this.dataType.getTipoSubyacente() + " SCOPE: " + this.scope;
        if (dataType.getTipo() == Tipo.dfun) {
            result += " FIRST: " + this.firstInstruction;
        }
        return result;
    }
}