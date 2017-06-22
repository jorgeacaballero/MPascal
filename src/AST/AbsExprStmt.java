package AST;

/**
 * Klic procedure.
 */
public class AbsExprStmt extends AbsStmt {

	/** Izraz. */
	public AbsValExpr expr;
	
	public AbsExprStmt(AbsValExpr expr) {
		this.expr = expr;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
