package symbols;

public class ArithmeticOperator extends SymbolBase {

	private String operator;

    public ArithmeticOperator(String operator) {
        super("Symbol Arithmetical Operator", 0);
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }
}
