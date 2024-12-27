package symbols;

public class SymbolValueComparison extends SymbolBase {

    private String var_id;
    private boolean isConstant;
    private Boolean value;

    public SymbolValueComparison(String var_id) {
        super("Symbol Value Comparison", 0);
        this.isConstant = false;
        this.var_id = var_id;
    }

    public SymbolValueComparison(String var_id, Boolean value) {
        super("Symbol Value Comparison", 0);
        this.isConstant = false;
        this.var_id = var_id;
        this.value = value;
    }

    public String getVarId() {
        return this.var_id;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }

    public Boolean getValue() {
        return this.value;
    }
}
