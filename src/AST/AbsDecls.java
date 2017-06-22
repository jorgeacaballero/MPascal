package AST;

import java.util.*;

/**
 * Deklaracije: seznam deklaracij.
 */
public class AbsDecls extends AbsTree {
	
	/** Seznam deklaracij. */
	public LinkedList<AbsDecl> decls;
	
	public AbsDecls() {
		decls = new LinkedList<AbsDecl>();
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
