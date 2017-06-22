package AST;

public class AbsRecordType extends AbsTypeExpr {
	
	public AbsDecls fields;
	
	public AbsRecordType(AbsDecls fields) {
		this.fields = fields;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
