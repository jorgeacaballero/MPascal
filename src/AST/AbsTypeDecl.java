package AST;

public class AbsTypeDecl extends AbsDecl {

	
	public AbsDeclName name;
	
	
	public AbsTypeExpr type;
	
	public AbsTypeDecl(AbsDeclName name, AbsTypeExpr type) {
		this.name = name;
		this.type = type;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
