package AST;

public class AbsConstDecl extends AbsDecl {


	public AbsDeclName name;
	

	public AbsValExpr value;
	
	public AbsConstDecl(AbsDeclName name, AbsValExpr value) {
		this.name = name;
		this.value = value;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
