package core;

public class ArithmeticOperation extends SymbolBase {

    private String idVar;
    private Integer val;
    private boolean isConstant;

    public ArithmeticOperation(String idVar) {
        super("Arithmetic operation", 0);
        this.idVar = idVar;
        this.isConstant = false;
    }

    public ArithmeticOperation(String idVar, Integer val) {
        super("Arithmetic operation", 0);
        this.idVar = idVar;
        this.val = val;
        this.isConstant = true;
    }

    public String getVarId() {
        return this.idVar;
    }

    public Integer getValue() {
        return this.val;
    }

    public boolean getIsConst() {
        return this.isConstant;
    }
    
}
