package symbols;

public class BooleanValue extends SymbolBase {

    private Boolean value;

    public BooleanValue(Boolean value) {
        super("Symbol Boolean Value", 0);
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }
}
