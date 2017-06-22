package AST;

public class AbsFunDecl extends AbsDecl {


	public AbsDeclName name;
	

	public AbsDecls pars;
	

	public AbsTypeExpr type;
	

	public AbsDecls decls;
	

	public AbsBlockStmt stmt;
	
	public AbsFunDecl(AbsDeclName name, AbsDecls pars, AbsTypeExpr type, AbsDecls decls, AbsBlockStmt stmt) {
		this.name = name;
		this.pars = pars;
		this.type = type;
		this.decls = decls;
		this.stmt = stmt;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
