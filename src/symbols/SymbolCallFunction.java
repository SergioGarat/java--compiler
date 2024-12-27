package symbols;

public class SymbolCallFunction extends SymbolBase {

    private String functionId;
    private String functionBackId;

    public SymbolCallFunction(String functionId, String functionBackId) {
        super("Symbol Call Function", 0);
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
