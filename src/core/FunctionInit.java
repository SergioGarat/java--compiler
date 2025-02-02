package core;

import symbolsTable.Type.TipoSubyacente;

public class FunctionInit extends SymbolBase {

    private String idFunc;
    private TipoSubyacente tipoSubyacente;

    public FunctionInit(String idFunc, TipoSubyacente tipoSubyacente) {
        super("Function init", 0);
        this.idFunc = idFunc;
        this.tipoSubyacente = tipoSubyacente;

    }

    public String getFunId() {
        return this.idFunc;
    }

    public TipoSubyacente getTipoSubyacente() {
        return this.tipoSubyacente;
    }

}
