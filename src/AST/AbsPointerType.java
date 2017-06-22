package AST;

public class AbsPointerType extends AbsTypeExpr {

	public AbsTypeExpr type;
	
	public AbsPointerType(AbsTypeExpr type) {
		this.type = type;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
