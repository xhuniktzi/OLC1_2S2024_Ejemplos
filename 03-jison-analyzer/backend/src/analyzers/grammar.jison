%{
  import LexicalError from './Exceptions/Lexical.js';
%}

%lex
%options case_insensitive

%%

\s+               /* skip whitespace */
[0-9]+            return 'INT_LITERAL'
(\"[^"]*\")       return 'STRING_LITERAL'
";"               return ';'
":"               return ':'
"-"               return '-'
"+"               return '+'
"=="              return '=='
"="               return '='
"("               return '('
")"               return ')'
"{"               return '{'
"}"               return '}'
"echo"            return 'ECHO'
"let"             return 'LET'
"int"             return 'INT'
"string"          return 'STRING'
"null"            return 'NULL'
[a-z][a-z0-9]*    return 'IDENTIFIER'
<<EOF>>           return 'EOF'
. {
  throw new LexicalError(yytext, yylineno + 1, yylloc.first_column + 1);
}

/lex

/* operator associations and precedence */
%{
  import SyntaxError from './Exceptions/Syntax.js'
  import BinaryExpr from './Expressions/Binary.js'
  import LiteralExpr from './Expressions/Literal.js'
  import UnaryExpr from './Expressions/Unary.js'
  import EchoStmt from './Statements/Echo.js'
  import VarDeclarationStmt from './Statements/VarDeclaration.js'
  import VarAssignmentStmt from './Statements/VarAssignment.js'
  import VarLookUpExpr from './Expressions/VarLookUp.js'
  import BlockStmt from './Statements/Block.js'
  const errors = []
%}


%left '=='
%left '+' '-'
%right UMINUS

%start start

%% /* language grammar */

start
  : statements EOF
  { return {errors: errors, ast: $1} }
;

statements
  : statements statement ';'
  {
    $1.push($2)
    $$ = $1
  }
  | statement ';'
  { $$ = [$1] }
  | error ';'
  {
    errors.push(new SyntaxError($1, @1))
    $$ = []
  }
;

statement
  : ECHO expression
  { $$ = new EchoStmt($2, @1) }
  | var_declaration
  | var_assignment
  | block
;

block
  : '{' statements '}'
  { $$ = new BlockStmt($2, @1)}
;

var_declaration
  : LET IDENTIFIER ':' type '=' expression
  { $$ = new VarDeclarationStmt($2, $4, $6, @2) }
  | LET IDENTIFIER ':' type
  { $$ = new VarDeclarationStmt($2, $4, null, @2) }
;

var_assignment
  : IDENTIFIER '=' expression
  { $$ = new VarAssignmentStmt($1, $3, @1) }
;

expressions
  : expressions expression
  {
    $1.push($2)
    $$ = $1
  }
  | expression
  {$$ = [$1]}
;

expression
  : arithmetic
  | relational
  | '(' expression ')'
  { $$ = $2 }
  | '(' error ')'
  {
    errors.push(new SyntaxError(@2, @2))
  }
  | '-' expression %prec UMINUS
  { $$ = new UnaryExpr($1, $2, @1) }
  | literal
  | IDENTIFIER
  { $$ = new VarLookUpExpr($1, @1) }
;

arithmetic
  : expression '+' expression
  {$$ = new BinaryExpr($1, $2, $3, @2) }
  | expression '-' expression
  {$$ = new BinaryExpr($1, $2, $3, @2) }
;

relational
  : expression '==' expression
  { $$ = new BinaryExpr($1, $2, $3, @1) }
;

literal
  : INT_LITERAL
  { $$ = new LiteralExpr($1, 'INT', @1) }
  | STRING_LITERAL
  { $$ = new LiteralExpr($1, 'STRING', @1) }
  | NULL
  { $$ = new LiteralExpr($1, 'NULL', @1) }
;

type
  : INT
  | STRING
  | NULL
;
