package AST;

public class AbsProcDecl extends AbsDecl {


	public AbsDeclName name;
	

	public AbsDecls pars;
	

	public AbsDecls decls;
	

	public AbsBlockStmt stmt;
	
	public AbsProcDecl(AbsDeclName name, AbsDecls pars, AbsDecls decls, AbsBlockStmt stmt) {
		this.name = name;
		this.pars = pars;
		this.decls = decls;
		this.stmt = stmt;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
