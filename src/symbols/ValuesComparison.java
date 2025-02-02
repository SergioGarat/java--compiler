package symbols;

public class ValuesComparison extends SymbolBase {

    private String idVar;
    private Boolean val;
    private boolean isConstant;

    public ValuesComparison(String idVar) {
        super("Value Comparison", 0);
        this.idVar = idVar;
        this.isConstant = false;
    }

    public ValuesComparison(String idVar, Boolean val) {
        super("Value Comparison", 0);
        this.idVar = idVar;
        this.val = val;
        this.isConstant = false;
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
