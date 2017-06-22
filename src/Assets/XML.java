package Assets;

import java.io.*;

public class XML {

	/**
	 * Opens an output file to display the results of each stage of the translation in
	 * XML format.
	 * 
	 * Name of the input file has the same name with the extension phase of translation
	 * <Tt> xml </ tt>. The file appears as the head of an XML document including
	 * (Open) the designation of the main element, which is again the same as the phase.
	 * 
	 * If the shell is set variable <tt> PASCALXSL </ tt>, its
	 * Value is used as a directory in which are stored the corresponding
	 * <Tt> .xsl </ tt> file. In this case, the head of the XML document prints
	 * The reference to the corresponding <tt> .xsl </ tt> file.
	 * 
	 * @param phase
	 *            Phase name translation.
	 * @return Open the output file.
	 */
	public static PrintStream open(String phase) {
		PrintStream stream = null;
		try {
			stream = new PrintStream(phase + ".xml");
			stream.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<?xml-stylesheet type='text/xsl' href='xsl/"+phase+".xsl'?>");
			try {
				String xslDir = System.getenv("PASCALXSL");
				if (xslDir != null) {
					stream.println("<?xml-stylesheet type=\"text/xsl\" href=\""
							+ xslDir + "/" + phase + ".xsl\"?>");
				}
			} catch (Exception _) {
			}
			stream.println("<" + phase + ">");
		} catch (IOException _) {
			Report.error("Cannot open XML file '" + phase + ".xml'.", 1);
		}
		return stream;
	}

	/**
	 * Closes the output file to display the results of each stage of the translation in
	 * XML format.
	 * 
	 * When closing the file prints the (closed) mark the main element,
	 * Which is the same as the phase of the.
	 * 
	 * @param phase
	 *            Phase name translation.
	 * @param stream
	 *            Open the output file.
	 */
	public static void close(String phase, PrintStream stream) {
		stream.println("</" + phase + ">");
		stream.close();
	}
}
