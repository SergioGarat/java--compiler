package symbols;

public class BooleanOperationValue extends SymbolBase {

    private String idVar;
    private Boolean val;
    private boolean isConstant;

    public BooleanOperationValue(String idVar) {
        super("Boolean operation", 0);
        this.isConstant = false;
        this.idVar = idVar;
    }

    public BooleanOperationValue(String idVar, Boolean val) {
        super("Boolean operation", 0);
        this.val = val;
        this.isConstant = true;
        this.idVar = idVar;
    }

    public String getVarId() {
        return this.idVar;
    }

    public Boolean getValue() {
        return this.val;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }

}
