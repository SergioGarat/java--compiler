package core;

import symbolsTable.Type;

import java.util.ArrayList;
import java.util.List;

public class FieldList extends SymbolBase {

    private ArrayList<TupleField> fields;

    private int size;

    public FieldList(ArrayList<TupleField> fields) {
        super("FieldList", 0);
        this.fields = fields;
        var x = 0;
        for (var field : fields) {
            x += field.getType().getSize();
        }
        this.size = x;
    }

    public List<Type> getTypes() {
        var types = new ArrayList<Type>();
        for (var field : fields) {
            types.add(field.getType());
        }
        return types;
    }

    public ArrayList<TupleField> getFields() {
        return fields;
    }

    public void setFields(ArrayList<TupleField> fields) {
        this.fields = fields;
    }

    public void addField(TupleField field) {
        fields.add(field);
        size += field.getType().getSize();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FieldList() {
        super("FieldList", 0);
    }
}
