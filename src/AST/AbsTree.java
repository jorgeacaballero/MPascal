package AST;

/**
 * 
 * @see AbsDecl
 * @see AbsDecls
 * @see AbsProgram
 * @see AbsStmt
 * @see AbsStmts
 * @see AbsTypeExpr
 * @see AbsValExpr
 * @see AbsValExprs
 */
public abstract class AbsTree {

	public boolean error = false;

	public int begLine;

	public int begColumn;

	public int endLine;

	public int endColumn;

	public AbsTree() {
		this.begLine = 0;
		this.begColumn = 0;
		this.endLine = 0;
		this.endColumn = 0;
	}


	public void setBeg(String lexeme, int line, int column) {
		begLine = line;
		begColumn = column;
	}


	public void setBeg(AbsTree tree) {
		begLine = tree.begLine;
		begColumn = tree.begColumn;
	}


	public void setEnd(String lexeme, int line, int column) {
		endLine = line;
		endColumn = column + lexeme.length() - 1;
	}


	public void setEnd(AbsTree tree) {
		endLine = tree.endLine;
		endColumn = tree.endColumn;
	}


	public void setPos(String lexeme, int line, int column) {
		setBeg(lexeme, line, column);
		setEnd(lexeme, line, column);
	}


	public void setPos(AbsTree tree) {
		setBeg(tree);
		setEnd(tree);
	}
	
	public abstract void accept(AbsVisitor visitor);

}
