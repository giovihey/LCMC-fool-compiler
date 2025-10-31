// Generated from C:/Users/giovi/IdeaProjects/FOOL/src/exp/Exp.g4 by ANTLR 4.13.2
package exp;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExpParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(ExpParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expProd2}
	 * labeled alternative in {@link ExpParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpProd2(ExpParser.ExpProd2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code expProd3}
	 * labeled alternative in {@link ExpParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpProd3(ExpParser.ExpProd3Context ctx);
	/**
	 * Visit a parse tree produced by the {@code expProd1}
	 * labeled alternative in {@link ExpParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpProd1(ExpParser.ExpProd1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code expProd4}
	 * labeled alternative in {@link ExpParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpProd4(ExpParser.ExpProd4Context ctx);
}