package symbols;

public class ArithmeticValue extends SymbolBase {

    private String idVar;
    private Integer val;
    private boolean isConstant;

    public ArithmeticValue(String idVar) {
        super("Arithmetic Value", 0);
        this.idVar = idVar;
        this.isConstant = false;
    }

    public ArithmeticValue(String idVar, Integer val) {
        super("Arithmetic Value", 0);
        this.idVar = idVar;
        this.val = val;
        this.isConstant = true;
    }

    public String getVarId() {
        return this.idVar;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }

    public Object getValue() {
        return this.val;
    }
}
