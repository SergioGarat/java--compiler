package core;

public class BooleanValue extends SymbolBase {

    private Boolean val;

    public BooleanValue(Boolean val) {
        super("Boolean val", 0);
        this.val = val;
    }

    public Boolean getValue() {
        return this.val;
    }
}
