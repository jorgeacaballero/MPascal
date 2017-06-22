package Assets;

import java.io.*;

import AST.*;

/** Dostop do globalne spremenljivke.  */
public class FrmVarAccess extends FrmAccess {

	///// HAS VALUE
	public int hasValue = 0;
	
	
	/** Opis spremenljivke.  */
	public AbsVarDecl var;

	/** Labela spremenljivke.  */
	public FrmLabel label;

	public FrmVarAccess(AbsVarDecl var) {
		this.var = var;
		label = FrmLabel.newLabel(var.name.name);
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"label\" value=\"" + label.name() + "\"/>\n</frmnode>\n");
	}

}
