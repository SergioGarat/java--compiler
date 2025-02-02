package symbols;

public class StringValue extends SymbolBase {

    private String string;

    public StringValue(String string) {
        super("String Value", 0);
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
