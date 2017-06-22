package AST;


public class AbsNilConst extends AbsConstExpr {

	public AbsNilConst() {
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
