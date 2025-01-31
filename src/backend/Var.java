package backend;

import symbolsTable.Type.TipoSubyacente;

public class Var {
    private String varName;        // Variable name
    private int parentId;          // Parent ID
    private int displacement;      // Offset/Displacement
    private int allocatedSize;     // Space occupation/Allocated size
    private TipoSubyacente dataType; // Data type
    private boolean isParameter;   // Is it a parameter?
    private String strValue;       // Only used for string variables

    // Constructor for non-string variables
    public Var(String varName, int parentId, int displacement, int allocatedSize, TipoSubyacente dataType, boolean isParameter) {
        this.varName = varName;
        this.parentId = parentId;
        this.displacement = displacement;
        this.allocatedSize = allocatedSize;
        this.dataType = dataType;
        this.isParameter = isParameter;
    }

    // Constructor for string variables
    public Var(String varName, int parentId, int allocatedSize, String strValue) {
        this(varName, parentId, 0, allocatedSize, TipoSubyacente.TS_STRING, false);
        this.strValue = strValue; // Store the string value
    }

    public String getName() {
        return varName;
    }

    public int getIdParent() {
        return parentId;
    }

    public int getOffset() {
        return displacement;
    }

    public int getSize() {
        return allocatedSize;
    }

    public TipoSubyacente getType() {
        return dataType;
    }

    public boolean isParam() {
        return isParameter;
    }

    public String getValue() {
        return strValue;
    }

    public String getDirection() {
        if (dataType == TipoSubyacente.TS_STRING) {
            return this.getName();
        }
        if (isParameter) {
            return this.getOffset() + "(%rbp)";
        }
        return (-this.getOffset()) + "(%rbp)";
    }

    @Override
    public String toString() {
        return varName + ":\tTamaño: " + allocatedSize + "\t|\tTipo Subyacente:" + dataType +
                "\t|\tIdFuncion : " + parentId + "\t|\tDesplazamiento : " + getDirection();
    }
}