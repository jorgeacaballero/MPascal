package AST;


public class AbsIfStmt extends AbsStmt {
	

	public AbsValExpr cond;


	public AbsStmt thenStmt;
	

	public AbsStmt elseStmt;
	
	public AbsIfStmt(AbsValExpr cond, AbsStmt thenStmt, AbsStmt elseStmt) {

		this.cond = cond;
		this.thenStmt = thenStmt;
		this.elseStmt = elseStmt;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
