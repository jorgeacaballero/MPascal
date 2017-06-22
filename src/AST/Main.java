package AST;

import java.io.*;

import Assets.*;
import AnalisisLexico.*;
import AnalisisSintactico.*;
import mpascal.*;

public class Main {


	public static AbsTree absTree = null;


	public static void exec() {
		/* Open the input and output file. */
		FileReader srcFile = null;
		String srcName = mpascal.Main_Pascal_EU.prgName + ".pascal";
		try {
			srcFile = new FileReader(srcName);
		} catch (FileNotFoundException _) {
			Report.error("Source file '" + srcName + "' cannot be opened.", 1);
		}
		PrintStream xml = XML.open("abstree");

		PascalLex lexer = new PascalLex(srcFile);
		PascalSyn parser = new PascalSyn(lexer);
		AbsProgram program = null;
		try {
			program = (AbsProgram) (parser.parse().value);
		} catch (Exception ex) {
			Report.error(
					"Error while testing the construction of the abstract syntax tree.",
					1);
		}
		program.accept(new AbsPrintXML(xml));

		
		XML.close("abstree", xml);
		try {
			srcFile.close();
		} catch (IOException _) {
			Report.error("Source file '" + srcName + "' cannot be opened.", 1);
		}
	}
}
