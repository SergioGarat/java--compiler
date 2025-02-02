package core;

public class StringValue extends SymbolBase {

    private String s;

    public StringValue(String s) {
        super("String val", 0);
        this.s = s;
    }

    public String getString() {
        return this.s;
    }
}
