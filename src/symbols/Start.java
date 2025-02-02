package symbols;

public class Start extends SymbolBase {

    public Boolean last;

    public Start(boolean last) {
        super("Start", 0);
        this.last = last;
    }

    @Override
    public String toString() {
        return "Reached the root";
    }
}