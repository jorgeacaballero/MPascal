package AST;

import java.util.*;

public class AbsDecls extends AbsTree {
	
	
	public LinkedList<AbsDecl> decls;
	
	public AbsDecls() {
		decls = new LinkedList<AbsDecl>();
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
