package symbols;

public class Const extends SymbolBase {

    private boolean isConst;

    public Const(boolean isConst) {
        super("Constant Value", 0);
        this.isConst = isConst;
    }

    public boolean getIsConst() {
        return this.isConst;
    }
}
