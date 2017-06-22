package Assets;

import java.io.*;

import AST.*;


public class FrmCmpAccess extends FrmAccess {

	
	public AbsVarDecl cmp;

	
	public int offset;

	public FrmCmpAccess(AbsVarDecl cmp, int offset) {
		this.cmp = cmp;
		this.offset = offset;
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"cmp offset\" value=\"" + offset + "\"/>\n</frmnode>\n");
	}

}
