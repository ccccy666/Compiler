// Generated from /home/cyf/compiler/Mxx.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, Integer=3, BoolConst=4, ConstString=5, Int=6, Bool=7, 
		Void=8, If=9, Else=10, Return=11, Class=12, Tru=13, Fals=14, This=15, 
		For=16, While=17, Break=18, Continue=19, New=20, Null=21, LeftParen=22, 
		RightParen=23, LeftBracket=24, RightBracket=25, LeftBrace=26, RightBrace=27, 
		To=28, Less=29, LessEqual=30, Greater=31, GreaterEqual=32, LeftShift=33, 
		RightShift=34, Plus=35, Minus=36, Mul=37, Div=38, Mod=39, AndAnd=40, OrOr=41, 
		Caret=42, And=43, Or=44, Not=45, Tilde=46, Question=47, Colon=48, Semi=49, 
		Comma=50, Assign=51, Equal=52, NotEqual=53, Dot=54, Identifier=55, Whitespace=56, 
		Newline=57, BlockComment=58, LineComment=59;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "Integer", "DecimalInteger", "BoolConst", "ConstString", 
			"ESC", "Int", "Bool", "Void", "If", "Else", "Return", "Class", "Tru", 
			"Fals", "This", "For", "While", "Break", "Continue", "New", "Null", "LeftParen", 
			"RightParen", "LeftBracket", "RightBracket", "LeftBrace", "RightBrace", 
			"To", "Less", "LessEqual", "Greater", "GreaterEqual", "LeftShift", "RightShift", 
			"Plus", "Minus", "Mul", "Div", "Mod", "AndAnd", "OrOr", "Caret", "And", 
			"Or", "Not", "Tilde", "Question", "Colon", "Semi", "Comma", "Assign", 
			"Equal", "NotEqual", "Dot", "Identifier", "Whitespace", "Newline", "BlockComment", 
			"LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'++'", "'--'", null, null, null, "'int'", "'bool'", "'void'", 
			"'if'", "'else'", "'return'", "'class'", "'true'", "'false'", "'this'", 
			"'for'", "'while'", "'break'", "'continue'", "'new'", "'null'", "'('", 
			"')'", "'['", "']'", "'{'", "'}'", "'->'", "'<'", "'<='", "'>'", "'>='", 
			"'<<'", "'>>'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&&'", "'||'", "'^'", 
			"'&'", "'|'", "'!'", "'~'", "'?'", "':'", "';'", "','", "'='", "'=='", 
			"'!='", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "Integer", "BoolConst", "ConstString", "Int", "Bool", 
			"Void", "If", "Else", "Return", "Class", "Tru", "Fals", "This", "For", 
			"While", "Break", "Continue", "New", "Null", "LeftParen", "RightParen", 
			"LeftBracket", "RightBracket", "LeftBrace", "RightBrace", "To", "Less", 
			"LessEqual", "Greater", "GreaterEqual", "LeftShift", "RightShift", "Plus", 
			"Minus", "Mul", "Div", "Mod", "AndAnd", "OrOr", "Caret", "And", "Or", 
			"Not", "Tilde", "Question", "Colon", "Semi", "Comma", "Assign", "Equal", 
			"NotEqual", "Dot", "Identifier", "Whitespace", "Newline", "BlockComment", 
			"LineComment"
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


	public MxxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mxx.g4"; }

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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2=\u0175\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\7\5\u0088\n\5\f\5\16\5"+
		"\u008b\13\5\3\5\5\5\u008e\n\5\3\6\3\6\5\6\u0092\n\6\3\7\3\7\3\7\7\7\u0097"+
		"\n\7\f\7\16\7\u009a\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a4\n\b"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3"+
		" \3!\3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3"+
		")\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3"+
		"\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\3\67\38\38\38\3"+
		"9\39\3:\3:\7:\u0148\n:\f:\16:\u014b\13:\3;\6;\u014e\n;\r;\16;\u014f\3"+
		";\3;\3<\3<\5<\u0156\n<\3<\5<\u0159\n<\3<\3<\3=\3=\3=\3=\7=\u0161\n=\f"+
		"=\16=\u0164\13=\3=\3=\3=\3=\3=\3>\3>\3>\3>\7>\u016f\n>\f>\16>\u0172\13"+
		">\3>\3>\4\u0098\u0162\2?\3\3\5\4\7\5\t\2\13\6\r\7\17\2\21\b\23\t\25\n"+
		"\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63"+
		"\31\65\32\67\339\34;\35=\36?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60"+
		"c\61e\62g\63i\64k\65m\66o\67q8s9u:w;y<{=\3\2\t\3\2\63;\3\2\62;\3\2^^\4"+
		"\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u017f\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2"+
		"[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3"+
		"\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2"+
		"\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\3}\3\2\2\2\5\u0080\3\2"+
		"\2\2\7\u0083\3\2\2\2\t\u008d\3\2\2\2\13\u0091\3\2\2\2\r\u0093\3\2\2\2"+
		"\17\u00a3\3\2\2\2\21\u00a5\3\2\2\2\23\u00a9\3\2\2\2\25\u00ae\3\2\2\2\27"+
		"\u00b3\3\2\2\2\31\u00b6\3\2\2\2\33\u00bb\3\2\2\2\35\u00c2\3\2\2\2\37\u00c8"+
		"\3\2\2\2!\u00cd\3\2\2\2#\u00d3\3\2\2\2%\u00d8\3\2\2\2\'\u00dc\3\2\2\2"+
		")\u00e2\3\2\2\2+\u00e8\3\2\2\2-\u00f1\3\2\2\2/\u00f5\3\2\2\2\61\u00fa"+
		"\3\2\2\2\63\u00fc\3\2\2\2\65\u00fe\3\2\2\2\67\u0100\3\2\2\29\u0102\3\2"+
		"\2\2;\u0104\3\2\2\2=\u0106\3\2\2\2?\u0109\3\2\2\2A\u010b\3\2\2\2C\u010e"+
		"\3\2\2\2E\u0110\3\2\2\2G\u0113\3\2\2\2I\u0116\3\2\2\2K\u0119\3\2\2\2M"+
		"\u011b\3\2\2\2O\u011d\3\2\2\2Q\u011f\3\2\2\2S\u0121\3\2\2\2U\u0123\3\2"+
		"\2\2W\u0126\3\2\2\2Y\u0129\3\2\2\2[\u012b\3\2\2\2]\u012d\3\2\2\2_\u012f"+
		"\3\2\2\2a\u0131\3\2\2\2c\u0133\3\2\2\2e\u0135\3\2\2\2g\u0137\3\2\2\2i"+
		"\u0139\3\2\2\2k\u013b\3\2\2\2m\u013d\3\2\2\2o\u0140\3\2\2\2q\u0143\3\2"+
		"\2\2s\u0145\3\2\2\2u\u014d\3\2\2\2w\u0158\3\2\2\2y\u015c\3\2\2\2{\u016a"+
		"\3\2\2\2}~\7-\2\2~\177\7-\2\2\177\4\3\2\2\2\u0080\u0081\7/\2\2\u0081\u0082"+
		"\7/\2\2\u0082\6\3\2\2\2\u0083\u0084\5\t\5\2\u0084\b\3\2\2\2\u0085\u0089"+
		"\t\2\2\2\u0086\u0088\t\3\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008e\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008c\u008e\7\62\2\2\u008d\u0085\3\2\2\2\u008d\u008c\3\2\2\2\u008e"+
		"\n\3\2\2\2\u008f\u0092\5\37\20\2\u0090\u0092\5!\21\2\u0091\u008f\3\2\2"+
		"\2\u0091\u0090\3\2\2\2\u0092\f\3\2\2\2\u0093\u0098\7$\2\2\u0094\u0097"+
		"\n\4\2\2\u0095\u0097\5\17\b\2\u0096\u0094\3\2\2\2\u0096\u0095\3\2\2\2"+
		"\u0097\u009a\3\2\2\2\u0098\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009b"+
		"\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009c\7$\2\2\u009c\16\3\2\2\2\u009d"+
		"\u009e\7^\2\2\u009e\u00a4\7^\2\2\u009f\u00a0\7^\2\2\u00a0\u00a4\7p\2\2"+
		"\u00a1\u00a2\7^\2\2\u00a2\u00a4\7$\2\2\u00a3\u009d\3\2\2\2\u00a3\u009f"+
		"\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\20\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6"+
		"\u00a7\7p\2\2\u00a7\u00a8\7v\2\2\u00a8\22\3\2\2\2\u00a9\u00aa\7d\2\2\u00aa"+
		"\u00ab\7q\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad\7n\2\2\u00ad\24\3\2\2\2\u00ae"+
		"\u00af\7x\2\2\u00af\u00b0\7q\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7f\2\2"+
		"\u00b2\26\3\2\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7h\2\2\u00b5\30\3\2\2"+
		"\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7u\2\2\u00b9\u00ba"+
		"\7g\2\2\u00ba\32\3\2\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be"+
		"\7v\2\2\u00be\u00bf\7w\2\2\u00bf\u00c0\7t\2\2\u00c0\u00c1\7p\2\2\u00c1"+
		"\34\3\2\2\2\u00c2\u00c3\7e\2\2\u00c3\u00c4\7n\2\2\u00c4\u00c5\7c\2\2\u00c5"+
		"\u00c6\7u\2\2\u00c6\u00c7\7u\2\2\u00c7\36\3\2\2\2\u00c8\u00c9\7v\2\2\u00c9"+
		"\u00ca\7t\2\2\u00ca\u00cb\7w\2\2\u00cb\u00cc\7g\2\2\u00cc \3\2\2\2\u00cd"+
		"\u00ce\7h\2\2\u00ce\u00cf\7c\2\2\u00cf\u00d0\7n\2\2\u00d0\u00d1\7u\2\2"+
		"\u00d1\u00d2\7g\2\2\u00d2\"\3\2\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7j"+
		"\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7\7u\2\2\u00d7$\3\2\2\2\u00d8\u00d9"+
		"\7h\2\2\u00d9\u00da\7q\2\2\u00da\u00db\7t\2\2\u00db&\3\2\2\2\u00dc\u00dd"+
		"\7y\2\2\u00dd\u00de\7j\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7n\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1(\3\2\2\2\u00e2\u00e3\7d\2\2\u00e3\u00e4\7t\2\2\u00e4"+
		"\u00e5\7g\2\2\u00e5\u00e6\7c\2\2\u00e6\u00e7\7m\2\2\u00e7*\3\2\2\2\u00e8"+
		"\u00e9\7e\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec\7v\2\2"+
		"\u00ec\u00ed\7k\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7w\2\2\u00ef\u00f0"+
		"\7g\2\2\u00f0,\3\2\2\2\u00f1\u00f2\7p\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4"+
		"\7y\2\2\u00f4.\3\2\2\2\u00f5\u00f6\7p\2\2\u00f6\u00f7\7w\2\2\u00f7\u00f8"+
		"\7n\2\2\u00f8\u00f9\7n\2\2\u00f9\60\3\2\2\2\u00fa\u00fb\7*\2\2\u00fb\62"+
		"\3\2\2\2\u00fc\u00fd\7+\2\2\u00fd\64\3\2\2\2\u00fe\u00ff\7]\2\2\u00ff"+
		"\66\3\2\2\2\u0100\u0101\7_\2\2\u01018\3\2\2\2\u0102\u0103\7}\2\2\u0103"+
		":\3\2\2\2\u0104\u0105\7\177\2\2\u0105<\3\2\2\2\u0106\u0107\7/\2\2\u0107"+
		"\u0108\7@\2\2\u0108>\3\2\2\2\u0109\u010a\7>\2\2\u010a@\3\2\2\2\u010b\u010c"+
		"\7>\2\2\u010c\u010d\7?\2\2\u010dB\3\2\2\2\u010e\u010f\7@\2\2\u010fD\3"+
		"\2\2\2\u0110\u0111\7@\2\2\u0111\u0112\7?\2\2\u0112F\3\2\2\2\u0113\u0114"+
		"\7>\2\2\u0114\u0115\7>\2\2\u0115H\3\2\2\2\u0116\u0117\7@\2\2\u0117\u0118"+
		"\7@\2\2\u0118J\3\2\2\2\u0119\u011a\7-\2\2\u011aL\3\2\2\2\u011b\u011c\7"+
		"/\2\2\u011cN\3\2\2\2\u011d\u011e\7,\2\2\u011eP\3\2\2\2\u011f\u0120\7\61"+
		"\2\2\u0120R\3\2\2\2\u0121\u0122\7\'\2\2\u0122T\3\2\2\2\u0123\u0124\7("+
		"\2\2\u0124\u0125\7(\2\2\u0125V\3\2\2\2\u0126\u0127\7~\2\2\u0127\u0128"+
		"\7~\2\2\u0128X\3\2\2\2\u0129\u012a\7`\2\2\u012aZ\3\2\2\2\u012b\u012c\7"+
		"(\2\2\u012c\\\3\2\2\2\u012d\u012e\7~\2\2\u012e^\3\2\2\2\u012f\u0130\7"+
		"#\2\2\u0130`\3\2\2\2\u0131\u0132\7\u0080\2\2\u0132b\3\2\2\2\u0133\u0134"+
		"\7A\2\2\u0134d\3\2\2\2\u0135\u0136\7<\2\2\u0136f\3\2\2\2\u0137\u0138\7"+
		"=\2\2\u0138h\3\2\2\2\u0139\u013a\7.\2\2\u013aj\3\2\2\2\u013b\u013c\7?"+
		"\2\2\u013cl\3\2\2\2\u013d\u013e\7?\2\2\u013e\u013f\7?\2\2\u013fn\3\2\2"+
		"\2\u0140\u0141\7#\2\2\u0141\u0142\7?\2\2\u0142p\3\2\2\2\u0143\u0144\7"+
		"\60\2\2\u0144r\3\2\2\2\u0145\u0149\t\5\2\2\u0146\u0148\t\6\2\2\u0147\u0146"+
		"\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"t\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014e\t\7\2\2\u014d\u014c\3\2\2\2"+
		"\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151"+
		"\3\2\2\2\u0151\u0152\b;\2\2\u0152v\3\2\2\2\u0153\u0155\7\17\2\2\u0154"+
		"\u0156\7\f\2\2\u0155\u0154\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0159\3\2"+
		"\2\2\u0157\u0159\7\f\2\2\u0158\u0153\3\2\2\2\u0158\u0157\3\2\2\2\u0159"+
		"\u015a\3\2\2\2\u015a\u015b\b<\2\2\u015bx\3\2\2\2\u015c\u015d\7\61\2\2"+
		"\u015d\u015e\7,\2\2\u015e\u0162\3\2\2\2\u015f\u0161\13\2\2\2\u0160\u015f"+
		"\3\2\2\2\u0161\u0164\3\2\2\2\u0162\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163"+
		"\u0165\3\2\2\2\u0164\u0162\3\2\2\2\u0165\u0166\7,\2\2\u0166\u0167\7\61"+
		"\2\2\u0167\u0168\3\2\2\2\u0168\u0169\b=\2\2\u0169z\3\2\2\2\u016a\u016b"+
		"\7\61\2\2\u016b\u016c\7\61\2\2\u016c\u0170\3\2\2\2\u016d\u016f\n\b\2\2"+
		"\u016e\u016d\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171"+
		"\3\2\2\2\u0171\u0173\3\2\2\2\u0172\u0170\3\2\2\2\u0173\u0174\b>\2\2\u0174"+
		"|\3\2\2\2\17\2\u0089\u008d\u0091\u0096\u0098\u00a3\u0149\u014f\u0155\u0158"+
		"\u0162\u0170\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}