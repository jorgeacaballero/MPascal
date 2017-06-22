package AST;

import java.util.*;



public class AbsValExprs extends AbsTree {

	
	public LinkedList<AbsValExpr> exprs;
	
	public AbsValExprs() {
		this.exprs = new LinkedList<AbsValExpr>();
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
