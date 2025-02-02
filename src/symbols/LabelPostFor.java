package symbols;

public class LabelPostFor extends SymbolBase {
    private String label;

    public LabelPostFor(String label) {
        super("Label For Post Instruction", 0);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
