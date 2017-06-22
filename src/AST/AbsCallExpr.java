package AST;

public class AbsCallExpr extends AbsValExpr {
	

	public AbsValName name;
	

	public AbsValExprs args;
	
	public AbsCallExpr(AbsValName name, AbsValExprs args) {
		this.name = name;
		this.args = args;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
