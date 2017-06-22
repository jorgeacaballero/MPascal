package Assets;

import java.io.*;

import AST.*;


public class FrmArgAccess extends FrmAccess {


	public AbsVarDecl var;


	public FrmFrame frame;


	public int offset;

	public FrmArgAccess(AbsVarDecl var, FrmFrame frame) {
		this.var = var;
		this.frame = frame;
		this.offset = 4 + frame.numArgs * 4;
		frame.numArgs++;
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"arg offset\" value=\"" + offset + "\"/>\n</frmnode>\n");
	}

}
