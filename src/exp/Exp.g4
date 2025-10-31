grammar Exp;

@lexer::members {
public int lexicalErrors=0;
}

// PARSER RULES

prog : exp EOF {System.out.println("Parsing finished!");} ;
 
exp     : exp (TIMES | DIV) exp  #expProd1 // <assoc=right>
        | exp (PLUS | MINUS) exp   #expProd2 // <assoc=right>
        | LPAR exp RPAR  #expProd3
        | MINUS? NUM            #expProd4
        ;
          
// LEXER RULES

PLUS	: '+' ;
TIMES	: '*' ;
MINUS	: '-' ;
DIV	    : '/' ;
LPAR    : '(' ;
RPAR    : ')' ;
NUM     : '0' | ('1'..'9')('0'..'9')* ;

WHITESP	: (' '|'\t'|'\n'|'\r')+ -> channel(HIDDEN) ;
COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ;

ERR 	: . {System.out.println("Invalid char: "+ getText()); lexicalErrors++;} -> channel(HIDDEN) ; 



