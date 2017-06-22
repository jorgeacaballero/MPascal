package AST;


public class AbsBlockStmt extends AbsStmt {

	
	public AbsStmts stmts;
	
	public AbsBlockStmt(AbsStmts stmts) {
		this.stmts = stmts;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
