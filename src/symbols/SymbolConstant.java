package symbols;

public class SymbolConstant extends SymbolBase {

    private boolean isConst;

    public SymbolConstant(boolean isConst) {
        super("Symbol Constant Value", 0);
        this.isConst = isConst;
    }

    public boolean getIsConst() {
        return this.isConst;
    }
}
