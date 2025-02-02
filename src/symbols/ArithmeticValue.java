package symbols;

public class ArithmeticValue extends SymbolBase {

    private String var_id;
    private Integer value;
    private boolean isConst;

    public ArithmeticValue(String var_id) {
        super("Arithmetical Value", 0);
        this.var_id = var_id;
        this.isConst = false;
    }

    public ArithmeticValue(String var_id, Integer value) {
        super("Arithmetical Value", 0);
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

    public Object getValue() {
        return this.value;
    }
}
