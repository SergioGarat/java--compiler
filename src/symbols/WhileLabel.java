package symbols;

public class WhileLabel extends SymbolBase {

    private String label;

    public WhileLabel(String label) {
        super("Label while", 0);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}