package AST;


public class AbsArrayType extends AbsTypeExpr {

	
	public AbsTypeExpr type;
	
	
	public AbsValExpr loBound;
	
	
	public AbsValExpr hiBound;
	
	public AbsArrayType(AbsTypeExpr type, AbsValExpr loBound, AbsValExpr hiBound) {
		this.type = type;
		this.loBound = loBound;
		this.hiBound = hiBound;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
