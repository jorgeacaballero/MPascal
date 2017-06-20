package mpascal;

import java_cup.runtime.*;

%%

%class Flexer
%unicode
%line
%column
%int
%caseless
%cup
%public

%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

Comment          =   \{{Caracter}*\}  |  \(\*{Caracter}*\*\) 


//Funciones
Write                       =   write | writeln
Read                        =   read | readln

//Tipos de datos
Array                       =   array
Of                          =   of
Var                         =   var
Boolean                     =   boolean
Char                        =   char
Integer                     =   integer
String                      =   string

/*Literales*/
BooleanLit              =   true | false
CharLit                 =   '[^']'
IntLit                  =   {Digit}+
StringLit               =   '[^']*'

//Operadores
Equal                 =   =
NotEqual              =   <>
GreaterThan           =   >
LessThan              =   <
GreaterEqualThan      =   >=
LessEqualThan         =   <=
And                   =   and
Or                    =   or
Not                   =   not
Sum                   =   \+
Minus                 =   -
Times                 =   \* 
Mod                   =   mod
Divided               =   \/
Div                   =   div

//Estructuras de control
If                          =   if
Then                        =   then
Else                        =   else
Begin                       =   begin
End                         =   end
For                         =   for
To                          =   to
Do                          =   do
While                       =   while
Repeat                      =   repeat
Until                       =   until

//Otros
Program                     =   program
Procedure                   =   procedure
Function                    =   function
Id                          =   {Letter}({Letter}|{Digit})*
Semicolon                   =   ;
WhiteSpace                  =   {Endline} | [ \t\f]
Endline                     =   \r|\n|\r\n
LPar                        =   \(
RPar                        =   \)
LCbracket                   =   \{
RCbracket                   =   \}
LBracket                    =   \[
RBracket                    =   \]
ComillaSimple               =   '
ComillaDentro               =   \'
Comma                       =   ,
Letter                      =   [a-zA-Z_]
Digit                       =   [0-9]
Colon                       =   :
Assign                      =   :=
Dot                         =   \.
DotDot                      =   \.\.

%state COMMENT
%state COMILLA_SIMPLE

%%
<YYINITIAL> {
    {WhiteSpace}                    {}
    {LCbracket}                    {yybegin(COMMENT);}
    {ComillaSimple}                 {yybegin(COMILLA_SIMPLE);}
    {RCbracket}                   {throw new Error("Error Lexico: Se ha encontrado un token inválido: \'"+ yytext() + "\' en la Linea: " + (yyline +1) + ", Columna: " + (yycolumn+1) );}
    {Program}                       {return symbol(sym.Program);}
    {Procedure}                     {return symbol(sym.Procedure);}
    {Function}                      {return symbol(sym.Function);}
    {If}                            {return symbol(sym.If);}
    {Else}                          {return symbol(sym.Else);}
    {Then}                          {return symbol(sym.Then);}
    {For}                           {return symbol(sym.For);}
    {To}                            {return symbol(sym.To);}
    {Do}                            {return symbol(sym.Do);}
    {While}                         {return symbol(sym.While);}
    {Repeat}                        {return symbol(sym.Repeat);}
    {Until}                         {return symbol(sym.Until);}
    {Comma}                          {return symbol(sym.Comma); }
    {DotDot}                    {return symbol(sym.DotDot);}
    {Dot}                         {return symbol(sym.Dot);}
    {Semicolon}                     {return symbol(sym.Semicolon);}
    {Assign}                {return symbol(sym.Assign);}
    {Colon}                     {return symbol(sym.Colon);}
    {LBracket}                  {return symbol(sym.LBracket);}        
    {RBracket}                 {return symbol(sym.RBracket);}
    {NotEqual}             {return symbol(sym.NotEqual);}
    {GreaterEqualThan}            {return symbol(sym.GreaterEqualThan,yytext());}
    {LessEqualThan}            {return symbol(sym.LessEqualThan,yytext());}
    {Equal}                 {return symbol(sym.Equal,yytext());}
    {GreaterThan}                 {return symbol(sym.GreaterThan,yytext());}
    {LessThan}                 {return symbol(sym.LessThan,yytext());}
    {And}                   {return symbol(sym.And,yytext());}
    {Or}                    {return symbol(sym.Or,yytext());}
    {Not}                   {return symbol(sym.Not,yytext());}
    {Sum}                  {return symbol(sym.Sum,yytext());}
    {Minus}                 {return symbol(sym.Minus,yytext());}
    {Times}        {return symbol(sym.Times,yytext());}
    {Mod}                   {return symbol(sym.Mod,yytext());}
    {Divided}              {return symbol(sym.Divided,yytext());}
    {Div}       {return symbol(sym.Div,yytext());}
    {Char}                      {return symbol(sym.Char,yytext());}
    {Integer}                   {return symbol(sym.Integer,yytext());}
    {Boolean}                       {return symbol(sym.Boolean,yytext());}
    {String}                    {return symbol(sym.String,yytext());}
    {CharLit}               {return symbol(sym.CharLit, yytext().charAt(1));}
    {IntLit}                 {return symbol(sym.IntLit, yytext());}
    {BooleanLit}                {return symbol(sym.BooleanLit,yytext());}
    {LPar}               {return symbol(sym.LPar);}
    {RPar}              {return symbol(sym.RPar);}
    {Var}                           {return symbol(sym.Var);}
    {Array}                         {return symbol(sym.Array);}
    {Of}                            {return symbol(sym.Of);}
    {Begin}                         {return symbol(sym.Begin);}
    {End}                           {return symbol(sym.End);}
    {Write}                         {return symbol(sym.Write);}
    {Read}                          {return symbol(sym.Read);}
    {Id}                 {return symbol(sym.Id, yytext());} 
    .                               {throw new Error("Error Lexico: Se ha encontrado un token inválido: \'"+ yytext() + "\' en la Linea: " + (yyline +1) + ", Columna: " + (yycolumn+1) );}
}

<COMMENT> {
    {RCbracket}               {yybegin(YYINITIAL);}
    {Endline}            {}
    .                           {}
}

<COMILLA_SIMPLE> {
    {ComillaSimple}         {yybegin(YYINITIAL);return symbol(sym.StringLit,string.toString());}
    {ComillaDentro}             {string.append("\'");}
    .                           {string.append(yytext());}
}