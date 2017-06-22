package AnalisisSemantico;

import java.io.*;


public class SemPointerType extends SemType {
	
	
	public SemType type;
	
	public SemPointerType(SemType type) {
		this.type = type;
	}
	
	
	public void toXML(PrintStream xml) {
		xml.print("<semtype kind=\"POINTER\">\n");
		type.toXML(xml);
		xml.print("</semtype>\n");
	}

	
	public boolean coercesTo(SemType type) {
		if (type instanceof SemPointerType) {
			SemPointerType pointerType = (SemPointerType)type;
			return (pointerType.type.coercesTo(this.type)) ||
				((this.type instanceof SemAtomType) && (((SemAtomType)this.type).type == SemAtomType.VOID)) ||
				((pointerType.type instanceof SemAtomType) && (((SemAtomType)pointerType.type).type == SemAtomType.VOID));
		} else
			return false;
	}
	
	
	public int size() {
		return 4;
	}
	
}
