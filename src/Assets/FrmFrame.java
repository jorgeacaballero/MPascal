package Assets;

import java.io.*;
import java.util.*;

import AST.*;

/** Call the routine record.  */
public class FrmFrame implements XMLable {

	/** Description subroutine.  */
	public AbsTree subp;

	/** Static subroutine level.  */
	public int level;

	/** entry Label.  */
	public FrmLabel label;

	/** number of arguments.  */
	public int numArgs;

	/** Local variables subroutine.  */
	LinkedList<FrmLocAccess> locVars;

	/** The block size of local variables.  */
	public int sizeLocs;

	/** Block size for oldFP in redAddr.  */
	public int sizeFPRA = 8;

	/** The block size temporary variables.  */
	public int sizeTmps;

	/** Block size register.  */
	public int sizeRegs;

	/** The size of the output arguments.  */
	public int sizeArgs;

	/** The pointer FP.  */
	public FrmTemp FP;

	/** The variable with the result function.  */
	public FrmTemp RV;

	public FrmFrame(AbsProgram prg, int level) {
		this.subp = prg;
		this.level = level;
		this.label = FrmLabel.newLabel("main");
		this.numArgs = 0;
		this.locVars = new LinkedList<FrmLocAccess> ();
		this.sizeLocs = 0;
		this.sizeFPRA = 8;
		this.sizeTmps = 0;
		this.sizeRegs = 0;
		this.sizeArgs = 0;
		FP = new FrmTemp();
		RV = null;
	}
	
	
	public FrmFrame(AbsProcDecl prc, int level) {
		this.subp = prc;
		this.level = level;
		this.label = (level == 1 ? FrmLabel.newLabel(prc.name.name) : FrmLabel.newLabel());
		this.numArgs = 0;
		this.locVars = new LinkedList<FrmLocAccess> ();
		this.sizeLocs = 0;
		this.sizeFPRA = 8;
		this.sizeTmps = 0;
		this.sizeRegs = 0;
		this.sizeArgs = 0;
		FP = new FrmTemp();
		RV = null;
	}

	public FrmFrame(AbsFunDecl fun, int level) {
		this.subp = fun;
		this.level = level;
		this.label = (level == 1 ? FrmLabel.newLabel(fun.name.name) : FrmLabel.newLabel());
		this.numArgs = 0;
		this.locVars = new LinkedList<FrmLocAccess> ();
		this.sizeLocs = 0;
		this.sizeFPRA = 8;
		this.sizeTmps = 0;
		this.sizeRegs = 0;
		this.sizeArgs = 0;
		FP = new FrmTemp();
		RV = new FrmTemp();
	}

	/** Size call record.  */
	public int size() {
		//Report.warning(""+this.label.name() + ": sizeLocs: "+this.sizeLocs + ", sizeFRPA: " + this.sizeFPRA + ", sizeTmps: " + this.sizeTmps + ", sizeArgs: " + this.sizeArgs + ", numArgs: " + this.numArgs);
		return sizeLocs + sizeFPRA + sizeTmps + sizeRegs + sizeArgs;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n");
		xml.print("<frm kind=\"level\" value=\"" + level + "\"/>\n");
		xml.print("<frm kind=\"label\" value=\"" + label.name() + "\"/>\n");
		xml.print("<frm kind=\"size\" value=\"" + size() + "\"/>\n");
		xml.print("<frm kind=\"FP\" value=\"" + FP.name() + "\"/>\n");
		if (RV != null) xml.print("<frm kind=\"RV\" value=\"" + RV.name() + "\"/>\n");
		xml.print("</frmnode>\n");
	}

}
