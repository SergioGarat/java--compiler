package symbolsTable;

public class ParamsDescriptor extends ExpandInfo {

	private String idFunc;
	private String idParam; 
	private int nextParam;

	public ParamsDescriptor(Type dataType,  String idFunc, String identifier, String idParam, int scope, int nextP) {
		super(dataType, identifier, scope);
		this.idFunc = idFunc;
		this.idParam = idParam;
		this.nextParam = nextP;
	}

	public void setNext(int nextP) {
		this.nextParam = nextP;
	}

	public int getNext() {
		return this.nextParam;
	}

	public String getFuncId() {
		return this.idFunc;
	}

	public String getParamId() {
		return this.idParam;
	}

	@Override
	public String toString() {
		return  "ID : " + this.getId() + " TIPO: " + this.getType().getTipo() + " TIPO SUBYACENTE: " + this.getType().getTipoSubyacente()
			+ " SCOPE : " + this.getScope() + " FUNCTION : "+this.idFunc+" NEXT : "+this.nextParam;
	}
}
