package symbols;

public class SymbolLabelPostFor extends SymbolBase {
    private String label;

    public SymbolLabelPostFor(String label) {
        super("Symbol Label For Post Instruction", 0);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
