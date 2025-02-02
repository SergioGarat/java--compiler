package core;

import sintactico.Parameter;

import java.util.ArrayList;


public class FunctionWithParams extends SymbolBase {

    private ArrayList<Parameter> parameters;

    public FunctionWithParams(Parameter p) {
        super("Function with parameters", 0);
        this.parameters = new ArrayList<>();
        this.parameters.add(p);
    }

    public FunctionWithParams(Parameter p, FunctionWithParams prev) {
        super("Function with parameters", 0);
        this.parameters = new ArrayList<>(prev.parameters);
        this.parameters.add(p);
    }

    public ArrayList<Parameter> getParams() {
        return this.parameters;
    }
}