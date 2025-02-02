package core;

public class CallBody extends SymbolBase {

    private String idFunc;
    private String idBackFunc;
    private int nparams;

    public CallBody(String idFunc, String idBackFunc, int nparams) {
        super("Call-Body", 0);
        this.idFunc = idFunc;
        this.idBackFunc = idBackFunc;
        this.nparams = nparams;
    }

    public String getFunId() {
        return this.idFunc;
    }

    public String getFunBackId() {
        return this.idBackFunc;
    }

    public int getNumParams() {
        return this.nparams;
    }
}
