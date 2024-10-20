%{
  import LexicalError from './Exceptions/Lexical.js';
%}

%lex
%options case_insensitive

%%

\s+               /* skip whitespace */
[0-9]+            return 'INT_LITERAL'
(\"[^"]*\")       return 'STRING_LITERAL'
True|False        return 'BOOL_LITERAL'
","               return ','
";"               return ';'
":"               return ':'
"-"               return '-'
"+"               return '+'
"=="              return '=='
">"               return '>'
"="               return '='
"("               return '('
")"               return ')'
"{"               return '{'
"}"               return '}'
"if"              return 'IF'
"else"          return 'ELSE'
"function"        return 'FUNC'
"echo"            return 'ECHO'
"let"             return 'LET'
"int"             return 'INT'
"string"          return 'STRING'
"null"            return 'NULL'
"return"          return 'RET'
"break"          return 'BREAK'
"continue"          return 'CONTINUE'
"loop"            return 'LOOP'
"ejecutar"      return 'EXECUTE'
"switch" return 'SWITCH'
"case" return 'CASE'
"default" return 'DEFAULT'
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
  import Return from './Statements/Return.js'
  import Switch from './Statements/Switch.js'
  import CaseStmt from './Statements/Case.js'
  import VarDeclarationStmt from './Statements/VarDeclaration.js'
  import VarAssignmentStmt from './Statements/VarAssignment.js'
  import VarLookUpExpr from './Expressions/VarLookUp.js'
  import BlockStmt from './Statements/Block.js'
  import IfStmt from './Statements/If.js'
  import FunctionDefine from './Statements/FuncDeclaration.js'
  import Loop from './Statements/Loop.js'
  import CallFunction from './Expressions/CallFunction.js';
  import ArgumentContainer from './Context/ArgumentContainer.js';
  import ParamContainer from './Context/ParamContainer.js';
  import Break from './Statements/Break.js';
  import Execute from './Statements/Execute.js';
  import Continue from './Statements/Continue.js';

  
  const errors = []
%}


%left '==' '>'
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
  | conditional
  | declare_func
  | return
  | loop
  | execute
  | selector
  | BREAK { $$ = new Break(@1); }
  | CONTINUE { $$=  new Continue(@1); }
;

execute: EXECUTE IDENTIFIER '(' list_args ')' {$$ = new Execute($2, $4, @1);}
| IDENTIFIER '('  ')' {$$ = new Execute($2, null, @1);}
;

block
  : '{' statements '}'
  { $$ = new BlockStmt($2, @1)}
;

loop
  : LOOP '{' statements '}'
  { $$ = new Loop($3, @1)}
;

selector: SWITCH '(' expression ')'  '{' cases '}'
{ $$ = new Switch($3, $6, null, @1); }
| SWITCH '(' expression ')'  '{' cases '}' DEFAULT '{' statements '}'
{ $$ = new Switch($3, $6, $10, @1); }
;

cases: cases case_stmt {$1.push($2)
    $$ = $1}
| case_stmt {$$ = [$1]}
;

case_stmt: CASE '(' expression ')'  '{' statements '}'
{ $$ = new CaseStmt($3, $6, @1); }
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

return: RET expression { $$ = new Return($2, @1)};

conditional : IF '(' expression ')'  '{' statements '}'
{ $$ = new IfStmt($3, $6, null, @1)}
| IF '(' expression ')'  '{' statements '}' ELSE '{' statements '}'
{ $$ = new IfStmt($3, $6, $10, @1)}
;

parameter: IDENTIFIER ':' type
{ $$ = new ParamContainer($1, $3);}
;


list_params: list_params ',' parameter
{ $1.push($3)
  $$ = $1
}
| parameter
{ $$ = [$1]}
;

declare_func: FUNC IDENTIFIER '(' list_params ')' '{' statements '}'
{$$ = new FunctionDefine($2, $4, $7, @1)}
| FUNC IDENTIFIER '(' ')' '{' statements '}'
;

argument: IDENTIFIER '=' expression
{ $$ = new ArgumentContainer($1, $3)}
;

list_args: list_args ',' argument 
{
  $1.push($3)
  $$ = $1
}
| argument
{$$ = [$1]}
;

call_func: IDENTIFIER '(' list_args ')'
{$$ = new CallFunction($1, $3, @1)}
| IDENTIFIER '('  ')'
;


expression
  : arithmetic
  | relational
  | call_func
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
  | expression '>' expression
  { $$ = new BinaryExpr($1, $2, $3, @1)}
;

literal
  : INT_LITERAL
  { $$ = new LiteralExpr($1, 'INT', @1) }
  | STRING_LITERAL
  { $$ = new LiteralExpr($1, 'STRING', @1) }
  | BOOL_LITERAL
  { $$ = new LiteralExpr($1, 'BOOL', @1) }
  | NULL
  { $$ = new LiteralExpr($1, 'NULL', @1) }
;

type
  : INT
  | STRING
  | NULL
;
