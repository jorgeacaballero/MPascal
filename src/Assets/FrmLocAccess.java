
package Assets;

import java.io.*;

import AST.*;
import AnalisisSemantico.*;


public class FrmLocAccess extends FrmAccess {


	public int hasValue = 0;
	

	public AbsVarDecl var;


	public FrmFrame frame;


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
