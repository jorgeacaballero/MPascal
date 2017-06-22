package AST;


public class AbsValName extends AbsValExpr {
	
	
	public String name;
	
	public AbsValName(String name) {
		this.name = name;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
