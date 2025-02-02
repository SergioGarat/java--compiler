package symbols;

import symbolsTable.Type.TipoSubyacente;

public class FunctionInit extends SymbolBase {

    private String fun_id;
    private TipoSubyacente tipoSubyacente;

    public FunctionInit(String fun_id, TipoSubyacente tipoSubyacente) {
        super("Function Initalization", 0);
        this.tipoSubyacente = tipoSubyacente;
        this.fun_id = fun_id;
    }

    public TipoSubyacente getTipoSubyacente() {
        return this.tipoSubyacente;
    }

    public String getFunId() {
        return this.fun_id;
    }
}
