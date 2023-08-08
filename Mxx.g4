grammar Mxx;

program:   ((classdef ';')|funcdef|(vardef ';'))+ EOF;
type 
    : Int 
    | Void
    //| String 
    | Bool 
    | Int listSuf+
    //| String listSuf+
    | Bool listSuf+
    | Identifier
    | Identifier listSuf+;//identifier for class name specifically

listSuf: (LeftBracket  RightBracket);

classdef: Class Identifier suite ;
funcdef: type Identifier ('(' parameterList? ')') suite;

parameterList: parameter (',' parameter)*;

parameter: type Identifier;

exprList:expr (',' expr)* ;

vardef: type singleAssign  (',' singleAssign )* ;

singleAssign:Identifier (Assign expr)? ;

suite: '{' stmt* '}';

constructordef: Identifier '('')' suite;

stmt   
    :   expr ';'
    |   vardef ';'
    |   If '(' Cond=expr ')' (Ifsuite=suite|Ifstmt=stmt) (Else (Elsesuite=suite|Elsestmt=stmt))?
    |   For'('(InitVdf=vardef|InitCond=expr)?';'EndCond=expr?';' Change=expr? ')' (LoopSuite=suite|LoopStmt=stmt)
    |   While'(' (Cond=expr) ')' (LoopSuite=suite|LoopStmt=stmt)
    |   Continue ';'
    |   Break ';'
    |   Return (expr)? ';'
    |   suite
    |   funcdef
    |   constructordef
    |   ';';

//newop:  New ((type (LeftBracket (expr) RightBracket)*listSuf+(type (LeftBracket (expr) RightBracket)+))) #Wrongnew
//        | New (((type (LeftBracket (expr) RightBracket)*listSuf*?))) #CorrectNew
//        | New type '('')'#CorrectNew;

expr
    :   New ((type (LeftBracket (expr) RightBracket)*listSuf+((LeftBracket (expr) RightBracket)+))) #Wrongnew
        | New type '('')'#CorrectNew
        | New (((type (LeftBracket (expr) RightBracket)*listSuf*?))) #CorrectNew
    |   expr '(' exprList? ')' #Func_call_Expr
    |   expr Dot Identifier     #Member_Expr
    |   '['(And)?']'('('parameterList?')')? To suite #Lambda_Func  
    |   atomExpr               #Recur_Expr
    |   '(' expr ')'       #Recur_Expr
    |   lv=expr op=('++'|'--') #sufExpr
    |   expr (LeftBracket expr RightBracket)+ #IndexExpr
    |   <assoc=right> op=('++'|'--'|Not|Tilde|Minus) rv=expr #preExpr
    |   lv=expr op=(Mod|Mul|Div) rv=expr         #binaryExpr
    |   lv=expr op=(Plus|Minus) rv=expr  #binaryExpr
    |   lv=expr op=(RightShift|LeftShift) rv=expr  #binaryExpr
    |   lv=expr op=(Greater|GreaterEqual|Less|LessEqual) rv=expr #binaryExpr
    |   lv=expr op=(Equal|NotEqual) rv=expr #binaryExpr
    |   lv=expr op=And rv=expr   #binaryExpr
    |   lv=expr op=Caret rv=expr #binaryExpr
    |   lv=expr op=Or rv=expr   #binaryExpr
    |   lv=expr op=AndAnd rv=expr   #binaryExpr
    |   lv=expr op=OrOr rv=expr #binaryExpr
    |  <assoc=right> lv=expr op=Assign rv=expr #assignExpr//右结合
    
    ;
atomExpr
    :   ConstString
    |   Integer
    |   BoolConst
    |   Identifier
    |   This
    |   Null;
Integer:(DecimalInteger);
fragment DecimalInteger
    : [1-9] [0-9]*
    | '0'
    ;
BoolConst: (Tru|Fals);
ConstString: '"' ((~'\\')|ESC)*? '"';
fragment ESC: '\\\\'|'\\n'|'\\"';
//value type
Int : 'int';
//String: 'string'; //No string from now on!
Bool: 'bool';
Void: 'void';
//keys
If : 'if';
Else : 'else';
Return : 'return';
Class: 'class';
Tru: 'true';
Fals: 'false';
This: 'this';
For: 'for';
While: 'while';
Break: 'break';
Continue: 'continue';
New: 'new';
Null: 'null';
//symbols
LeftParen : '(';
RightParen : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';
To: '->';//Before Minus and Greater to avoid deprecation

Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
LeftShift : '<<';
RightShift : '>>';

Plus : '+';
Minus : '-';
Mul: '*';
Div: '/';
Mod: '%';

AndAnd : '&&';
OrOr : '||';
Caret : '^';
And : '&';
Or : '|';
Not : '!';
Tilde : '~';

Question : '?';
Colon : ':';
Semi : ';';
Comma : ',';

Assign : '=';
Equal : '==';
NotEqual : '!=';
Dot: '.';

Identifier
    : [a-zA-Z] [a-zA-Z_0-9]*
    ;

Whitespace
    :   [ \t\r\n]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;