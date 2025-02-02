package symbols;

public class BooleanOperator extends SymbolBase {

    private String var_id;
    private Boolean value;
    private boolean isConst;

    public BooleanOperator(String var_id) {
        super("Boolean Operator", 0);
        this.var_id = var_id;
        this.isConst = false;
    }

    public BooleanOperator(String var_id, Boolean value) {
        super("Boolean Operator", 0);
        this.var_id = var_id;
        this.value = value;
        this.isConst = true;
    }

    public String getVarId() {
        return this.var_id;
    }

    public boolean getIsConst() {
        return this.isConst;
    }

    public Boolean getValue() {
        return this.value;
    }
}

