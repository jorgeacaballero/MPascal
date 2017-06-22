package AnalisisSintactico;

import java.io.*;
import java.lang.reflect.*;
import java_cup.runtime.*;

import AnalisisLexico.*;
import AnalisisSemantico.*;
import AST.*;
import Assets.*;

public class Main {

	public static String[] pascalNontNames;

	static {
		/* Pripravimo imena vrst vmesnih simbolov.  */
		PascalTok pascalTok = new PascalTok();
		Field[] pascalToks = pascalTok.getClass().getDeclaredFields();
		pascalNontNames = new String[pascalToks.length];
		for (int f = 0; f < pascalToks.length; f++) {
			try {
				int tok = pascalToks[f].getInt(pascalTok);
				String lex = pascalToks[f].toString().replaceAll("^.*\\.", "");
				if (! ((tok < AnalisisLexico.Main.pascalTermNames.length) &&
					   (lex.equals(AnalisisLexico.Main.pascalTermNames[tok])))) {
					pascalNontNames[tok] = lex;
				}
			}
			catch (IllegalAccessException _) {}
		}
	}

	/** Izvede prevajanje do vkljucno faze sintaksne analize. */
	public static void exec() {
		/* Open the input and output file.  */
		FileReader srcFile = null;
		String srcName = finalcompilator.Main.prgName + ".pascal";
		try { srcFile = new FileReader(srcName); }
		catch (FileNotFoundException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
		PrintStream xml = XML.open("synanal");
		System.out.println("pride do sem");

		PascalLex lexer = new PascalLex(srcFile);
		PascalSyn parser = new PascalSyn(lexer);
		try {
			parser.debug_parse(xml);
		}
		catch (Exception ex) {
			XML.close("synanal", xml);
			Report.error("Error while testing syntax analyzer.", 1);
		}

		/* Zapremo obe datoteki.  */
        XML.close("synanal", xml);
        try { srcFile.close(); }
		catch (IOException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
	}
}
