%lex
%options case-insensitive

%%
\s+ // ignore whitespace
"INICIO" return 'START';
"FIN" return 'END';

"CONTENIDO" return 'CONTENT';

<<EOF>> return 'EOF';

. return "ERR";
/lex

%start ini
%%

ini: START CONTENT END EOF {console.log("Regla reducida");};