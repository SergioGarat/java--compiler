package symbols;

public class BooleanOperationValue extends SymbolBase {

    private Boolean value;
    private boolean isConstant;
    private String var_id;

    public BooleanOperationValue(String var_id) {
        super("Boolean Operation Value", 0);
        this.isConstant = false;
        this.var_id = var_id;
    }

    public BooleanOperationValue(String var_id, Boolean value) {
        super("Boolean Operation Value", 0);
        this.value = value;
        this.isConstant = true;
        this.var_id = var_id;
    }

    public Boolean getValue() {
        return this.value;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }

    public String getVarId() {
        return this.var_id;
    }

}
