package AnalisisSemantico;

import java.io.*;

import Assets.*;
import AnalisisLexico.*;
import AnalisisSintactico.*;
import AST.*;

public class Main {

	/** Abstract syntax tree broadcasted programs. */
	public static AbsTree absTree = null;

	/**
	 * Performs translation up phase of semantic analysis.
	 */
	public static void exec() {
		/* Open the input and output file. */
		FileReader srcFile = null;
		String srcName = mpascal.Main_Pascal_EU.prgName + ".pascal";
		try {
			srcFile = new FileReader(srcName);
		} catch (FileNotFoundException _) {
			Report.error("Source file '" + srcName + "' cannot be opened.", 1);
		}
		PrintStream xml = XML.open("semanal");

		PascalLex lexer = new PascalLex(srcFile);
		PascalSyn parser = new PascalSyn(lexer);
		AbsProgram program = null;
		try {
			program = (AbsProgram) (parser.parse().value);
		} catch (Exception ex) {
			Report.error("Uncaught syntax error.", 1);
		}
		SemNameResolver nameResolver = new SemNameResolver();
		SemTypeChecker typeChecker = new SemTypeChecker();
		program.accept(nameResolver);
		program.accept(typeChecker);
		program.accept(new SemPrintXML(xml));

		/* Close both files. */
		XML.close("semanal", xml);
		try {
			srcFile.close();
		} catch (IOException _) {
			Report.error("Source file '" + srcName + "' cannot be closed.", 1);
		}
		
		if (nameResolver.error || typeChecker.error) {
			Report.error("Too many errors.", 1);
		}
	}
}
