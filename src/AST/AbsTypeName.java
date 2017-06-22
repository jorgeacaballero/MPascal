package AST;

public class AbsTypeName extends AbsTypeExpr {
	
	
	public String name;
	
	public AbsTypeName(String name) {
		this.name = name;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
