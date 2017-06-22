package AST;

import java.util.*;

/**
 * Stavki: seznam stavkov.
 */
public class AbsStmts extends AbsTree {
	
	/** Seznam stavkov. */
	public LinkedList<AbsStmt> stmts;
	
	public AbsStmts() {
		stmts = new LinkedList<AbsStmt>();
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
