package core;

public class ForLabel extends SymbolBase {

    private String label;

    public ForLabel(String label) {
        super("For Label", 0);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}