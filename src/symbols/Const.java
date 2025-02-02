package symbols;

public class Const extends SymbolBase {

    private boolean isConstant;

    public Const(boolean isConstant) {
        super("Constant Value", 0);
        this.isConstant = isConstant;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }
}
