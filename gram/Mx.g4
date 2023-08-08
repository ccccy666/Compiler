grammar Mx;
@header { package gram; }
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
//   | '[' BAnd? ']' '(' parameterList? ')' Arrow '{' suite '}' '(' exprList? ')'
//                                                       #lambdaExpr
  |   New typeName (unit)* ('(' ')')?           #newExpr
  // | New typeName ('(' ')')?                           #newClassExpr
  |   expr op=Member ID                         #memberExpr
  |   expr ('[' expr ']') +                                #indexExpr 
  |   expr '(' exprList? ')'                            #funcExpr
  |   lv=expr op=('++'|'--') #sufExpr
  |   <assoc=right> op=('++'|'--'|LNot|BNot|Sub) rv=expr #preExpr
//   | op=(SelfAdd | SelfSub) expr                       #preAddExpr
//   | <assoc=right> expr op=(SelfAdd | SelfSub)         #unaryExpr
//   | <assoc=right> op=(LNot | BNot | Add | Sub) expr   #unaryExpr
  |   expr op=(Mul | Div | Mod) expr                    #binaryExpr 
  |   expr op=(Add | Sub) expr                          #binaryExpr                                              
  |   expr op=(LShift | RShift) expr                    #binaryExpr 
  |   expr op=(LThan | GThan | LEqual | GEqual) expr    #binaryExpr
  |   expr op=(EEqual | NEqual) expr                    #binaryExpr
  |   expr op=BAnd expr                                 #binaryExpr
  |   expr op=BXor expr                                 #binaryExpr
  |   expr op=BOr expr                                  #binaryExpr
  |   expr op=LAnd expr                                 #binaryExpr
  |   expr op=LOr expr                                  #binaryExpr
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

GThan: '>';
LThan: '<';
GEqual: '>=';
LEqual: '<=';
NEqual: '!=';
EEqual: '==';

LAnd: '&&';
LOr: '||';
LNot: '!';

RShift: '>>';
LShift: '<<';
BAnd: '&';
BOr: '|';
BXor: '^';
BNot: '~';

Assign: '=';

SelfAdd: '++';
SelfSub: '--';

Member: '.';
LBracket: '[';
RBracket: ']';
LParen: '(';
RParen: ')';

Semi: ';';
Comma: ',';
LBrace: '{';
RBrace: '}';

Quote: '"';
Arrow: '->';

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
StringConst: Quote (PChar)*? Quote;
fragment PChar: '\\n' | '\\\\' | '\\"' | [ -~];

WhiteSpace: [ \t\r\n]+ -> skip;
Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;
CommentPara: '/*' .*? '*/' -> skip;   
CommentLine: '//' ~[\r\n]* -> skip;

// program:   ((classdef ';')|funcdef|(vardef ';'))+ EOF;
// type 
//     : Int 
//     | Void
//     //| String 
//     | Bool 
//     | Int listSuf+
//     //| String listSuf+
//     | Bool listSuf+
//     | ID
//     | ID listSuf+;//ID for class name specifically

// listSuf: (LeftBracket  RightBracket);

// classdef: Class ID content ;
// funcdef: type ID ('(' parameters? ')') content;

// parameters: parameter (',' parameter)*;

// parameter: type ID;

// exprList:expr (',' expr)* ;

// vardef: type variable  (',' variable )* ;

// variable:ID (Assign expr)? ;

// content: '{' stmt* '}';

// constructordef: ID '('')' content;

// stmt   
//     :   expr ';'
//     |   vardef ';'
//     |   If '(' Cond=expr ')' (Ifcontent=content|Ifstmt=stmt) (Else (Elsecontent=content|Elsestmt=stmt))?
//     |   For'('(InitVdf=vardef|InitCond=expr)?';'EndCond=expr?';' Change=expr? ')' (Loopcontent=content|LoopStmt=stmt)
//     |   While'(' (Cond=expr) ')' (Loopcontent=content|LoopStmt=stmt)
//     |   Continue ';'
//     |   Break ';'
//     |   Return (expr)? ';'
//     |   content
//     |   funcdef
//     |   constructordef
//     |   ';';

// //newop:  New ((type (LeftBracket (expr) RightBracket)*listSuf+(type (LeftBracket (expr) RightBracket)+))) #Wrongnew
// //        | New (((type (LeftBracket (expr) RightBracket)*listSuf*?))) #CorrectNew
// //        | New type '('')'#CorrectNew;

// expr
//     :    
       
//        '(' expr ')'       #Recur_Expr
//     |    New (((type (LeftBracket (expr) RightBracket)*listSuf*?))) ('(' ')')#Newexpr
//     |   expr Dot ID     #Member_Expr
//     |   expr (LeftBracket expr RightBracket)+ #IndexExpr
//     |   expr '(' exprList? ')' #Func_call_Expr
//     |   lv=expr op=('++'|'--') #sufExpr
//     |   <assoc=right> op=('++'|'--'|Not|Tilde|Sub) rv=expr #preExpr
//     |   lv=expr op=(Mod|Mul|Div) rv=expr         #binaryExpr
//     |   lv=expr op=(Add|Sub) rv=expr  #binaryExpr
//     |   lv=expr op=(RightShift|LeftShift) rv=expr  #binaryExpr
//     |   lv=expr op=(Greater|GreaterEqual|Less|LessEqual) rv=expr #binaryExpr
//     |   lv=expr op=(Equal|NotEqual) rv=expr #binaryExpr
//     |   lv=expr op=And rv=expr   #binaryExpr
//     |   lv=expr op=Caret rv=expr #binaryExpr
//     |   lv=expr op=Or rv=expr   #binaryExpr
//     |   lv=expr op=AndAnd rv=expr   #binaryExpr
//     |   lv=expr op=OrOr rv=expr #binaryExpr
//     |  <assoc=right> lv=expr op1='?' mv=expr op2=':' rv=expr #ternaryexpr
//     |  <assoc=right> lv=expr op=Assign rv=expr #assignExpr//右结合
//     |   atomExpr               #BasicExpr
//     // |New ((type (LeftBracket (expr) RightBracket)*listSuf+((LeftBracket (expr) RightBracket)+))) #Wrongnew
//     //     | New type '('')'#CorrectNew
    
    
    
//     // |   '['(And)?']'('('parameters?')')? To content #Lambda_Func 
//     ;
// atomExpr
//     :   ConstString
//     |   Integer
//     |   BoolConst
//     |   ID
//     |   This
//     |   Null;
// Integer:[1-9] [0-9]*
//     | '0'
//     ;

// fragment DecimalInteger
//     : [1-9] [0-9]*
//     | '0'
//     ;
// BoolConst: (Tru|Fals);
// ConstString: '"' ((~'\\')|ESC)*? '"';
// fragment ESC: '\\\\'|'\\n'|'\\"';
// //value type
// Int : 'int';
// //String: 'string'; //No string from now on!
// Bool: 'bool';
// Void: 'void';
// //keys
// If : 'if';
// Else : 'else';
// Return : 'return';
// Class: 'class';
// Tru: 'true';
// Fals: 'false';
// This: 'this';
// For: 'for';
// While: 'while';
// Break: 'break';
// Continue: 'continue';
// New: 'new';
// Null: 'null';
// //symbols
// LeftParen : '(';
// RightParen : ')';
// LeftBracket : '[';
// RightBracket : ']';
// LeftBrace : '{';
// RightBrace : '}';
// To: '->';//Before Sub and Greater to avoid deprecation

// Less : '<';
// LessEqual : '<=';
// Greater : '>';
// GreaterEqual : '>=';
// LeftShift : '<<';
// RightShift : '>>';

// Add : '+';
// Sub : '-';
// Mul: '*';
// Div: '/';
// Mod: '%';

// AndAnd : '&&';
// OrOr : '||';
// Caret : '^';
// And : '&';
// Or : '|';
// Not : '!';
// Tilde : '~';

// Question : '?';
// Colon : ':';
// Semi : ';';
// Comma : ',';

// Assign : '=';
// Equal : '==';
// NotEqual : '!=';
// Dot: '.';

// ID
//     : [a-zA-Z] [a-zA-Z_0-9]*
//     ;

// Whitespace
//     :   [ \t\r\n]+
//         -> skip
//     ;

// Newline
//     :   (   '\r' '\n'?
//         |   '\n'
//         )
//         -> skip
//     ;

// BlockComment
//     :   '/*' .*? '*/'
//         -> skip
//     ;

// LineComment
//     :   '//' ~[\r\n]*
//         -> skip
//     ;