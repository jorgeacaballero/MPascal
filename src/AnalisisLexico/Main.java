package AnalisisLexico;

import java.io.*;
import java.lang.reflect.*;

import Assets.*;
import AnalisisSintactico.*;

public class Main {

	public static String[] pascalTermNames;

	static {
		PascalTok pascalTok = new PascalTok();
		Field[] pascalToks = pascalTok.getClass().getDeclaredFields();
		pascalTermNames = new String[pascalToks.length];
		for (int f = 0; f < pascalToks.length; f++) {
			try {
				int tok = pascalToks[f].getInt(pascalTok);
				String lex = pascalToks[f].toString().replaceAll("^.*\\.", "");
				pascalTermNames[tok] = lex;
			}
			catch (IllegalAccessException _) {}
		}
	}
	public static void exec() {
		FileReader srcFile = null;
		String srcName = mpascal.Main_Pascal_EU.prgName + ".pascal";
		try { srcFile = new FileReader(srcName); }
		catch (FileNotFoundException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
		PrintStream xml = XML.open("lexanal");

        PascalLex lexer = new PascalLex(srcFile);
        PascalSym symbol;
        try {
            while ((symbol = lexer.next_token ()).sym != PascalTok.EOF) {
            	symbol.toXML(xml);
            }
        }
        catch (IOException _) {
            Report.error("Error while testing lexical analyzer.", 1);
        }
        XML.close("lexanal", xml);
        try { srcFile.close(); }
		catch (IOException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
	}

}
