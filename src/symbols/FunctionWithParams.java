package symbols;

import sintactico.Parameter;

import java.util.ArrayList;


public class FunctionWithParams extends SymbolBase {

    private ArrayList<Parameter> params;

    public FunctionWithParams(Parameter param) {
        super("Function With Params", 0);
        this.params = new ArrayList<>();
        this.params.add(param);
    }

    public FunctionWithParams(Parameter param, FunctionWithParams prev) {
        super("Function With Params", 0);
        this.params = new ArrayList<>(prev.params);
        this.params.add(param);
    }

    public ArrayList<Parameter> getParams() {
        return this.params;
    }
}