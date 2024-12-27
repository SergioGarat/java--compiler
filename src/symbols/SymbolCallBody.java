package symbols;

public class SymbolCallBody extends SymbolBase {

    private String fun_id;
    private String fun_back_id;
    private int num_params;

    public SymbolCallBody(String fun_id, String fun_back_id, int num_params) {
        super("Symbol Call Body", 0);
        this.fun_id = fun_id;
        this.fun_back_id = fun_back_id;
        this.num_params = num_params;
    }

    public int getNumParams() {
        return this.num_params;
    }

    public String getFunId() {
        return this.fun_id;
    }

    public String getFunBackId() {
        return this.fun_back_id;
    }
}
