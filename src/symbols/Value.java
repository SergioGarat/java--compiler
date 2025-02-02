package symbols;

import symbolsTable.Type.Tipo;
import symbolsTable.Type.TipoSubyacente;

public class Value extends SymbolBase {



    private String idVar;
    private String name;
    private Object val;
    private Tipo tipo;
    private TipoSubyacente tipoSubyacente;
    public boolean isConstant;
    private boolean ok;
    private int size;


    public Value() {
        super("Val", 0);
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = TipoSubyacente.TS_NULL;
        this.isConstant = false;
    }


    // String
    public Value(String val, int size) {
        super("Val", 0);
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = TipoSubyacente.TS_STRING;
        this.isConstant = true;
        this.val = val;
        this.ok = true;
        this.size = size;
    }
    
    // Constant
    public Value(String idVar, TipoSubyacente tipoSubyacente, Object val) {
        super("Val", 0);
        this.idVar = idVar;
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = tipoSubyacente;
        this.isConstant = true;
        this.val = val;
    }

    public Value(String idVar, TipoSubyacente tipoSubyacente) {
        super("Val", 0);
        this.idVar = idVar;
        this.tipo = Tipo.dnull;
        this.tipoSubyacente = tipoSubyacente;
        this.isConstant = false;
    }

    public Value(String idVar, Tipo tipo, String name) {
        super("Val", 0);
        this.idVar = idVar;
        this.tipo = tipo;
        this.name = name;
        this.isConstant = false;
    }

    public Value(String idVar, Tipo tipo, String name, Object val) {
        super("Val", 0);
        this.idVar = idVar;
        this.tipo = tipo;
        this.name = name;
        this.isConstant = true;
        this.val = val;
    }

    public String getVarId() {
        return this.idVar;
    }

    public TipoSubyacente getTipoSubyacente() {
        return this.tipoSubyacente;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public String getTypeName() {
        return this.name;
    }

    public Object getValue() {
        return this.val;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }

    public boolean getIsString() {
        return this.ok;
    }

    public int getStringSize() {
        return this.size;
    }
}