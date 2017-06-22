package AST;

public class AbsAlloc extends AbsValExpr {

	
	public AbsTypeExpr type;
	
	public AbsAlloc(AbsTypeExpr type) {
		this.type = type;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
