package symbols;


import sintactico.Parameter;

import java.util.ArrayList;

public class FunctionParams extends SymbolBase {

    private ArrayList<Parameter> parameters = new ArrayList<Parameter>();

    public FunctionParams() {
        super("Function Params", 0);
    }

    public FunctionParams(ArrayList<Parameter> parameters) {
        super("Function Params", 0);
        this.parameters = parameters;
    }

    public ArrayList<Parameter> getParams() {
        return this.parameters;
    }
}
