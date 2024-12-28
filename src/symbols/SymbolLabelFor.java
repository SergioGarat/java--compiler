package symbols;

public class SymbolLabelFor extends SymbolBase {

    private String label;

    public SymbolLabelFor(String label) {
        super("Symbol For Init Label", 0);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}