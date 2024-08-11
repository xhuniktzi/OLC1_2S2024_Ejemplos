package example_sets;

import java_cup.runtime.Symbol;

%%

%class Lexer
%public
%unicode
%cup
%line
%char
%ignorecase

%{

%}

%%

"CONJ"             { return new Symbol(sym.CONJ, yyline, (int) yychar, yytext()); }
"OPERATION"        { return new Symbol(sym.OPERATION, yyline, (int) yychar, yytext()); }
"U"                { return new Symbol(sym.UNION, yyline, (int) yychar, yytext()); }
"&"                { return new Symbol(sym.INTERSEC, yyline, (int) yychar, yytext()); }
"-"                { return new Symbol(sym.DIFF, yyline, (int) yychar, yytext()); }
"^"                { return new Symbol(sym.COMPLEMENT, yyline, (int) yychar, yytext()); }
[0-9]              { return new Symbol(sym.NUMBER, yyline, (int) yychar, yytext()); }
[0-9A-Za-z_]+      { return new Symbol(sym.VAR, yyline, (int) yychar, yytext()); }
"{"              { return new Symbol(sym.LBRACE, yyline, (int) yychar, yytext()); }
"}"              { return new Symbol(sym.RBRACE, yyline, (int) yychar, yytext()); }
":"              { return new Symbol(sym.COLON, yyline, (int) yychar, yytext()); }
","              { return new Symbol(sym.COMMA, yyline, (int) yychar, yytext()); }
"("              { return new Symbol(sym.LPAREN, yyline, (int) yychar, yytext()); }
")"              { return new Symbol(sym.RPAREN, yyline, (int) yychar, yytext()); }
[ \t\n\r\f]+     { /* Ignorar espacios en blanco */ }
.                { System.err.println("Illegal character: " + yytext()); return new Symbol(sym.error); }
