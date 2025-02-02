package core;

public class NumericValue extends SymbolBase {

    private Integer n;

    public NumericValue(int n) {
        super("Number Value", 0);
        this.n = n;
    }

    public Integer getValue() {
        return this.n;
    }
}
