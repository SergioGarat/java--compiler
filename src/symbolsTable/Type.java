package symbolsTable;

import java.util.List;

public class Type {
    private Tipo tipo;

    private TipoSubyacente tipoSubyacente;

    private List<TipoSubyacente> tiposSubyacentes;

    private String backendId = "";

    private int size;
    private Object value;
    private String typeName;

    public Type(Tipo tipo, TipoSubyacente tipoSubyacente, int size) {
        this.tipo = tipo;
        this.tipoSubyacente = tipoSubyacente;
        this.size = size;
    }

    public Type(Tipo tipo, List<TipoSubyacente> tiposSubyacentes, int size) {
        this.tipo = tipo;
        this.tiposSubyacentes = tiposSubyacentes;
        this.size = size;
    }

    public Type(Tipo tipo, String typeName) {
        this.tipo = tipo;
        this.typeName = typeName;
    }

    public Type(String backendId, Tipo tipo, String typeName) {
        this.backendId = backendId;
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

    public void setTipoSubyacente(TipoSubyacente tipoSubyacente) {
        this.tipoSubyacente = tipoSubyacente;
    }

    public String getBackendId() {
        return backendId;
    }

    public void setBackendId(String backendId) {
        this.backendId = backendId;
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

    public List<TipoSubyacente> getTiposSubyacentes() {
        return tiposSubyacentes;
    }

    public void setTiposSubyacentes(List<TipoSubyacente> tiposSubyacentes) {
        this.tiposSubyacentes = tiposSubyacentes;
    }

    public enum TipoSubyacente {
        TS_BOOLEAN,
        TS_NUMBER,
        TS_STRING,
        TS_NULL;

        public String toString() {
            return switch (this) {
                case TS_BOOLEAN -> "boolean";
                case TS_NULL -> "null";
                case TS_NUMBER -> "number";
                case TS_STRING -> "string";
                default -> "";
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
}
