package core;

public class CallFunction extends SymbolBase {

    private String idFunc;
    private String idBackFunc;

    public CallFunction(String idFunc, String idBackFunc) {
        super("Call-Function", 0);
        this.idFunc = idFunc;
        this.idBackFunc = idBackFunc;
    }

    public String getFunctionId() {
        return this.idFunc;
    }

    public String getBackendId() {
        return this.idBackFunc;
    }
}
