package core;

public class ArithmeticOperator extends SymbolBase {

	private String op;

    public ArithmeticOperator(String op) {
        super("Arithmetic operator", 0);
        this.op = op;
    }

    public String getOperator() {
        return this.op;
    }
}
