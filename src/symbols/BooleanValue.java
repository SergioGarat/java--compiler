package symbols;

public class BooleanValue extends SymbolBase {

    private Boolean val;

    public BooleanValue(Boolean val) {
        super("Boolean Value", 0);
        this.val = val;
    }

    public Boolean getValue() {
        return this.val;
    }
}
