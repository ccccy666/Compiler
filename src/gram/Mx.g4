grammar Mx;

program: (funcDef | classDef | varDef)* EOF;

funcDef: returnType ID '(' parameterList? ')' '{' suite '}';
returnType: type | Void;
parameterList: (type ID) (Comma type ID)*;
suite: statement*;

classDef: Class ID '{' (varDef | classBuild | funcDef)* '}' Semi;
classBuild: ID '(' ')' '{' suite '}';
varDef: type varDefUnit (Comma varDefUnit)* Semi;
varDefUnit: ID (Assign expr)?;

type: typeName ('[' ']')*;
typeName: baseType | ID;
baseType: Int 
        | Bool 
        | String;

statement:
    '{' suite '}'
  |   varDef
  |   ifStmt 
  |   whileStmt 
  |   forStmt
  |   breakStmt 
  |   continueStmt 
  |   returnStmt
  |   exprStmt;

ifStmt: If '(' expr ')' statement (Else statement)?;
whileStmt: While '(' expr ')' statement;
forStmt: For '(' forInit exprStmt expr? ')' statement;
forInit: varDef | exprStmt;

breakStmt: Break Semi;
continueStmt: Continue Semi;
returnStmt: Return expr? Semi;

exprStmt: expr? Semi;
unit:'[' expr? ']';
expr:
    '(' expr ')'                                      #parenExpr
  |   New typeName (unit)* ('(' ')')?           #newExpr
  // | New typeName ('(' ')')?                           #newClassExpr
  |   expr op=Dot ID                         #memberExpr
  |   expr ('[' expr ']') +                                #indexExpr 
  |   expr '(' exprList? ')'                            #funcExpr
  |   lv=expr op=('++'|'--') #sufExpr
  |   <assoc=right> op=('++'|'--'|Not|Tilde|Sub) rv=expr #preExpr
//   | op=(SelfAdd | SelfSub) expr                       #preAddExpr
//   | <assoc=right> expr op=(SelfAdd | SelfSub)         #unaryExpr
//   | <assoc=right> op=(LNot | BNot | Add | Sub) expr   #unaryExpr
  |   expr op=(Mul | Div | Mod) expr                    #binaryExpr 
  |   expr op=(Add | Sub) expr                          #binaryExpr                                              
  |   expr op=(LeftShift | RightShift) expr                    #binaryExpr 
  |   expr op=(Less | Greater | LessEqual | GreaterEqual) expr    #binaryExpr
  |   expr op=(Equal | NotEqual) expr                    #binaryExpr
  |   expr op=And expr                                 #binaryExpr
  |   expr op=Caret expr                                 #binaryExpr
  |   expr op=Or expr                                  #binaryExpr
  |   expr op=AndAnd expr                                 #binaryExpr
  |   expr op=OrOr expr                                  #binaryExpr
  |   <assoc=right> lv=expr op1='?' mv=expr op2=':' rv=expr #ternaryExpr

  |   <assoc=right> expr op=Assign expr                 #assignExpr  
  |   single                                           #atomExpr
  ;  

// may have problem 
single:
    IntConst 
  | StringConst 
  | True 
  | False 
  | Null
  | ID
  | This
  ;

exprList: expr (Comma expr)*;
Add: '+';
Sub: '-';
Mul: '*';
Div: '/';
Mod: '%';
Less : '<';
Greater : '>';
LessEqual : '<=';
GreaterEqual : '>=';
NotEqual : '!=';
Equal : '==';



AndAnd : '&&';
OrOr : '||';
Not : '!';



LeftShift : '<<';
RightShift : '>>';
And : '&';
Or : '|';
Caret : '^';
Tilde : '~';



Assign: '=';

SelfAdd: '++';
SelfSub: '--';

Dot: '.';
LeftBracket : '[';
RightBracket : ']';
LeftParen : '(';
RightParen : ')';
Semi: ';';
Comma: ',';
LeftBrace : '{';
RightBrace : '}';
Quote: '"';


Void: 'void';
Bool: 'bool';
Int: 'int';
String: 'string';
New: 'new';
Class: 'class';
Null: 'null';
True: 'true';
False: 'false';
This: 'this';
If: 'if';
Else: 'else';
For: 'for';
While: 'while';
Break: 'break';
Continue: 'continue';
Return: 'return';

ID: [A-Za-z][0-9A-Za-z_]*;

IntConst: [1-9][0-9]* | '0';
StringConst: Quote (ESC)*? Quote;
fragment ESC: '\\n' | '\\\\' | '\\"' | [ -~];

WhiteSpace: [ \t\r\n]+ -> skip;
Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;
CommentPara: '/*' .*? '*/' -> skip;   
CommentLine: '//' ~[\r\n]* -> skip;

