
package Assets;

import java.io.*;

import AST.*;
import AnalisisSemantico.*;

/** Dostop do lokalne spremenljivke.  */
public class FrmLocAccess extends FrmAccess {

	////// HAS IT BEEN ASSIGNED YET?
	public int hasValue = 0;
	
	/** Opis spremenljivke.  */
	public AbsVarDecl var;

	/** Klicni zapis funkcije, v kateri je spremenljivka deklarirana.  */
	public FrmFrame frame;

	/** Odmik od FPja.  */
	public int offset;

	public FrmLocAccess(AbsVarDecl var, FrmFrame frame) {
		this.var = var;
		this.frame = frame;
		
		SemType type = SemDesc.getActualType(var);
		this.offset = 0 - frame.sizeLocs - type.size();
		frame.sizeLocs = frame.sizeLocs + type.size();
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"loc offset\" value=\"" + offset + "\"/>\n</frmnode>\n");
	}

}
