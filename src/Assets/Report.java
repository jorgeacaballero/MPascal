package Assets;

public class Report {


	public static void warning(String msg) {
		System.err.println(":-o " + msg);
	}

	public static void warning(String msg, int begLine, int begColumn) {
		System.err.println(":-o [" + begLine + ":" + begColumn + "] " + msg);
	}


	public static void warning(String msg, int begLine, int begColumn,
			int endLine, int endColumn) {
		System.err.println(":-o [" + begLine + ":" + begColumn + "-" + endLine
				+ ":" + endColumn + "] " + msg);
	}


	public static void error(String msg, int exitCode) {
		System.err.println(":-( " + msg);
		System.exit(exitCode);
	}

	
	public static void error(String msg, int begLine, int begColumn,
			int exitCode) {
		System.err.println(":-( [" + begLine + ":" + begColumn + "] " + msg);
		System.exit(exitCode);
	}

	public static void error(String msg, int begLine, int begColumn,
			int endLine, int endColumn, int exitCode) {
		System.err.println(":-( [" + begLine + ":" + begColumn + "-" + endLine
				+ ":" + endColumn + "] " + msg);
		System.exit(exitCode);
	}
}
