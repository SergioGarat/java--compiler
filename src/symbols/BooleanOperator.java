package symbols;

public class BooleanOperator extends SymbolBase {

    private String idVar;
    private Boolean val;
    private boolean isConst;

    public BooleanOperator(String idVar) {
        super("Boolean Operator", 0);
        this.idVar = idVar;
        this.isConst = false;
    }

    public BooleanOperator(String idVar, Boolean val) {
        super("Boolean Operator", 0);
        this.idVar = idVar;
        this.val = val;
        this.isConst = true;
    }

    public String getVarId() {
        return this.idVar;
    }

    public Boolean getValue() {
        return this.val;
    }

    public boolean getIsConst() {
        return this.isConst;
    }
    
}

