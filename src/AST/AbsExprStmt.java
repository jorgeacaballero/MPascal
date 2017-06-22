package AST;

public class AbsExprStmt extends AbsStmt {


	public AbsValExpr expr;
	
	public AbsExprStmt(AbsValExpr expr) {
		this.expr = expr;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
