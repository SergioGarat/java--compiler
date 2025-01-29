package symbols;

public class Start extends SymbolBase {

    public Boolean hasFinished;

    public Start(boolean hasFinished) {
        super("Start!", 0);
        this.hasFinished = hasFinished;
    }

    @Override
    public String toString() {
        return "Arrived to root!!";
    }
}