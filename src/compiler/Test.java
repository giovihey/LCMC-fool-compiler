package compiler;

import compiler.lib.Node;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception {
    			
        String fileName = "esempio.fool";

        CharStream chars = CharStreams.fromFileName(fileName);
        FOOLLexer lexer = new FOOLLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FOOLParser parser = new FOOLParser(tokens);
                
        ParseTree st = parser.prog();

        System.out.println("You had: "+lexer.lexicalErrors+" lexical errors and "+
        	parser.getNumberOfSyntaxErrors()+" syntax errors");

        if (lexer.lexicalErrors+parser.getNumberOfSyntaxErrors() > 0) System.exit(1);   
        
        System.out.println("Generating AST");
        
        ASTGenerationSTVisitor astGenVisitor = new ASTGenerationSTVisitor();
        Node ast = astGenVisitor.visit(st);

        System.out.println("Visualizing AST...");

        new PrintASTVisitor().visit(ast);

        System.out.println("Calculating program value...");
        System.out.println("Program value is: "+new CalcASTVisitor().visit(ast));
            
    }
}
