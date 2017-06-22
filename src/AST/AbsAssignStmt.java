package AST;

public class AbsAssignStmt extends AbsStmt {

	
	public AbsValExpr dstExpr;
	
	
	public AbsValExpr srcExpr;
	
	public AbsAssignStmt(AbsValExpr dstExpr, AbsValExpr srcExpr) {
		this.dstExpr = dstExpr;
		this.srcExpr = srcExpr;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
