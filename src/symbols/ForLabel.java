package symbols;

public class ForLabel extends SymbolBase {

    private String label;

    public ForLabel(String label) {
        super("Symbol For Init Label", 0);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}