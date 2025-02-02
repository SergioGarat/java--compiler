package symbols;

public class ArithmeticOperator extends SymbolBase {

	private String op;

    public ArithmeticOperator(String op) {
        super("Arithmetical Operator", 0);
        this.op = op;
    }

    public String getOperator() {
        return this.op;
    }
}
