package symbols;

public class End extends SymbolBase {

    private String label;

    public End(String label) {
        super("M_End", 0);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
