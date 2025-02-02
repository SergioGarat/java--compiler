package symbols;

public class ForBody extends SymbolBase {

    private String label;

    public ForBody(String label) {
        super("For Body", 0);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
