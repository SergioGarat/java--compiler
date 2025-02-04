package symbolsTable;

import java.util.List;

public class Type {

    public enum TipoSubyacente {
        TS_BOOLEAN,
        TS_NUMBER,
        TS_STRING,
        TS_TUPLE,
        TS_NULL;

        public String toString() {
            return switch (this) {
                case TS_BOOLEAN -> "boolean";
                case TS_NULL -> "null";
                case TS_NUMBER -> "number";
                case TS_STRING -> "string";
                case TS_TUPLE -> "tuple";
            };
        }
    }

    public enum Tipo {
        dvar,
        dtype,
        dconst,
        dnull,
        dfun,
        darg
    }


    private Tipo tipo;
    private TipoSubyacente tipoSubyacente;
    private List<Type> tupleTypes;
    private String idBack = "";
    private int size;
    private Object value;
    private String typeName;

    public Type(Tipo tipo, TipoSubyacente tipoSubyacente, int size) {
        this.tipo = tipo;
        this.tipoSubyacente = tipoSubyacente;
        this.size = size;
    }

    public Type(Tipo tipo, List<Type> tupleTypes, int size) {
        this.tipo = tipo;
        this.tupleTypes = tupleTypes;
        this.size = size;
    }

    public Type(Tipo tipo, String typeName) {
        this.tipo = tipo;
        this.typeName = typeName;
    }

    public Type(String idBack, Tipo tipo, String typeName) {
        this.idBack = idBack;
        this.tipo = tipo;
        this.typeName = typeName;
    }

    public Type(Tipo tipo, String typeName, Object value) {
        this.tipo = tipo;
        this.typeName = typeName;
        this.value = value;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public TipoSubyacente getTipoSubyacente() {
        return tipoSubyacente;
    }

    public String getBackendId() {
        return idBack;
    }

    public void setIdBack(String idBack) {
        this.idBack = idBack;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Type> getTupleTypes() {
        return tupleTypes;
    }

    public void setTupleTypes(List<Type> tupleTypes) {
        this.tupleTypes = tupleTypes;
    }
}
