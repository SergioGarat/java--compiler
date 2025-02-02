package symbols;

public class NumericValue extends SymbolBase {

    private Integer value;

    public NumericValue(int value) {
        super("Number Value", 0);
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
