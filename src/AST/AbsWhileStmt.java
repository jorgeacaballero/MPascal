package AST;

public class AbsWhileStmt extends AbsStmt {

	
	public AbsValExpr cond;
	
	
	public AbsStmt stmt;
	
	public AbsWhileStmt(AbsValExpr cond, AbsStmt stmt) {
		this.cond = cond;
		this.stmt = stmt;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
