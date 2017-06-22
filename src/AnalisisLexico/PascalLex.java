/* The following code was generated by JFlex 1.5.0-SNAPSHOT */

package AnalisisLexico;

import java.io.*;

import Assets.*;
import AnalisisSintactico.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.5.0-SNAPSHOT
 * from the specification file <tt>pascal.jflex</tt>
 */
public class PascalLex implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\3\1\4\1\4\1\3\22\0\1\34\6\33\1\32"+
    "\1\45\1\47\1\54\1\51\1\42\1\56\1\43\1\33\1\36\11\35"+
    "\1\40\1\50\1\53\1\41\1\52\1\57\1\33\32\37\1\44\1\33"+
    "\1\46\1\55\1\37\1\33\1\5\1\12\1\16\1\7\1\13\1\24"+
    "\1\14\1\30\1\15\2\37\1\23\1\27\1\6\1\17\1\26\1\37"+
    "\1\10\1\20\1\21\1\25\1\22\1\31\1\37\1\11\1\37\1\2"+
    "\1\33\1\1\1\33\6\0\1\4\u1fa2\0\1\4\1\4\udfd6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\4\20\5\1\1\2\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\4\1\27\1\30\5\5\1\31\6\5\1\32\2\5"+
    "\1\33\1\34\3\5\1\35\7\5\2\0\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\5\1\45\1\46"+
    "\1\47\3\5\1\50\10\5\1\51\1\5\1\52\4\5"+
    "\1\53\1\36\1\54\4\5\1\55\2\5\1\56\1\5"+
    "\1\57\1\60\1\61\5\5\1\62\1\5\1\63\2\5"+
    "\1\64\5\5\1\65\1\66\2\5\1\67\4\5\1\70"+
    "\1\71\1\5\1\72\1\73\1\5\1\74\1\5\1\75";

  private static int [] zzUnpackAction() {
    int [] result = new int[153];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\60\0\140\0\140\0\140\0\220\0\300\0\360"+
    "\0\u0120\0\u0150\0\u0180\0\u01b0\0\u01e0\0\u0210\0\u0240\0\u0270"+
    "\0\u02a0\0\u02d0\0\u0300\0\u0330\0\u0360\0\u0390\0\u03c0\0\u03f0"+
    "\0\140\0\u0420\0\140\0\140\0\u0450\0\140\0\140\0\140"+
    "\0\140\0\140\0\140\0\u0480\0\u04b0\0\140\0\140\0\140"+
    "\0\140\0\140\0\140\0\140\0\u04e0\0\u0510\0\u0540\0\u0570"+
    "\0\u05a0\0\u0180\0\u05d0\0\u0600\0\u0630\0\u0660\0\u0690\0\u06c0"+
    "\0\u0180\0\u06f0\0\u0720\0\u0180\0\u0180\0\u0750\0\u0780\0\u07b0"+
    "\0\u0180\0\u07e0\0\u0810\0\u0840\0\u0870\0\u08a0\0\u08d0\0\u0900"+
    "\0\u0930\0\u0960\0\u0990\0\140\0\140\0\140\0\140\0\140"+
    "\0\u0180\0\u09c0\0\u0180\0\u0180\0\u0180\0\u09f0\0\u0a20\0\u0a50"+
    "\0\u0180\0\u0a80\0\u0ab0\0\u0ae0\0\u0b10\0\u0b40\0\u0b70\0\u0ba0"+
    "\0\u0bd0\0\u0180\0\u0c00\0\u0180\0\u0c30\0\u0c60\0\u0c90\0\u0cc0"+
    "\0\140\0\140\0\u0cf0\0\u0d20\0\u0d50\0\u0d80\0\u0db0\0\u0180"+
    "\0\u0de0\0\u0e10\0\u0180\0\u0e40\0\u0180\0\u0180\0\u0180\0\u0e70"+
    "\0\u0ea0\0\u0ed0\0\u0f00\0\u0f30\0\u0180\0\u0f60\0\u0180\0\u0f90"+
    "\0\u0fc0\0\u0180\0\u0ff0\0\u1020\0\u1050\0\u1080\0\u10b0\0\u0180"+
    "\0\u0180\0\u10e0\0\u1110\0\u0180\0\u1140\0\u1170\0\u11a0\0\u11d0"+
    "\0\u0180\0\u0180\0\u1200\0\u0180\0\u0180\0\u1230\0\u0180\0\u1260"+
    "\0\u0180";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[153];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\0\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\13\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\13\1\24\1\13\1\25\2\13"+
    "\1\26\1\27\1\3\1\6\1\30\1\31\1\13\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42"+
    "\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52"+
    "\1\53\1\54\1\6\1\0\27\52\1\6\23\52\63\0"+
    "\1\6\30\0\1\6\30\0\1\13\1\55\1\13\1\56"+
    "\21\13\3\0\3\13\25\0\10\13\1\57\1\13\1\60"+
    "\12\13\3\0\3\13\25\0\10\13\1\61\1\13\1\62"+
    "\12\13\3\0\3\13\25\0\6\13\1\63\16\13\3\0"+
    "\3\13\25\0\25\13\3\0\3\13\25\0\6\13\1\64"+
    "\3\13\1\65\12\13\3\0\3\13\25\0\1\13\1\66"+
    "\14\13\1\67\6\13\3\0\3\13\25\0\1\13\1\70"+
    "\15\13\1\71\5\13\3\0\3\13\25\0\12\13\1\72"+
    "\10\13\1\73\1\13\3\0\3\13\25\0\3\13\1\74"+
    "\13\13\1\75\5\13\3\0\3\13\25\0\10\13\1\76"+
    "\14\13\3\0\3\13\25\0\3\13\1\77\1\100\5\13"+
    "\1\101\10\13\1\102\1\13\3\0\3\13\25\0\1\103"+
    "\24\13\3\0\3\13\25\0\1\104\11\13\1\105\5\13"+
    "\1\106\4\13\3\0\3\13\25\0\3\13\1\107\21\13"+
    "\3\0\3\13\25\0\23\13\1\110\1\13\3\0\3\13"+
    "\20\0\1\111\2\112\2\111\25\112\1\113\25\112\35\0"+
    "\2\30\62\0\1\114\61\0\1\115\55\0\1\116\57\0"+
    "\1\117\10\0\1\120\12\0\2\13\1\121\22\13\3\0"+
    "\3\13\25\0\3\13\1\122\21\13\3\0\3\13\25\0"+
    "\16\13\1\123\6\13\3\0\3\13\25\0\14\13\1\124"+
    "\10\13\3\0\3\13\25\0\15\13\1\125\7\13\3\0"+
    "\3\13\25\0\11\13\1\126\13\13\3\0\3\13\25\0"+
    "\7\13\1\127\15\13\3\0\3\13\25\0\12\13\1\130"+
    "\12\13\3\0\3\13\25\0\2\13\1\131\22\13\3\0"+
    "\3\13\25\0\13\13\1\132\11\13\3\0\3\13\25\0"+
    "\14\13\1\133\10\13\3\0\3\13\25\0\1\13\1\134"+
    "\23\13\3\0\3\13\25\0\1\135\24\13\3\0\3\13"+
    "\25\0\1\13\1\136\23\13\3\0\3\13\25\0\20\13"+
    "\1\137\4\13\3\0\3\13\25\0\21\13\1\140\3\13"+
    "\3\0\3\13\25\0\6\13\1\141\16\13\3\0\3\13"+
    "\25\0\3\13\1\142\21\13\3\0\3\13\25\0\16\13"+
    "\1\143\6\13\3\0\3\13\25\0\3\13\1\144\21\13"+
    "\3\0\3\13\25\0\1\13\1\145\23\13\3\0\3\13"+
    "\25\0\10\13\1\146\1\13\1\147\12\13\3\0\3\13"+
    "\25\0\10\13\1\150\14\13\3\0\3\13\20\0\32\111"+
    "\1\151\57\111\1\152\25\111\32\0\1\153\32\0\1\154"+
    "\24\13\3\0\3\13\25\0\12\13\1\155\12\13\3\0"+
    "\3\13\25\0\10\13\1\156\14\13\3\0\3\13\25\0"+
    "\16\13\1\157\6\13\3\0\3\13\25\0\6\13\1\160"+
    "\16\13\3\0\3\13\25\0\6\13\1\161\16\13\3\0"+
    "\3\13\25\0\13\13\1\162\11\13\3\0\3\13\25\0"+
    "\3\13\1\163\21\13\3\0\3\13\25\0\7\13\1\164"+
    "\15\13\3\0\3\13\25\0\6\13\1\165\16\13\3\0"+
    "\3\13\25\0\6\13\1\166\16\13\3\0\3\13\25\0"+
    "\1\13\1\167\23\13\3\0\3\13\25\0\13\13\1\137"+
    "\11\13\3\0\3\13\25\0\11\13\1\170\13\13\3\0"+
    "\3\13\25\0\15\13\1\171\7\13\3\0\3\13\25\0"+
    "\7\13\1\172\1\13\1\173\13\13\3\0\3\13\25\0"+
    "\16\13\1\174\6\13\3\0\3\13\52\0\1\152\32\0"+
    "\4\13\1\175\20\13\3\0\3\13\25\0\3\13\1\176"+
    "\21\13\3\0\3\13\25\0\1\13\1\177\23\13\3\0"+
    "\3\13\25\0\6\13\1\200\16\13\3\0\3\13\25\0"+
    "\7\13\1\201\15\13\3\0\3\13\25\0\14\13\1\202"+
    "\10\13\3\0\3\13\25\0\16\13\1\203\6\13\3\0"+
    "\3\13\25\0\14\13\1\204\10\13\3\0\3\13\25\0"+
    "\1\205\24\13\3\0\3\13\25\0\3\13\1\206\21\13"+
    "\3\0\3\13\25\0\6\13\1\207\16\13\3\0\3\13"+
    "\25\0\6\13\1\210\16\13\3\0\3\13\25\0\2\13"+
    "\1\211\22\13\3\0\3\13\25\0\1\212\24\13\3\0"+
    "\3\13\25\0\6\13\1\213\16\13\3\0\3\13\25\0"+
    "\6\13\1\214\16\13\3\0\3\13\25\0\10\13\1\215"+
    "\14\13\3\0\3\13\25\0\14\13\1\216\10\13\3\0"+
    "\3\13\25\0\1\217\24\13\3\0\3\13\25\0\2\13"+
    "\1\220\22\13\3\0\3\13\25\0\1\13\1\221\23\13"+
    "\3\0\3\13\25\0\3\13\1\222\21\13\3\0\3\13"+
    "\25\0\12\13\1\223\12\13\3\0\3\13\25\0\6\13"+
    "\1\224\16\13\3\0\3\13\25\0\22\13\1\225\2\13"+
    "\3\0\3\13\25\0\20\13\1\226\4\13\3\0\3\13"+
    "\25\0\1\13\1\227\23\13\3\0\3\13\25\0\3\13"+
    "\1\230\21\13\3\0\3\13\25\0\6\13\1\231\16\13"+
    "\3\0\3\13\20\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4752];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\3\11\23\1\1\11\1\1\2\11\1\1\6\11"+
    "\2\1\7\11\34\1\2\0\1\1\5\11\30\1\2\11"+
    "\57\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[153];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private PascalSym sym(int type) {
    	String sequence = yytext();
    	/*if(type == PascalTok.CHAR_CONST) {
    		sequence = sequence.substring(1, sequence.length()-1);
    	}*/
    
    	if(sequence.equals("''''")) {
    		return new PascalSym(type, yyline + 1, yycolumn + 1, "'");
    	}
        return new PascalSym(type, yyline + 1, yycolumn + 1, sequence);
    }
int bracketCount = 0;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public PascalLex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public PascalLex(java.io.InputStream in) {
    this(new java.io.InputStreamReader
             (in, java.nio.charset.Charset.forName("UTF-8")));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 138) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

    // numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public PascalSym next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { Assets.Report.error("Character not recognizable as valid Pascal keyword, symbol, name, identifier or constant.", yyline+1, yycolumn+1, -1);
          }
        case 62: break;
        case 2: 
          { Assets.Report.error("At least one too many } in your code!", yyline+1, yycolumn+1, -1);
          }
        case 63: break;
        case 3: 
          { bracketCount++; yybegin(COMMENT);
          }
        case 64: break;
        case 4: 
          { 
          }
        case 65: break;
        case 5: 
          { return sym(PascalTok.IDENTIFIER);
          }
        case 66: break;
        case 6: 
          { return sym(PascalTok.INT_CONST);
          }
        case 67: break;
        case 7: 
          { return sym(PascalTok.COLON);
          }
        case 68: break;
        case 8: 
          { return sym(PascalTok.EQU);
          }
        case 69: break;
        case 9: 
          { return sym(PascalTok.COMMA);
          }
        case 70: break;
        case 10: 
          { return sym(PascalTok.DOT);
          }
        case 71: break;
        case 11: 
          { return sym(PascalTok.LBRACKET);
          }
        case 72: break;
        case 12: 
          { return sym(PascalTok.LPARENTHESIS);
          }
        case 73: break;
        case 13: 
          { return sym(PascalTok.RBRACKET);
          }
        case 74: break;
        case 14: 
          { return sym(PascalTok.RPARENTHESIS);
          }
        case 75: break;
        case 15: 
          { return sym(PascalTok.SEMIC);
          }
        case 76: break;
        case 16: 
          { return sym(PascalTok.ADD);
          }
        case 77: break;
        case 17: 
          { return sym(PascalTok.GTH);
          }
        case 78: break;
        case 18: 
          { return sym(PascalTok.LTH);
          }
        case 79: break;
        case 19: 
          { return sym(PascalTok.MUL);
          }
        case 80: break;
        case 20: 
          { return sym(PascalTok.PTR);
          }
        case 81: break;
        case 21: 
          { return sym(PascalTok.SUB);
          }
        case 82: break;
        case 22: 
          { return sym(PascalTok.QMARK);
          }
        case 83: break;
        case 23: 
          { bracketCount --; 
									  if(bracketCount == 0) 
										yybegin(YYINITIAL);
          }
        case 84: break;
        case 24: 
          { bracketCount++;
          }
        case 85: break;
        case 25: 
          { return sym(PascalTok.DO);
          }
        case 86: break;
        case 26: 
          { return sym(PascalTok.IF);
          }
        case 87: break;
        case 27: 
          { return sym(PascalTok.OR);
          }
        case 88: break;
        case 28: 
          { return sym(PascalTok.OF);
          }
        case 89: break;
        case 29: 
          { return sym(PascalTok.TO);
          }
        case 90: break;
        case 30: 
          { return sym(PascalTok.CHAR_CONST);
          }
        case 91: break;
        case 31: 
          { return sym(PascalTok.ASSIGN);
          }
        case 92: break;
        case 32: 
          { return sym(PascalTok.DOTS);
          }
        case 93: break;
        case 33: 
          { return sym(PascalTok.GEQ);
          }
        case 94: break;
        case 34: 
          { return sym(PascalTok.LEQ);
          }
        case 95: break;
        case 35: 
          { return sym(PascalTok.NEQ);
          }
        case 96: break;
        case 36: 
          { return sym(PascalTok.AND);
          }
        case 97: break;
        case 37: 
          { return sym(PascalTok.NIL);
          }
        case 98: break;
        case 38: 
          { return sym(PascalTok.NOT);
          }
        case 99: break;
        case 39: 
          { return sym(PascalTok.DIV);
          }
        case 100: break;
        case 40: 
          { return sym(PascalTok.END);
          }
        case 101: break;
        case 41: 
          { return sym(PascalTok.VAR);
          }
        case 102: break;
        case 42: 
          { return sym(PascalTok.FOR);
          }
        case 103: break;
        case 43: 
          { Assets.Report.error("Invalid character definition.", yyline+1, yycolumn+1, -1);
          }
        case 104: break;
        case 44: 
          { Assets.Report.error("Invalid character. For ', use a sequence of four characters '.", yyline+1, yycolumn+1, -1);
          }
        case 105: break;
        case 45: 
          { return sym(PascalTok.ELSE);
          }
        case 106: break;
        case 46: 
          { return sym(PascalTok.CHAR);
          }
        case 107: break;
        case 47: 
          { return sym(PascalTok.BOOL_CONST);
          }
        case 108: break;
        case 48: 
          { return sym(PascalTok.TYPE);
          }
        case 109: break;
        case 49: 
          { return sym(PascalTok.THEN);
          }
        case 110: break;
        case 50: 
          { return sym(PascalTok.ARRAY);
          }
        case 111: break;
        case 51: 
          { return sym(PascalTok.BEGIN);
          }
        case 112: break;
        case 52: 
          { return sym(PascalTok.CONST);
          }
        case 113: break;
        case 53: 
          { return sym(PascalTok.WHILE);
          }
        case 114: break;
        case 54: 
          { return sym(PascalTok.RECORD);
          }
        case 115: break;
        case 55: 
          { return sym(PascalTok.SINGLE);
          }
        case 116: break;
        case 56: 
          { return sym(PascalTok.BOOL);
          }
        case 117: break;
        case 57: 
          { return sym(PascalTok.INT);
          }
        case 118: break;
        case 58: 
          { return sym(PascalTok.PRIVATE);
          }
        case 119: break;
        case 59: 
          { return sym(PascalTok.PROGRAM);
          }
        case 120: break;
        case 60: 
          { return sym(PascalTok.FUNCTION);
          }
        case 121: break;
        case 61: 
          { return sym(PascalTok.PROCEDURE);
          }
        case 122: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { 	if(bracketCount == 0)
    	return new PascalSym(PascalTok.EOF);
    else {
    	Assets.Report.error("You forgot to close some of your { brackets! Compiler error at line "+yyline+", column "+yycolumn+".", yyline, yycolumn, -1);
    }
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
