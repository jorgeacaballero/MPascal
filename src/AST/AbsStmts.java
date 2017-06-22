package AST;

import java.util.*;


public class AbsStmts extends AbsTree {
	

	public LinkedList<AbsStmt> stmts;
	
	public AbsStmts() {
		stmts = new LinkedList<AbsStmt>();
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
