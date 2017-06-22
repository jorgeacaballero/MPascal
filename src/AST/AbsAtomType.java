package AST;


public class AbsAtomType extends AbsTypeExpr {

	public static final int BOOL = 0;
	public static final int CHAR = 1;
	public static final int INT = 2;
	
	
	public int type;
	
	public AbsAtomType(int type) {
		this.type = type;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
