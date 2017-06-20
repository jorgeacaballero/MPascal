/* The following code was generated by JFlex 1.6.1 */

package mpascal;

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>src/mpascal/lex2.flex</tt>
 */
public class Flexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 2;
  public static final int COMILLA_SIMPLE = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\43\1\45\1\57\1\60\1\44\22\0\1\43\6\0\1\31"+
    "\1\3\1\5\1\4\1\35\1\50\1\36\1\56\1\40\12\54\1\55"+
    "\1\42\1\33\1\32\1\34\2\0\1\15\1\23\1\24\1\16\1\12"+
    "\1\21\1\26\1\25\1\52\2\51\1\13\1\37\1\14\1\20\1\41"+
    "\1\51\1\7\1\53\1\11\1\30\1\22\1\6\1\51\1\17\1\51"+
    "\1\46\1\0\1\47\1\0\1\51\1\0\1\15\1\23\1\24\1\16"+
    "\1\12\1\21\1\26\1\25\1\52\2\51\1\13\1\37\1\14\1\20"+
    "\1\41\1\51\1\7\1\53\1\11\1\30\1\22\1\6\1\51\1\17"+
    "\1\51\1\1\1\0\1\2\7\0\1\57\252\0\2\10\115\0\1\27"+
    "\u1ea8\0\1\57\1\57\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\4\1\5\2\6\1\1"+
    "\13\6\1\1\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\6\1\15\1\6\1\16\2\17\1\20\1\21"+
    "\1\22\2\6\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\3\6\1\0\1\31\1\6\1\32\6\6\1\0\1\33"+
    "\1\6\1\34\1\35\7\6\1\0\1\6\1\0\1\36"+
    "\1\37\1\40\3\6\1\31\1\6\1\41\1\42\1\0"+
    "\1\6\1\0\3\6\1\0\2\6\1\0\1\6\1\43"+
    "\1\44\1\6\1\45\2\46\1\6\1\47\1\6\1\50"+
    "\3\6\1\0\1\6\1\51\1\52\3\6\1\0\1\6"+
    "\1\0\1\6\1\53\1\6\1\0\1\54\1\55\2\56"+
    "\1\6\1\0\1\6\1\0\2\6\1\57\2\0\5\6"+
    "\2\60\2\61\2\6\1\0\1\62\1\54\1\6\2\63"+
    "\1\6\1\0\2\64\4\6\1\0\1\6\1\53\1\65"+
    "\2\0\2\6\1\66\3\6\1\66\2\60\1\67\1\0"+
    "\1\6\1\70\1\6\1\71\1\67\2\72\1\6\1\73";

  private static int [] zzUnpackAction() {
    int [] result = new int[187];
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
    "\0\0\0\61\0\142\0\223\0\223\0\223\0\223\0\223"+
    "\0\304\0\365\0\u0126\0\u0157\0\u0188\0\u01b9\0\u01ea\0\u021b"+
    "\0\u024c\0\u027d\0\u02ae\0\u02df\0\u0310\0\u0341\0\u0372\0\u03a3"+
    "\0\u03d4\0\223\0\u0405\0\u0436\0\223\0\223\0\u0467\0\223"+
    "\0\u0498\0\223\0\223\0\u04c9\0\223\0\223\0\223\0\u04fa"+
    "\0\u052b\0\u055c\0\u058d\0\u05be\0\223\0\223\0\223\0\u05ef"+
    "\0\u0620\0\u0651\0\u0682\0\223\0\u06b3\0\u01b9\0\u06e4\0\u0715"+
    "\0\u0746\0\u0777\0\u07a8\0\u07d9\0\u080a\0\u01b9\0\u083b\0\u01b9"+
    "\0\u01b9\0\u086c\0\u089d\0\u08ce\0\u08ff\0\u0930\0\u0961\0\u0992"+
    "\0\u09c3\0\u09f4\0\u0a25\0\223\0\223\0\223\0\u0a56\0\u0a87"+
    "\0\u0ab8\0\u01b9\0\u0ae9\0\223\0\223\0\u0b1a\0\u0b4b\0\u0b7c"+
    "\0\u0bad\0\u0bde\0\u0c0f\0\u0c40\0\u0c71\0\u0ca2\0\u0cd3\0\u0d04"+
    "\0\u01b9\0\u01b9\0\u0d35\0\u01b9\0\223\0\u01b9\0\u0d66\0\u01b9"+
    "\0\u0d97\0\u01b9\0\u0dc8\0\u0df9\0\u0e2a\0\u0e5b\0\u0e8c\0\223"+
    "\0\u01b9\0\u0ebd\0\u0eee\0\u0f1f\0\u0f50\0\u0f81\0\u0fb2\0\u0fe3"+
    "\0\u1014\0\u1045\0\u1076\0\u01b9\0\u01b9\0\223\0\u01b9\0\u10a7"+
    "\0\u10d8\0\u1109\0\u113a\0\u116b\0\u119c\0\u01b9\0\u11cd\0\u11fe"+
    "\0\u122f\0\u1260\0\u1291\0\u12c2\0\u12f3\0\u1324\0\u1355\0\223"+
    "\0\u01b9\0\u1386\0\u13b7\0\u13e8\0\u01b9\0\223\0\u1419\0\223"+
    "\0\u01b9\0\u144a\0\u147b\0\223\0\u01b9\0\u14ac\0\u14dd\0\u150e"+
    "\0\u153f\0\u1570\0\u15a1\0\u01b9\0\u01b9\0\u15d2\0\u1603\0\u1634"+
    "\0\u1665\0\223\0\u1696\0\u16c7\0\u16f8\0\u01b9\0\223\0\u01b9"+
    "\0\223\0\u1729\0\u175a\0\u01b9\0\u178b\0\u01b9\0\u01b9\0\223"+
    "\0\u01b9\0\u17bc\0\u01b9";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[187];
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
    "\1\4\1\5\1\4\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\16"+
    "\1\22\1\23\1\24\1\25\1\26\2\16\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\43\1\45\1\46\1\47"+
    "\1\16\1\50\1\51\1\52\1\53\1\54\1\0\3\43"+
    "\1\55\41\43\1\44\12\43\2\0\31\56\1\57\12\56"+
    "\2\0\11\56\71\0\1\16\1\60\1\0\14\16\1\61"+
    "\1\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\1\16\1\62\14\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\20\0"+
    "\1\63\4\0\1\64\45\0\1\16\1\65\1\0\7\16"+
    "\1\66\4\16\1\67\1\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\2\16\1\0\2\16"+
    "\1\70\1\71\12\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\12\0\2\16\1\0\16\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\12\0"+
    "\2\16\1\0\7\16\1\72\6\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\12\0\1\16\1\73"+
    "\1\0\3\16\1\74\12\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\2\16\1\75\7\16"+
    "\1\76\6\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\1\16\1\77\2\16\12\0\1\16\1\100\1\0"+
    "\10\16\1\101\5\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\12\0\2\16\1\0\4\16\1\102"+
    "\2\16\1\103\6\16\1\0\1\104\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\12\0\2\16\1\0\4\16\1\105"+
    "\11\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\1\16\1\106\5\16\1\107"+
    "\6\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\14\16\1\110\1\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\15\0"+
    "\1\111\55\0\2\16\1\0\3\16\1\112\12\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\4\0"+
    "\31\113\1\0\27\113\32\0\1\114\1\0\1\115\56\0"+
    "\1\116\34\0\2\16\1\0\7\16\1\117\6\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\12\0"+
    "\1\16\1\120\1\0\16\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\51\0\1\43\21\0\2\16"+
    "\1\0\3\16\1\121\4\16\1\122\5\16\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\12\0\2\16"+
    "\1\0\1\123\15\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\60\0\1\52\36\0\1\124\104\0"+
    "\1\125\10\0\2\16\1\126\16\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\1\16\1\127\2\16\12\0"+
    "\2\16\1\130\16\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\1\16\1\131\2\16\12\0\2\16\1\0"+
    "\4\16\1\132\11\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\133\7\0\4\16\15\0\1\134\55\0\2\16\1\0"+
    "\16\16\1\0\1\135\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\1\16\1\136\14\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\12\0"+
    "\2\16\1\0\16\16\1\137\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\2\16\1\140\1\16\12\0\2\16\1\0"+
    "\5\16\1\141\10\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\12\0\2\16\1\0\1\142\15\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\12\0\1\16\1\143\1\0\16\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\12\0\2\16\1\0"+
    "\5\16\1\144\10\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\26\0\1\145\44\0\2\16\1\0"+
    "\11\16\1\146\4\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\12\0\2\16\1\0\2\16\1\147"+
    "\13\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\1\16\1\150\1\0\16\16\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\12\0\2\16"+
    "\1\0\3\16\1\151\12\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\1\16\1\152\1\0"+
    "\16\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\15\16\1\153\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\12\0\2\16"+
    "\1\0\7\16\1\154\6\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\2\16\1\0\4\16"+
    "\1\155\11\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\13\0\1\156\57\0\2\16\1\0\1\157"+
    "\15\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\35\0\1\160\35\0\2\16\1\0\5\16\1\161"+
    "\10\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\7\16\1\162\6\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\12\0"+
    "\2\16\1\0\1\163\15\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\1\16\1\164\1\0"+
    "\16\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\15\0\1\165\55\0\2\16\1\0\1\166\15\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\17\0\1\167\53\0\2\16\1\0\2\16\1\170\13\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\12\0\2\16\1\0\5\16\1\171\10\16\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\12\0\2\16"+
    "\1\0\1\16\1\172\14\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\16\0\1\173\54\0\2\16"+
    "\1\0\1\16\1\174\14\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\2\16\1\0\3\16"+
    "\1\175\12\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\16\0\1\176\54\0\2\16\1\0\1\16"+
    "\1\177\14\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\12\0\2\16\1\0\4\16\1\200\11\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\12\0\2\16\1\0\16\16\1\201\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\2\16\1\135\1\16\12\0\2\16"+
    "\1\0\13\16\1\202\2\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\2\16\1\203\16\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\1\16"+
    "\1\204\2\16\12\0\2\16\1\0\2\16\1\205\13\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\12\0\1\16\1\206\1\0\16\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\14\0\1\207\41\0"+
    "\1\207\14\0\2\16\1\210\16\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\1\16\1\211\2\16\12\0"+
    "\2\16\1\0\13\16\1\212\1\16\1\213\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\12\0\2\16"+
    "\1\0\1\16\1\214\14\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\2\16\1\207\16\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\1\16"+
    "\1\215\2\16\16\0\1\216\54\0\2\16\1\0\1\16"+
    "\1\217\14\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\16\0\1\220\54\0\2\16\1\0\1\16"+
    "\1\221\14\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\12\0\2\16\1\0\2\16\1\222\13\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\12\0\2\16\1\0\4\16\1\223\11\16\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\32\0\1\224"+
    "\40\0\2\16\1\0\6\16\1\225\7\16\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\16\0\1\226"+
    "\54\0\2\16\1\0\1\227\15\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\20\0\1\230\52\0"+
    "\2\16\1\0\3\16\1\231\12\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\12\0\2\16\1\0"+
    "\1\16\1\232\14\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\20\0\1\233\57\0\1\234\53\0"+
    "\2\16\1\0\2\16\1\235\13\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\12\0\2\16\1\0"+
    "\1\16\1\236\14\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\12\0\1\16\1\237\1\0\16\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\12\0\2\16\1\0\15\16\1\240\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\12\0\2\16\1\0"+
    "\3\16\1\241\12\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\17\0\1\242\53\0\2\16\1\0"+
    "\2\16\1\243\13\16\1\0\1\16\6\0\1\16\1\0"+
    "\1\16\7\0\4\16\12\0\2\16\1\0\3\16\1\244"+
    "\12\16\1\0\1\16\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\1\245\15\16\1\0\1\16"+
    "\6\0\1\16\1\0\1\16\7\0\4\16\16\0\1\246"+
    "\54\0\2\16\1\247\16\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\1\16\1\250\2\16\12\0\2\16"+
    "\1\0\4\16\1\251\11\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\32\0\1\252\40\0\2\16"+
    "\1\0\5\16\1\253\10\16\1\0\1\16\6\0\1\16"+
    "\1\0\1\16\7\0\4\16\12\0\2\16\1\0\4\16"+
    "\1\254\11\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\12\0\2\16\1\0\1\16\1\255\14\16"+
    "\1\0\1\16\6\0\1\16\1\0\1\16\7\0\4\16"+
    "\12\0\2\16\1\0\15\16\1\256\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\20\0\1\257\52\0"+
    "\2\16\1\0\3\16\1\260\12\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\13\0\1\261\71\0"+
    "\1\262\46\0\2\16\1\0\7\16\1\263\6\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\12\0"+
    "\2\16\1\0\3\16\1\264\12\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\12\0\2\16\1\0"+
    "\16\16\1\0\1\265\6\0\1\16\1\0\1\16\7\0"+
    "\4\16\12\0\2\16\1\0\16\16\1\0\1\16\6\0"+
    "\1\266\1\0\1\16\7\0\4\16\12\0\1\16\1\267"+
    "\1\0\16\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\20\0\1\270\52\0\2\16\1\0\3\16"+
    "\1\271\12\16\1\0\1\16\6\0\1\16\1\0\1\16"+
    "\7\0\4\16\12\0\1\16\1\272\1\0\16\16\1\0"+
    "\1\16\6\0\1\16\1\0\1\16\7\0\4\16\12\0"+
    "\2\16\1\0\1\16\1\273\14\16\1\0\1\16\6\0"+
    "\1\16\1\0\1\16\7\0\4\16\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6125];
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
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\5\11\21\1\1\11\2\1\2\11\1\1\1\11"+
    "\1\1\2\11\1\1\3\11\5\1\3\11\3\1\1\0"+
    "\1\11\10\1\1\0\13\1\1\0\1\1\1\0\3\11"+
    "\5\1\2\11\1\0\1\1\1\0\3\1\1\0\2\1"+
    "\1\0\5\1\1\11\10\1\1\0\1\1\1\11\4\1"+
    "\1\0\1\1\1\0\3\1\1\0\2\1\1\11\2\1"+
    "\1\0\1\1\1\0\3\1\2\0\7\1\1\11\3\1"+
    "\1\0\1\1\1\11\1\1\1\11\2\1\1\0\1\11"+
    "\5\1\1\0\3\1\2\0\2\1\1\11\4\1\1\11"+
    "\1\1\1\11\1\0\5\1\1\11\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[187];
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
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Flexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 220) {
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
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
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
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
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
    zzFinalHighSurrogate = 0;
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
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
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
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
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
          yycolumn += zzCharCount;
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
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
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
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
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

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new Error("Error Lexico: Se ha encontrado un token inválido: \'"+ yytext() + "\' en la Linea: " + (yyline +1) + ", Columna: " + (yycolumn+1) );
            }
          case 60: break;
          case 2: 
            { yybegin(COMMENT);
            }
          case 61: break;
          case 3: 
            { return symbol(sym.LPar);
            }
          case 62: break;
          case 4: 
            { return symbol(sym.Times,yytext());
            }
          case 63: break;
          case 5: 
            { return symbol(sym.RPar);
            }
          case 64: break;
          case 6: 
            { return symbol(sym.Id, yytext());
            }
          case 65: break;
          case 7: 
            { yybegin(COMILLA_SIMPLE);
            }
          case 66: break;
          case 8: 
            { return symbol(sym.Equal,yytext());
            }
          case 67: break;
          case 9: 
            { return symbol(sym.LessThan,yytext());
            }
          case 68: break;
          case 10: 
            { return symbol(sym.GreaterThan,yytext());
            }
          case 69: break;
          case 11: 
            { return symbol(sym.Sum,yytext());
            }
          case 70: break;
          case 12: 
            { return symbol(sym.Minus,yytext());
            }
          case 71: break;
          case 13: 
            { return symbol(sym.Divided,yytext());
            }
          case 72: break;
          case 14: 
            { return symbol(sym.Semicolon);
            }
          case 73: break;
          case 15: 
            { 
            }
          case 74: break;
          case 16: 
            { return symbol(sym.LBracket);
            }
          case 75: break;
          case 17: 
            { return symbol(sym.RBracket);
            }
          case 76: break;
          case 18: 
            { return symbol(sym.Comma);
            }
          case 77: break;
          case 19: 
            { return symbol(sym.IntLit, yytext());
            }
          case 78: break;
          case 20: 
            { return symbol(sym.Colon);
            }
          case 79: break;
          case 21: 
            { return symbol(sym.Dot);
            }
          case 80: break;
          case 22: 
            { yybegin(YYINITIAL);
            }
          case 81: break;
          case 23: 
            { string.append(yytext());
            }
          case 82: break;
          case 24: 
            { yybegin(YYINITIAL);return symbol(sym.StringLit,string.toString());
            }
          case 83: break;
          case 25: 
            { return symbol(sym.If);
            }
          case 84: break;
          case 26: 
            { return symbol(sym.To);
            }
          case 85: break;
          case 27: 
            { return symbol(sym.Do);
            }
          case 86: break;
          case 28: 
            { return symbol(sym.Or,yytext());
            }
          case 87: break;
          case 29: 
            { return symbol(sym.Of);
            }
          case 88: break;
          case 30: 
            { return symbol(sym.LessEqualThan,yytext());
            }
          case 89: break;
          case 31: 
            { return symbol(sym.NotEqual);
            }
          case 90: break;
          case 32: 
            { return symbol(sym.GreaterEqualThan,yytext());
            }
          case 91: break;
          case 33: 
            { return symbol(sym.Assign);
            }
          case 92: break;
          case 34: 
            { return symbol(sym.DotDot);
            }
          case 93: break;
          case 35: 
            { return symbol(sym.End);
            }
          case 94: break;
          case 36: 
            { return symbol(sym.Not,yytext());
            }
          case 95: break;
          case 37: 
            { return symbol(sym.And,yytext());
            }
          case 96: break;
          case 38: 
            { return symbol(sym.Div,yytext());
            }
          case 97: break;
          case 39: 
            { return symbol(sym.For);
            }
          case 98: break;
          case 40: 
            { return symbol(sym.Var);
            }
          case 99: break;
          case 41: 
            { return symbol(sym.CharLit, yytext().charAt(1));
            }
          case 100: break;
          case 42: 
            { return symbol(sym.Mod,yytext());
            }
          case 101: break;
          case 43: 
            { return symbol(sym.Read);
            }
          case 102: break;
          case 44: 
            { return symbol(sym.BooleanLit,yytext());
            }
          case 103: break;
          case 45: 
            { return symbol(sym.Then);
            }
          case 104: break;
          case 46: 
            { return symbol(sym.Else);
            }
          case 105: break;
          case 47: 
            { return symbol(sym.Char,yytext());
            }
          case 106: break;
          case 48: 
            { return symbol(sym.Write);
            }
          case 107: break;
          case 49: 
            { return symbol(sym.While);
            }
          case 108: break;
          case 50: 
            { return symbol(sym.Array);
            }
          case 109: break;
          case 51: 
            { return symbol(sym.Begin);
            }
          case 110: break;
          case 52: 
            { return symbol(sym.Until);
            }
          case 111: break;
          case 53: 
            { return symbol(sym.Repeat);
            }
          case 112: break;
          case 54: 
            { return symbol(sym.String,yytext());
            }
          case 113: break;
          case 55: 
            { return symbol(sym.Integer,yytext());
            }
          case 114: break;
          case 56: 
            { return symbol(sym.Boolean,yytext());
            }
          case 115: break;
          case 57: 
            { return symbol(sym.Program);
            }
          case 116: break;
          case 58: 
            { return symbol(sym.Function);
            }
          case 117: break;
          case 59: 
            { return symbol(sym.Procedure);
            }
          case 118: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
