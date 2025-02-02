package core;

public class LabelPostFor extends SymbolBase {
    private String label;

    public LabelPostFor(String label) {
        super("Label for post instruction", 0);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
