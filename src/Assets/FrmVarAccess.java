package Assets;

import java.io.*;

import AST.*;


public class FrmVarAccess extends FrmAccess {


	public int hasValue = 0;
	
	

	public AbsVarDecl var;


	public FrmLabel label;

	public FrmVarAccess(AbsVarDecl var) {
		this.var = var;
		label = FrmLabel.newLabel(var.name.name);
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"label\" value=\"" + label.name() + "\"/>\n</frmnode>\n");
	}

}
