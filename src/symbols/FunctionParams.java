package symbols;


import sintactico.Parameter;

import java.util.ArrayList;

public class FunctionParams extends SymbolBase {

    private ArrayList<Parameter> params = new ArrayList<Parameter>();

    public FunctionParams() {
        super("Function Params", 0);
    }

    public FunctionParams(ArrayList<Parameter> params) {
        super("Function Params", 0);
        this.params = params;
    }

    public ArrayList<Parameter> getParams() {
        return this.params;
    }
}
