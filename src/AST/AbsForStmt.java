package AST;

public class AbsForStmt extends AbsStmt {


	public AbsValName name;
	

	public AbsValExpr loBound;
	

	public AbsValExpr hiBound;
	

	public AbsStmt stmt;
	
	public AbsForStmt(AbsValName name, AbsValExpr loBound, AbsValExpr hiBound, AbsStmt stmt) {
		this.name = name;
		this.loBound = loBound;
		this.hiBound = hiBound;
		this.stmt = stmt;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
