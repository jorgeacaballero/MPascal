package AST;


public class AbsVarDecl extends AbsDecl {

	
	public AbsDeclName name;
	
	
	public AbsTypeExpr type;
	
	
	public VisibilityType visType;
	
	public AbsVarDecl(AbsDeclName name, AbsTypeExpr type, VisibilityType visType) {
		this.name = name;
		this.type = type;
		this.visType = visType;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
