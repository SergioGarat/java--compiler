package symbols;

public class CallFunction extends SymbolBase {

    private String functionId;
    private String functionBackId;

    public CallFunction(String functionId, String functionBackId) {
        super("Function", 0);
        this.functionId = functionId;
        this.functionBackId = functionBackId;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public String getBackendId() {
        return this.functionBackId;
    }
}
