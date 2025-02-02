package symbols;

public class ValuesComparison extends SymbolBase {

    private String var_id;
    private boolean isConstant;
    private Boolean value;

    public ValuesComparison(String var_id) {
        super("Value Comparison", 0);
        this.isConstant = false;
        this.var_id = var_id;
    }

    public ValuesComparison(String var_id, Boolean value) {
        super("Value Comparison", 0);
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
