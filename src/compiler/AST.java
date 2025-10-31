package compiler;

import compiler.lib.BaseASTVisitor;
import compiler.lib.Node;

public class AST {
	public static class ProgNode implements Node {
		Node exp;
		ProgNode(Node e) { exp=e; }

		@Override
		public <S> S accept(BaseASTVisitor<S> visitor) {
			return visitor.visitNode(this);
		}
	}

	public static class PlusNode implements Node {
		Node left;
		Node right;
		PlusNode(Node l, Node r) { left=l; right=r; }

        @Override
        public <S> S accept(BaseASTVisitor<S> visitor) {
            return visitor.visitNode(this);
        }
	}

	public static class TimesNode implements Node {
		Node left;
		Node right;
		TimesNode(Node l, Node r) { left=l; right=r; }

        @Override
        public <S> S accept(BaseASTVisitor<S> visitor) {
            return visitor.visitNode(this);
        }
	}

	public static class IntNode implements Node {
		Integer val;
		IntNode(Integer n) { val=n; }

        @Override
        public <S> S accept(BaseASTVisitor<S> visitor) {
            return visitor.visitNode(this);
        }
	}

    public static class EqualNode implements Node {
        Node left;
        Node right;
        EqualNode(Node l, Node r) { left=l; right=r; }

        @Override
        public <S> S accept(BaseASTVisitor<S> visitor) {
            return visitor.visitNode(this);
        }
    }

    public static class BoolNode implements Node {
        Boolean val;
        BoolNode(Boolean bool) { val=bool; }

        @Override
        public <S> S accept(BaseASTVisitor<S> visitor) {
            return visitor.visitNode(this);
        }
    }

    public static class IfNode implements Node {
        Node condition;
        Node thenNode;
        Node elseNode;
        IfNode(Node c, Node t, Node e) { condition=c; thenNode=t; elseNode=e; }

        @Override
        public <S> S accept(BaseASTVisitor<S> visitor) {
            return visitor.visitNode(this);
        }
    }

    public static class PrintNode implements Node {
        Node exp;
        PrintNode(Node e) { exp=e; }

        @Override
        public <S> S accept(BaseASTVisitor<S> visitor) {
            return visitor.visitNode(this);
        }
    }

}






