package compiler;

import compiler.AST.*;
import compiler.lib.*;

import java.util.Objects;

public class CalcASTVisitor extends BaseASTVisitor<Integer> {

	@Override
	public Integer visitNode(ProgNode n) {
//	   if (print) printNode(n);
	   return visit(n.exp);
	}

	@Override
	public Integer visitNode(PlusNode n) {
//		if (print) printNode(n);
	    return visit(n.left) + visit(n.right);
	}

	@Override
	public Integer visitNode(TimesNode n) {
//		if (print) printNode(n);
		return visit(n.left) * visit(n.right);
	}

	@Override
	public Integer visitNode(IntNode n) {
//		if (print) printNode(n,n.val.toString());
        return n.val;
	}

    @Override
    public Integer visitNode(EqualNode n) {
        return (Objects.equals(visit(n.left), visit(n.right))) ? 1 : 0;
    }

    @Override
    public Integer visitNode(BoolNode n) {
        return (n.val) ? 1 : 0;
    }

    @Override
    public Integer visitNode(PrintNode n) {
        return visit(n.exp);
    }

    @Override
    public Integer visitNode(IfNode n) {
        return (visit(n.condition) == 1 ) ? visit(n.thenNode) : visit(n.elseNode);
    }
}

//    CalcASTVisitor() {}
//    CalcASTVisitor(boolean debug) { super(debug); } // enables print for debugging

