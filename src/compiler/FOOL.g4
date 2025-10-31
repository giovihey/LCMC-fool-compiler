grammar FOOL;

@lexer::members {
int lexicalErrors=0;
}

 
/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
 
prog    : exp SEMIC EOF ;
 
exp     : exp TIMES exp                 #times
        | exp PLUS exp                  #plus
        | exp EQ exp                    #eq       //
        | LPAR exp RPAR                 #pars
    	| MINUS? NUM                    #integer        
	    | TRUE                          #true           //
	    | FALSE                         #false          //
	    | IF exp THEN CLPAR exp CRPAR 
	             ELSE CLPAR exp CRPAR   #if             //
	    | PRINT LPAR exp RPAR           #print          //
        ;  		
  		
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

SEMIC	: ';' ;
EQ	    : '==' ;
PLUS	: '+' ;
MINUS   : '-' ;
TIMES	: '*' ;
TRUE	: 'true' ;
FALSE	: 'false' ;
LPAR 	: '(' ;
RPAR	: ')' ;
CLPAR 	: '{' ;
CRPAR	: '}' ;
IF 	    : 'if' ;
THEN 	: 'then' ;
ELSE 	: 'else' ;
PRINT	: 'print' ; 
NUM     : '0' | ('1'..'9')('0'..'9')* ;
 
WHITESP : (' '|'\t'|'\n'|'\r')+ -> channel(HIDDEN) ;
COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ; //non-greedy *

ERR	    : . { System.out.println("Invalid char: "+ getText()); lexicalErrors++; } -> channel(HIDDEN); 
