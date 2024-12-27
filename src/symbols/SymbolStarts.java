package symbols;

public class SymbolStarts extends SymbolBase {

    public Boolean hasFinished;

    public SymbolStarts(boolean hasFinished) {
        super("Start!", 0);
        this.hasFinished = hasFinished;
    }

    @Override
    public String toString() {
        return "Arrived to root!!";
    }
}