package backend;

import symbolsTable.Type.TipoSubyacente;

public class Proc {

    private String procName;          // Procedure name
    private TipoSubyacente returnType;    // Return type (type)

    private int memorySize;           // Memory used (size)
    private int variableNumber;      // Variable number (nv)
    private int numParameters;       // Number of parameters
    private int parametersOffset;    // Parameters offset (offset)

    private static int nextVariableId = 0; // More descriptive name


    public Proc(String procName, int numParameters, int memorySize, TipoSubyacente returnType) {
        this.procName = procName;
        this.variableNumber = nextVariableId++;
        this.numParameters = numParameters;
        this.memorySize = memorySize;
        this.returnType = returnType;
        this.parametersOffset = 0;
    }

    public TipoSubyacente getType() {
        return returnType;
    }

    public String getName() {
        return procName;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public int getVarNumber() {
        return variableNumber;
    }

    public int getOffset() {
        return parametersOffset;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public void setParamsOffset(int parametersOffset) {
        this.parametersOffset = parametersOffset;
    }


    @Override
    public String toString() {
        return procName + ":\tNº de parámetros: " + numParameters + "\t|\tTipo: " + returnType;
    }
}