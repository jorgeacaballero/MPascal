package AST;

/**
 * Deklaracije: ime.
 */
public class AbsDeclName extends AbsDecl {

	/* Ime. */
	public String name;
	
	public AbsDeclName(String name) {
		this.name = name;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
