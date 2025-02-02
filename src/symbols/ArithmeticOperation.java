package symbols;

public class ArithmeticOperation extends SymbolBase {

    private String var_id;
    private Integer value;
    private boolean isConst;

    public ArithmeticOperation(String var_id) {
        super("Arithmetical Operation", 0);
        this.var_id = var_id;
        this.isConst = false;
    }

    public ArithmeticOperation(String var_id, Integer value) {
        super("Arithmetical Operation", 0);
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

    public Integer getValue() {
        return this.value;
    }
    
}
