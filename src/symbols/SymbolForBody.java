package symbols;

public class SymbolForBody extends SymbolBase {

    private String label;

    public SymbolForBody(String label) {
        super("Symbol For Body", 0);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
