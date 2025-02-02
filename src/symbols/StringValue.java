package symbols;

public class StringValue extends SymbolBase {

    private String s;

    public StringValue(String s) {
        super("String Value", 0);
        this.s = s;
    }

    public String getString() {
        return this.s;
    }
}
