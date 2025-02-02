package core;

import java.util.ArrayList;

public class TupleValues extends SymbolBase {

    public TupleValues() {
        super("TupleValues", 0);
    }

    public TupleValues(ArrayList<Value> values) {
        super("TupleValues", 0);
        this.values = values;
    }

    private ArrayList<Value> values;

    public void addValue(Value value) {
        values.add(value);
    }

    public ArrayList<Value> getValues() {
        return values;
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }
}
