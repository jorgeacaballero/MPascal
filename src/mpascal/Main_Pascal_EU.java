package mpascal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import AnalisisLexico.*;
import AnalisisSintactico.*;
import AnalisisSemantico.*;
import AST.*;
import Assets.*;

public class Main_Pascal_EU {

    /**
     * The name of the program we are translating.
     */
    public static String prgName;

    /**
     * Launcher of the compiler.
     *
     * The first argument on the command line contains the name of the input source file with the source code
     * In the Pascal programming language. The second argument (if present) determines
     * The last phase of the translation that is being done. Other arguments are ignored.
     *
     * @param Argv Arguments of the command line.
     */
    public static void main(String[] args) {
		System.out.println("This is Pascal compiler:");
                
                String[] args2 = new String[1];
                args2[0] = "./test2";

		/* We define the name of the program we are translating. */
		if (args2.length < 1) {
			Report.error("Source file is not specified.", 1);
		} else
			prgName = args2[0];

		/* We define the name of the program we are translating. */
		String phase = args2.length < 2 ? "" : args2[1];
		phase = "abstree";
		/* We perform the selected phase of translation (and all previous stages). */
		if (phase.equals("lexanal")){
        	System.out.println("Lexical analisis");
			AnalisisLexico.Main.exec();
		} else if (phase.equals("synanal")){
			AnalisisSintactico.Main.exec();
		} else if (phase.equals("abstree")){
			AST.Main.exec();
		} else if (phase.equals("semanal")){
			AnalisisSemantico.Main.exec();
		} else if (phase.equals("frames")){
			//frames.Main.exec();
		} else if (phase.equals("imcode")){
			//imcode.Main.exec();
		} else if (phase.equals("lincode")){
			//lincode.Main.exec();
		} else{
			//lincode.Main.exec();
		}
		
        System.out.println("------------");
        System.out.print(":-) Done.\n");
        System.exit(0);
    }
}
