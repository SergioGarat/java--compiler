package core;

import symbolsTable.Type.TipoSubyacente;

public class FunctionReturn extends SymbolBase {

    private String idVar;
    private TipoSubyacente tipoSubyacente;

    public FunctionReturn() {
        super("Function return", 0);
        this.tipoSubyacente = TipoSubyacente.TS_NULL;
    }

    public FunctionReturn(String idVar, TipoSubyacente tipoSubyacente) {
        super("Function return", 0);
        this.idVar = idVar;
        this.tipoSubyacente = tipoSubyacente;
    }

    public String getVarId() {
        return this.idVar;
    }

    public TipoSubyacente getTipoSubyacente() {
        return tipoSubyacente;
    }
}
