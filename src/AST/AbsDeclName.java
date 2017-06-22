package AST;

public class AbsDeclName extends AbsDecl {

	
	public String name;
	
	public AbsDeclName(String name) {
		this.name = name;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
