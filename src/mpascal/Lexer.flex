package mpascal;

import java_cup.runtime.*;
import java.io.Reader;

import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%

%public
%class Lexer
%cup
%char
%line
%column
%caseless
%eofval{
	if(stComment!=0)
		System.out.println("Comment started at line " + commentLine + " is not finished");
	return new Symbol(sym.EOF, yyline, yycolumn);
%eofval}
%eofclose
%{
	int stComment = 0;
	int commentLine;
	StringBuffer string = new StringBuffer();

	private Symbol symbol(String name, int sym) {
		//System.out.println("name: " + name + " sym: " + sym);
		return new Symbol(sym, yyline, yycolumn);
	}

	private Symbol symbol(String name, int sym, Object val) {
		//System.out.println("name: " + name + " sym: " + sym + " val: " + val);
		return new Symbol(sym, yyline, yycolumn, val);
	}

	private void error(String message) {
		System.err.println("Lexical Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
	}
%}

letter = [A-Za-z]
digit = [0-9]

boolean = "true"|"false"
integer = {digit}+
single_char = [^\r\n\'\\]
line_term = \r|\n|\r\n

id = {letter}({letter}|{digit}|[_])*

%state COMMENT, CHARLITERAL, STRING

%%

<YYINITIAL> {
	[ \n\t]+		{ }
	\"				{ string.setLength(0); string.append( yytext() ); yybegin(STRING); }
	\'              { yybegin(CHARLITERAL); }
	"{"				{ commentLine = yyline+1; stComment++; yybegin(COMMENT); }
	"program"		{ return symbol("program", sym.PROGRAM); }
	"begin"			{ return symbol("begin", sym.BEGIN); }
	"writeln"		{ return symbol("writeln", sym.WRITE_LN); }
	"readln"		{ return symbol("writeln", sym.READ_LN); }
	"end"			{ return symbol("end", sym.END); }
	"array"			{ return symbol("array", sym.ARRAY); }
	"div"			{ return symbol("div", sym.DIV); }
	"do"			{ return symbol("do", sym.DO); }
	"else"			{ return symbol("else", sym.ELSE); }
	"if"			{ return symbol("if", sym.IF); }
	"not"			{ return symbol("not", sym.NOT); }
	"of"			{ return symbol("of", sym.OF); }
	"or"			{ return symbol("or", sym.OR); }
	"then"			{ return symbol("then", sym.THEN); }
	"var"			{ return symbol("var", sym.VAR); }
	"while"			{ return symbol("while", sym.WHILE); }
	"repeat"		{ return symbol("repeat", sym.REPEAT); }
	"until"			{ return symbol("until", sym.UNTIL); }
	"for"			{ return symbol("for", sym.FOR); }
	"to"			{ return symbol("to", sym.TO); }
	"in"			{ return symbol("in", sym.IN); }
	"("				{ return symbol("(", sym.LEFT_PAR); }
	")"				{ return symbol(")", sym.RIGHT_PAR); }
	"["				{ return symbol("[", sym.LEFT_BRACKET); }
	"]"				{ return symbol("]", sym.RIGHT_BRACKET); }
	";"				{ return symbol(";", sym.SEMICOLON); }
	":"				{ return symbol(":", sym.COLON); }
	","				{ return symbol(",", sym.COMMA); }
	"."				{ return symbol(".", sym.DOT); }
	":="			{ return symbol(":=", sym.ASIGN); }
	"+"				{ return symbol("+", sym.PLUS); }
	"-"				{ return symbol("-", sym.MINUS); }
	"/"				{ return symbol("/", sym.DIVIDE); }
	"*"				{ return symbol("*", sym.PRODUCT); }
	"mod"			{ return symbol("mod", sym.MOD); }
	"="				{ return symbol("=", sym.EQUALS); }
	"<>"			{ return symbol("<>", sym.NOT_EQUAL); }
	">"				{ return symbol(">", sym.GREATER_THAN); }
	"<"				{ return symbol("<", sym.LESS_THAN); }
	">="			{ return symbol(">=", sym.GREATER_EQUALS); }
	"<="			{ return symbol("<=", sym.LESS_EQUAL); }
	"boolean"		{ return symbol("boolean", sym.BOOLEAN); }
	"true"			{ return symbol("true", sym.TRUE); }
	"false"			{ return symbol("false", sym.FALSE); }
	"char"			{ return symbol("char", sym.CHAR); }
	"integer"		{ return symbol("integer", sym.INTEGER); }
	"string"		{ return symbol("string", sym.STRING); }
	"function"		{ return symbol("function", sym.FUNCTION); }
	"procedure"		{ return symbol("procedure", sym.PROCEDURE); }

	{integer}		{ return symbol("integerconst", sym.INT_CONST, yytext()); }

	{id}			{ Symbol newsym = symbol("id", sym.ID, yytext());
					  //symtab.enter(yytext(), newsym);
					  return newsym;
					}

	.				{ error("Illegal character <"+ yytext()+"> @ Line " + (yyline+1)); }
}

<STRING> {
      \"                             { string.append( yytext() );
                                       yybegin(YYINITIAL);
                                       return symbol("string literal" ,sym.STRING_LITERAL, string.toString());}
      [^\n\r\"\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\n                            { string.append('\n'); }
      \\r                            { string.append('\r'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
 }


<CHARLITERAL> {
  {single_char}\'            { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, yytext().charAt(0)); }

  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol("char literal", sym.CHAR_CONS, '\\');}


  /* error cases */
  {line_term}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

<COMMENT> {
	"{"		{ stComment++; }
	"}"		{ stComment--; if(stComment==0) yybegin(YYINITIAL); }
	[^]	{ }
}