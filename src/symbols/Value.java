package symbols;

import symbolsTable.Type.Tipo;
import symbolsTable.Type.TipoSubyacente;

public class Value extends SymbolBase {

    private Tipo tipo;
    private TipoSubyacente tipoSubyacente;

    private String typeName;
    public boolean isConstant;
    private Object value;

    private String var_id;

    private boolean isString;
    private int stringSize;


    public Value() {
        super("value", 0);
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = TipoSubyacente.TS_NULL;
        this.isConstant = false;
    }


    public Value(String var_id, TipoSubyacente tipoSubyacente) {
        super("value", 0);
        this.var_id = var_id;
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = tipoSubyacente;
        this.isConstant = false;
    }

    // Literall or constant
    public Value(String var_id, TipoSubyacente tipoSubyacente, Object value) {
        super("value", 0);
        this.var_id = var_id;
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = tipoSubyacente;
        this.isConstant = true;
        this.value = value;
    }

    // Literall string
    public Value(String value, int stringSize) {
        super("value", 0);
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = TipoSubyacente.TS_STRING;
        this.isConstant = true;
        this.value = value;
        this.isString = true;
        this.stringSize = stringSize;
    }

    public Value(String var_id, Tipo tipo, String typeName) {
        super("value", 0);
        this.var_id = var_id;
        this.tipo = tipo;
        this.typeName = typeName;
        this.isConstant = false;
    }

    public Value(String var_id, Tipo tipo, String typeName, Object value) {
        super("value", 0);
        this.var_id = var_id;
        this.tipo = tipo;
        this.typeName = typeName;
        this.isConstant = true;
        this.value = value;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public String getVarId() {
        return this.var_id;
    }

    public TipoSubyacente getTipoSubyacente() {
        return this.tipoSubyacente;
    }

    public String getTypeName() {
        return this.typeName;
    }


    public boolean getIsConst() {
        return this.isConstant;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean getIsString() {
        return this.isString;
    }

    public int getStringSize() {
        return this.stringSize;
    }
}