// Generated from C:/Users/giovi/IdeaProjects/FOOL/src/compiler/FOOL.g4 by ANTLR 4.13.2
package compiler;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FOOLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMIC=1, EQ=2, PLUS=3, MINUS=4, TIMES=5, TRUE=6, FALSE=7, LPAR=8, RPAR=9, 
		CLPAR=10, CRPAR=11, IF=12, THEN=13, ELSE=14, PRINT=15, NUM=16, WHITESP=17, 
		COMMENT=18, ERR=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SEMIC", "EQ", "PLUS", "MINUS", "TIMES", "TRUE", "FALSE", "LPAR", "RPAR", 
			"CLPAR", "CRPAR", "IF", "THEN", "ELSE", "PRINT", "NUM", "WHITESP", "COMMENT", 
			"ERR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'=='", "'+'", "'-'", "'*'", "'true'", "'false'", "'('", 
			"')'", "'{'", "'}'", "'if'", "'then'", "'else'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMIC", "EQ", "PLUS", "MINUS", "TIMES", "TRUE", "FALSE", "LPAR", 
			"RPAR", "CLPAR", "CRPAR", "IF", "THEN", "ELSE", "PRINT", "NUM", "WHITESP", 
			"COMMENT", "ERR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	int lexicalErrors=0;


	public FOOLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 18:
			ERR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.out.println("Invalid char: "+ getText()); lexicalErrors++; 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000\u0013|\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\\\b\u000f\n\u000f\f\u000f_\t\u000f\u0003\u000fa\b\u000f"+
		"\u0001\u0010\u0004\u0010d\b\u0010\u000b\u0010\f\u0010e\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011n\b"+
		"\u0011\n\u0011\f\u0011q\t\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001o\u0000\u0013\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\u0001"+
		"\u0000\u0001\u0003\u0000\t\n\r\r  \u007f\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0001\'\u0001\u0000"+
		"\u0000\u0000\u0003)\u0001\u0000\u0000\u0000\u0005,\u0001\u0000\u0000\u0000"+
		"\u0007.\u0001\u0000\u0000\u0000\t0\u0001\u0000\u0000\u0000\u000b2\u0001"+
		"\u0000\u0000\u0000\r7\u0001\u0000\u0000\u0000\u000f=\u0001\u0000\u0000"+
		"\u0000\u0011?\u0001\u0000\u0000\u0000\u0013A\u0001\u0000\u0000\u0000\u0015"+
		"C\u0001\u0000\u0000\u0000\u0017E\u0001\u0000\u0000\u0000\u0019H\u0001"+
		"\u0000\u0000\u0000\u001bM\u0001\u0000\u0000\u0000\u001dR\u0001\u0000\u0000"+
		"\u0000\u001f`\u0001\u0000\u0000\u0000!c\u0001\u0000\u0000\u0000#i\u0001"+
		"\u0000\u0000\u0000%w\u0001\u0000\u0000\u0000\'(\u0005;\u0000\u0000(\u0002"+
		"\u0001\u0000\u0000\u0000)*\u0005=\u0000\u0000*+\u0005=\u0000\u0000+\u0004"+
		"\u0001\u0000\u0000\u0000,-\u0005+\u0000\u0000-\u0006\u0001\u0000\u0000"+
		"\u0000./\u0005-\u0000\u0000/\b\u0001\u0000\u0000\u000001\u0005*\u0000"+
		"\u00001\n\u0001\u0000\u0000\u000023\u0005t\u0000\u000034\u0005r\u0000"+
		"\u000045\u0005u\u0000\u000056\u0005e\u0000\u00006\f\u0001\u0000\u0000"+
		"\u000078\u0005f\u0000\u000089\u0005a\u0000\u00009:\u0005l\u0000\u0000"+
		":;\u0005s\u0000\u0000;<\u0005e\u0000\u0000<\u000e\u0001\u0000\u0000\u0000"+
		"=>\u0005(\u0000\u0000>\u0010\u0001\u0000\u0000\u0000?@\u0005)\u0000\u0000"+
		"@\u0012\u0001\u0000\u0000\u0000AB\u0005{\u0000\u0000B\u0014\u0001\u0000"+
		"\u0000\u0000CD\u0005}\u0000\u0000D\u0016\u0001\u0000\u0000\u0000EF\u0005"+
		"i\u0000\u0000FG\u0005f\u0000\u0000G\u0018\u0001\u0000\u0000\u0000HI\u0005"+
		"t\u0000\u0000IJ\u0005h\u0000\u0000JK\u0005e\u0000\u0000KL\u0005n\u0000"+
		"\u0000L\u001a\u0001\u0000\u0000\u0000MN\u0005e\u0000\u0000NO\u0005l\u0000"+
		"\u0000OP\u0005s\u0000\u0000PQ\u0005e\u0000\u0000Q\u001c\u0001\u0000\u0000"+
		"\u0000RS\u0005p\u0000\u0000ST\u0005r\u0000\u0000TU\u0005i\u0000\u0000"+
		"UV\u0005n\u0000\u0000VW\u0005t\u0000\u0000W\u001e\u0001\u0000\u0000\u0000"+
		"Xa\u00050\u0000\u0000Y]\u000219\u0000Z\\\u000209\u0000[Z\u0001\u0000\u0000"+
		"\u0000\\_\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000"+
		"\u0000\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000`X\u0001"+
		"\u0000\u0000\u0000`Y\u0001\u0000\u0000\u0000a \u0001\u0000\u0000\u0000"+
		"bd\u0007\u0000\u0000\u0000cb\u0001\u0000\u0000\u0000de\u0001\u0000\u0000"+
		"\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000"+
		"\u0000\u0000gh\u0006\u0010\u0000\u0000h\"\u0001\u0000\u0000\u0000ij\u0005"+
		"/\u0000\u0000jk\u0005*\u0000\u0000ko\u0001\u0000\u0000\u0000ln\t\u0000"+
		"\u0000\u0000ml\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000\u0000op\u0001"+
		"\u0000\u0000\u0000om\u0001\u0000\u0000\u0000pr\u0001\u0000\u0000\u0000"+
		"qo\u0001\u0000\u0000\u0000rs\u0005*\u0000\u0000st\u0005/\u0000\u0000t"+
		"u\u0001\u0000\u0000\u0000uv\u0006\u0011\u0000\u0000v$\u0001\u0000\u0000"+
		"\u0000wx\t\u0000\u0000\u0000xy\u0006\u0012\u0001\u0000yz\u0001\u0000\u0000"+
		"\u0000z{\u0006\u0012\u0000\u0000{&\u0001\u0000\u0000\u0000\u0005\u0000"+
		"]`eo\u0002\u0000\u0001\u0000\u0001\u0012\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}