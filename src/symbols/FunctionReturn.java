package symbols;

import symbolsTable.Type.TipoSubyacente;

public class FunctionReturn extends SymbolBase {

    private String var_id;
    private TipoSubyacente tipoSubyacente;

    public FunctionReturn() {
        super("Symbol Function Return", 0);
        this.tipoSubyacente = TipoSubyacente.TS_NULL;
    }

    public FunctionReturn(String var_id, TipoSubyacente tipoSubyacente) {
        super("Symbol Function Return", 0);
        this.var_id = var_id;
        this.tipoSubyacente = tipoSubyacente;
    }

    public String getVarId() {
        return this.var_id;
    }

    public TipoSubyacente getTipoSubyacente() {
        return tipoSubyacente;
    }
}
