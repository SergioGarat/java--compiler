package symbols;

public class SymbolMFor extends SymbolBase {

    private String label;

    public SymbolMFor(String label) {
        super("Symbol M_For", 0);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}