package AST;

public class AbsAtomConst extends AbsConstExpr {
	
	public final static int BOOL = 0;
	public final static int CHAR = 1;
	public final static int INT = 2;

	public String value;
	

	public int type;
	
	public AbsAtomConst(String value, int type) {
		this.value = value;
		this.type = type;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
