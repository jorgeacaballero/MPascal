package AST;

public class AbsProgram extends AbsTree {

	
	public AbsDeclName name;
	
	
	public AbsDecls decls;
	
	
	public AbsBlockStmt stmt;
	
	public AbsProgram(AbsDeclName name, AbsDecls decls, AbsBlockStmt stmt) {
		this.name = name;
		this.decls = decls;
		this.stmt = stmt;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
