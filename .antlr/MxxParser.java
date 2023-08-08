// Generated from /home/cyf/compiler/Mxx.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxxParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_type = 1, RULE_listSuf = 2, RULE_classdef = 3, 
		RULE_funcdef = 4, RULE_parameterList = 5, RULE_parameter = 6, RULE_exprList = 7, 
		RULE_vardef = 8, RULE_singleAssign = 9, RULE_suite = 10, RULE_constructordef = 11, 
		RULE_stmt = 12, RULE_expr = 13, RULE_atomExpr = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "type", "listSuf", "classdef", "funcdef", "parameterList", 
			"parameter", "exprList", "vardef", "singleAssign", "suite", "constructordef", 
			"stmt", "expr", "atomExpr"
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

	@Override
	public String getGrammarFileName() { return "Mxx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxxParser.EOF, 0); }
		public List<FuncdefContext> funcdef() {
			return getRuleContexts(FuncdefContext.class);
		}
		public FuncdefContext funcdef(int i) {
			return getRuleContext(FuncdefContext.class,i);
		}
		public List<ClassdefContext> classdef() {
			return getRuleContexts(ClassdefContext.class);
		}
		public ClassdefContext classdef(int i) {
			return getRuleContext(ClassdefContext.class,i);
		}
		public List<TerminalNode> Semi() { return getTokens(MxxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxxParser.Semi, i);
		}
		public List<VardefContext> vardef() {
			return getRuleContexts(VardefContext.class);
		}
		public VardefContext vardef(int i) {
			return getRuleContext(VardefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(37);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					{
					setState(30);
					classdef();
					setState(31);
					match(Semi);
					}
					}
					break;
				case 2:
					{
					setState(33);
					funcdef();
					}
					break;
				case 3:
					{
					{
					setState(34);
					vardef();
					setState(35);
					match(Semi);
					}
					}
					break;
				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0) );
			setState(41);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxxParser.Int, 0); }
		public TerminalNode Void() { return getToken(MxxParser.Void, 0); }
		public TerminalNode Bool() { return getToken(MxxParser.Bool, 0); }
		public List<ListSufContext> listSuf() {
			return getRuleContexts(ListSufContext.class);
		}
		public ListSufContext listSuf(int i) {
			return getRuleContext(ListSufContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_type);
		try {
			int _alt;
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				match(Int);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				match(Void);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				match(Bool);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(46);
				match(Int);
				setState(48); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(47);
						listSuf();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(50); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				match(Bool);
				setState(54); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(53);
						listSuf();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(56); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(58);
				match(Identifier);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(59);
				match(Identifier);
				setState(61); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(60);
						listSuf();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(63); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListSufContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(MxxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxxParser.RightBracket, 0); }
		public ListSufContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listSuf; }
	}

	public final ListSufContext listSuf() throws RecognitionException {
		ListSufContext _localctx = new ListSufContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_listSuf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(67);
			match(LeftBracket);
			setState(68);
			match(RightBracket);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassdefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ClassdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdef; }
	}

	public final ClassdefContext classdef() throws RecognitionException {
		ClassdefContext _localctx = new ClassdefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classdef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(Class);
			setState(71);
			match(Identifier);
			setState(72);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncdefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxxParser.RightParen, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FuncdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdef; }
	}

	public final FuncdefContext funcdef() throws RecognitionException {
		FuncdefContext _localctx = new FuncdefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funcdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			type();
			setState(75);
			match(Identifier);
			{
			setState(76);
			match(LeftParen);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(77);
				parameterList();
				}
			}

			setState(80);
			match(RightParen);
			}
			setState(82);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxxParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			parameter();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(85);
				match(Comma);
				setState(86);
				parameter();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			type();
			setState(93);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxxParser.Comma, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			expr(0);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(96);
				match(Comma);
				setState(97);
				expr(0);
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VardefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<SingleAssignContext> singleAssign() {
			return getRuleContexts(SingleAssignContext.class);
		}
		public SingleAssignContext singleAssign(int i) {
			return getRuleContext(SingleAssignContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxxParser.Comma, i);
		}
		public VardefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardef; }
	}

	public final VardefContext vardef() throws RecognitionException {
		VardefContext _localctx = new VardefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_vardef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			type();
			setState(104);
			singleAssign();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(105);
				match(Comma);
				setState(106);
				singleAssign();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SingleAssignContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxxParser.Assign, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SingleAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleAssign; }
	}

	public final SingleAssignContext singleAssign() throws RecognitionException {
		SingleAssignContext _localctx = new SingleAssignContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_singleAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(Identifier);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(113);
				match(Assign);
				setState(114);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxxParser.RightBrace, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(LeftBrace);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << Integer) | (1L << BoolConst) | (1L << ConstString) | (1L << Int) | (1L << Bool) | (1L << Void) | (1L << If) | (1L << Return) | (1L << This) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << New) | (1L << Null) | (1L << LeftParen) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Semi) | (1L << Identifier))) != 0)) {
				{
				{
				setState(118);
				stmt();
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructordefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxxParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ConstructordefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructordef; }
	}

	public final ConstructordefContext constructordef() throws RecognitionException {
		ConstructordefContext _localctx = new ConstructordefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_constructordef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(Identifier);
			setState(127);
			match(LeftParen);
			setState(128);
			match(RightParen);
			setState(129);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public ExprContext Cond;
		public SuiteContext Ifsuite;
		public StmtContext Ifstmt;
		public SuiteContext Elsesuite;
		public StmtContext Elsestmt;
		public VardefContext InitVdf;
		public ExprContext InitCond;
		public ExprContext EndCond;
		public ExprContext Change;
		public SuiteContext LoopSuite;
		public StmtContext LoopStmt;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> Semi() { return getTokens(MxxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxxParser.Semi, i);
		}
		public VardefContext vardef() {
			return getRuleContext(VardefContext.class,0);
		}
		public TerminalNode If() { return getToken(MxxParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxxParser.RightParen, 0); }
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxxParser.Else, 0); }
		public TerminalNode For() { return getToken(MxxParser.For, 0); }
		public TerminalNode While() { return getToken(MxxParser.While, 0); }
		public TerminalNode Continue() { return getToken(MxxParser.Continue, 0); }
		public TerminalNode Break() { return getToken(MxxParser.Break, 0); }
		public TerminalNode Return() { return getToken(MxxParser.Return, 0); }
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public ConstructordefContext constructordef() {
			return getRuleContext(ConstructordefContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stmt);
		int _la;
		try {
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				expr(0);
				setState(132);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				vardef();
				setState(135);
				match(Semi);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(If);
				setState(138);
				match(LeftParen);
				setState(139);
				((StmtContext)_localctx).Cond = expr(0);
				setState(140);
				match(RightParen);
				setState(143);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(141);
					((StmtContext)_localctx).Ifsuite = suite();
					}
					break;
				case 2:
					{
					setState(142);
					((StmtContext)_localctx).Ifstmt = stmt();
					}
					break;
				}
				setState(150);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(145);
					match(Else);
					setState(148);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						setState(146);
						((StmtContext)_localctx).Elsesuite = suite();
						}
						break;
					case 2:
						{
						setState(147);
						((StmtContext)_localctx).Elsestmt = stmt();
						}
						break;
					}
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(152);
				match(For);
				setState(153);
				match(LeftParen);
				setState(156);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(154);
					((StmtContext)_localctx).InitVdf = vardef();
					}
					break;
				case 2:
					{
					setState(155);
					((StmtContext)_localctx).InitCond = expr(0);
					}
					break;
				}
				setState(158);
				match(Semi);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << Integer) | (1L << BoolConst) | (1L << ConstString) | (1L << This) | (1L << New) | (1L << Null) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Identifier))) != 0)) {
					{
					setState(159);
					((StmtContext)_localctx).EndCond = expr(0);
					}
				}

				setState(162);
				match(Semi);
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << Integer) | (1L << BoolConst) | (1L << ConstString) | (1L << This) | (1L << New) | (1L << Null) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Identifier))) != 0)) {
					{
					setState(163);
					((StmtContext)_localctx).Change = expr(0);
					}
				}

				setState(166);
				match(RightParen);
				setState(169);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(167);
					((StmtContext)_localctx).LoopSuite = suite();
					}
					break;
				case 2:
					{
					setState(168);
					((StmtContext)_localctx).LoopStmt = stmt();
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(171);
				match(While);
				setState(172);
				match(LeftParen);
				{
				setState(173);
				((StmtContext)_localctx).Cond = expr(0);
				}
				setState(174);
				match(RightParen);
				setState(177);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(175);
					((StmtContext)_localctx).LoopSuite = suite();
					}
					break;
				case 2:
					{
					setState(176);
					((StmtContext)_localctx).LoopStmt = stmt();
					}
					break;
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(179);
				match(Continue);
				setState(180);
				match(Semi);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(181);
				match(Break);
				setState(182);
				match(Semi);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(183);
				match(Return);
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << Integer) | (1L << BoolConst) | (1L << ConstString) | (1L << This) | (1L << New) | (1L << Null) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Identifier))) != 0)) {
					{
					setState(184);
					expr(0);
					}
				}

				setState(187);
				match(Semi);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(188);
				suite();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(189);
				funcdef();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(190);
				constructordef();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(191);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Recur_ExprContext extends ExprContext {
		public AtomExprContext atomExpr() {
			return getRuleContext(AtomExprContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxxParser.LeftParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxxParser.RightParen, 0); }
		public Recur_ExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class Func_call_ExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxxParser.RightParen, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public Func_call_ExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class CorrectNewContext extends ExprContext {
		public TerminalNode New() { return getToken(MxxParser.New, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxxParser.RightParen, 0); }
		public List<TerminalNode> LeftBracket() { return getTokens(MxxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxxParser.RightBracket, i);
		}
		public List<ListSufContext> listSuf() {
			return getRuleContexts(ListSufContext.class);
		}
		public ListSufContext listSuf(int i) {
			return getRuleContext(ListSufContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CorrectNewContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class WrongnewContext extends ExprContext {
		public TerminalNode New() { return getToken(MxxParser.New, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxxParser.RightBracket, i);
		}
		public List<ListSufContext> listSuf() {
			return getRuleContexts(ListSufContext.class);
		}
		public ListSufContext listSuf(int i) {
			return getRuleContext(ListSufContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WrongnewContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class BinaryExprContext extends ExprContext {
		public ExprContext lv;
		public Token op;
		public ExprContext rv;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Mod() { return getToken(MxxParser.Mod, 0); }
		public TerminalNode Mul() { return getToken(MxxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxxParser.Div, 0); }
		public TerminalNode Plus() { return getToken(MxxParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxxParser.Minus, 0); }
		public TerminalNode RightShift() { return getToken(MxxParser.RightShift, 0); }
		public TerminalNode LeftShift() { return getToken(MxxParser.LeftShift, 0); }
		public TerminalNode Greater() { return getToken(MxxParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxxParser.GreaterEqual, 0); }
		public TerminalNode Less() { return getToken(MxxParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(MxxParser.LessEqual, 0); }
		public TerminalNode Equal() { return getToken(MxxParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxxParser.NotEqual, 0); }
		public TerminalNode And() { return getToken(MxxParser.And, 0); }
		public TerminalNode Caret() { return getToken(MxxParser.Caret, 0); }
		public TerminalNode Or() { return getToken(MxxParser.Or, 0); }
		public TerminalNode AndAnd() { return getToken(MxxParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(MxxParser.OrOr, 0); }
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class Member_ExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxxParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public Member_ExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class PreExprContext extends ExprContext {
		public Token op;
		public ExprContext rv;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Not() { return getToken(MxxParser.Not, 0); }
		public TerminalNode Tilde() { return getToken(MxxParser.Tilde, 0); }
		public TerminalNode Minus() { return getToken(MxxParser.Minus, 0); }
		public PreExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class SufExprContext extends ExprContext {
		public ExprContext lv;
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SufExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class Lambda_FuncContext extends ExprContext {
		public TerminalNode LeftBracket() { return getToken(MxxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxxParser.RightBracket, 0); }
		public TerminalNode To() { return getToken(MxxParser.To, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode And() { return getToken(MxxParser.And, 0); }
		public TerminalNode LeftParen() { return getToken(MxxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxxParser.RightParen, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public Lambda_FuncContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class AssignExprContext extends ExprContext {
		public ExprContext lv;
		public Token op;
		public ExprContext rv;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxxParser.Assign, 0); }
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class IndexExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxxParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxxParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxxParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxxParser.RightBracket, i);
		}
		public IndexExprContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				_localctx = new WrongnewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(195);
				match(New);
				{
				{
				setState(196);
				type();
				setState(203);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(197);
						match(LeftBracket);
						{
						setState(198);
						expr(0);
						}
						setState(199);
						match(RightBracket);
						}
						} 
					}
					setState(205);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				setState(207); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(206);
						listSuf();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(209); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				{
				setState(215); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(211);
						match(LeftBracket);
						{
						setState(212);
						expr(0);
						}
						setState(213);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(217); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				}
				}
				}
				break;
			case 2:
				{
				_localctx = new CorrectNewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(219);
				match(New);
				setState(220);
				type();
				setState(221);
				match(LeftParen);
				setState(222);
				match(RightParen);
				}
				break;
			case 3:
				{
				_localctx = new CorrectNewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(224);
				match(New);
				{
				{
				{
				setState(225);
				type();
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(226);
						match(LeftBracket);
						{
						setState(227);
						expr(0);
						}
						setState(228);
						match(RightBracket);
						}
						} 
					}
					setState(234);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				setState(238);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(235);
						listSuf();
						}
						} 
					}
					setState(240);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				}
				}
				}
				}
				break;
			case 4:
				{
				_localctx = new Lambda_FuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(241);
				match(LeftBracket);
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==And) {
					{
					setState(242);
					match(And);
					}
				}

				setState(245);
				match(RightBracket);
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LeftParen) {
					{
					setState(246);
					match(LeftParen);
					setState(248);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << Void) | (1L << Identifier))) != 0)) {
						{
						setState(247);
						parameterList();
						}
					}

					setState(250);
					match(RightParen);
					}
				}

				setState(253);
				match(To);
				setState(254);
				suite();
				}
				break;
			case 5:
				{
				_localctx = new Recur_ExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(255);
				atomExpr();
				}
				break;
			case 6:
				{
				_localctx = new Recur_ExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(256);
				match(LeftParen);
				setState(257);
				expr(0);
				setState(258);
				match(RightParen);
				}
				break;
			case 7:
				{
				_localctx = new PreExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(260);
				((PreExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << Minus) | (1L << Not) | (1L << Tilde))) != 0)) ) {
					((PreExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(261);
				((PreExprContext)_localctx).rv = expr(12);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(317);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(264);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(265);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(266);
						((BinaryExprContext)_localctx).rv = expr(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(267);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(268);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(269);
						((BinaryExprContext)_localctx).rv = expr(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(270);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(271);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(272);
						((BinaryExprContext)_localctx).rv = expr(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(273);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(274);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(275);
						((BinaryExprContext)_localctx).rv = expr(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(276);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(277);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(278);
						((BinaryExprContext)_localctx).rv = expr(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(279);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(280);
						((BinaryExprContext)_localctx).op = match(And);
						setState(281);
						((BinaryExprContext)_localctx).rv = expr(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(282);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(283);
						((BinaryExprContext)_localctx).op = match(Caret);
						setState(284);
						((BinaryExprContext)_localctx).rv = expr(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(285);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(286);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(287);
						((BinaryExprContext)_localctx).rv = expr(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(288);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(289);
						((BinaryExprContext)_localctx).op = match(AndAnd);
						setState(290);
						((BinaryExprContext)_localctx).rv = expr(4);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(291);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(292);
						((BinaryExprContext)_localctx).op = match(OrOr);
						setState(293);
						((BinaryExprContext)_localctx).rv = expr(3);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExprContext(_parentctx, _parentState));
						((AssignExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(294);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(295);
						((AssignExprContext)_localctx).op = match(Assign);
						setState(296);
						((AssignExprContext)_localctx).rv = expr(1);
						}
						break;
					case 12:
						{
						_localctx = new Func_call_ExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(297);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(298);
						match(LeftParen);
						setState(300);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << Integer) | (1L << BoolConst) | (1L << ConstString) | (1L << This) | (1L << New) | (1L << Null) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Identifier))) != 0)) {
							{
							setState(299);
							exprList();
							}
						}

						setState(302);
						match(RightParen);
						}
						break;
					case 13:
						{
						_localctx = new Member_ExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(303);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(304);
						match(Dot);
						setState(305);
						match(Identifier);
						}
						break;
					case 14:
						{
						_localctx = new SufExprContext(new ExprContext(_parentctx, _parentState));
						((SufExprContext)_localctx).lv = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(306);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(307);
						((SufExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__0 || _la==T__1) ) {
							((SufExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 15:
						{
						_localctx = new IndexExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(308);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(313); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(309);
								match(LeftBracket);
								setState(310);
								expr(0);
								setState(311);
								match(RightBracket);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(315); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomExprContext extends ParserRuleContext {
		public TerminalNode ConstString() { return getToken(MxxParser.ConstString, 0); }
		public TerminalNode Integer() { return getToken(MxxParser.Integer, 0); }
		public TerminalNode BoolConst() { return getToken(MxxParser.BoolConst, 0); }
		public TerminalNode Identifier() { return getToken(MxxParser.Identifier, 0); }
		public TerminalNode This() { return getToken(MxxParser.This, 0); }
		public TerminalNode Null() { return getToken(MxxParser.Null, 0); }
		public AtomExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomExpr; }
	}

	public final AtomExprContext atomExpr() throws RecognitionException {
		AtomExprContext _localctx = new AtomExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_atomExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Integer) | (1L << BoolConst) | (1L << ConstString) | (1L << This) | (1L << Null) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 19);
		case 12:
			return precpred(_ctx, 18);
		case 13:
			return precpred(_ctx, 14);
		case 14:
			return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0147\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\6\2(\n\2\r\2\16\2)\3\2\3\2\3\3\3\3\3\3\3\3\3\3\6\3\63\n\3\r\3"+
		"\16\3\64\3\3\3\3\6\39\n\3\r\3\16\3:\3\3\3\3\3\3\6\3@\n\3\r\3\16\3A\5\3"+
		"D\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6Q\n\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\7\7Z\n\7\f\7\16\7]\13\7\3\b\3\b\3\b\3\t\3\t\3\t\7\te"+
		"\n\t\f\t\16\th\13\t\3\n\3\n\3\n\3\n\7\nn\n\n\f\n\16\nq\13\n\3\13\3\13"+
		"\3\13\5\13v\n\13\3\f\3\f\7\fz\n\f\f\f\16\f}\13\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u0092\n\16\3\16\3\16\3\16\5\16\u0097\n\16\5\16\u0099\n\16\3\16\3\16\3"+
		"\16\3\16\5\16\u009f\n\16\3\16\3\16\5\16\u00a3\n\16\3\16\3\16\5\16\u00a7"+
		"\n\16\3\16\3\16\3\16\5\16\u00ac\n\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u00b4\n\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00bc\n\16\3\16\3\16\3"+
		"\16\3\16\3\16\5\16\u00c3\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17"+
		"\u00cc\n\17\f\17\16\17\u00cf\13\17\3\17\6\17\u00d2\n\17\r\17\16\17\u00d3"+
		"\3\17\3\17\3\17\3\17\6\17\u00da\n\17\r\17\16\17\u00db\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00e9\n\17\f\17\16\17\u00ec"+
		"\13\17\3\17\7\17\u00ef\n\17\f\17\16\17\u00f2\13\17\3\17\3\17\5\17\u00f6"+
		"\n\17\3\17\3\17\3\17\5\17\u00fb\n\17\3\17\5\17\u00fe\n\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0109\n\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u012f\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\6\17\u013c\n\17\r\17\16\17\u013d\7\17\u0140\n\17"+
		"\f\17\16\17\u0143\13\17\3\20\3\20\3\20\3\u00f0\3\34\21\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36\2\n\5\2\3\4&&/\60\3\2\')\3\2%&\3\2#$\3\2\37\""+
		"\3\2\66\67\3\2\3\4\6\2\5\7\21\21\27\2799\2\u017d\2\'\3\2\2\2\4C\3\2\2"+
		"\2\6E\3\2\2\2\bH\3\2\2\2\nL\3\2\2\2\fV\3\2\2\2\16^\3\2\2\2\20a\3\2\2\2"+
		"\22i\3\2\2\2\24r\3\2\2\2\26w\3\2\2\2\30\u0080\3\2\2\2\32\u00c2\3\2\2\2"+
		"\34\u0108\3\2\2\2\36\u0144\3\2\2\2 !\5\b\5\2!\"\7\63\2\2\"(\3\2\2\2#("+
		"\5\n\6\2$%\5\22\n\2%&\7\63\2\2&(\3\2\2\2\' \3\2\2\2\'#\3\2\2\2\'$\3\2"+
		"\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*+\3\2\2\2+,\7\2\2\3,\3\3\2\2\2-D\7"+
		"\b\2\2.D\7\n\2\2/D\7\t\2\2\60\62\7\b\2\2\61\63\5\6\4\2\62\61\3\2\2\2\63"+
		"\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65D\3\2\2\2\668\7\t\2\2\679\5"+
		"\6\4\28\67\3\2\2\29:\3\2\2\2:8\3\2\2\2:;\3\2\2\2;D\3\2\2\2<D\79\2\2=?"+
		"\79\2\2>@\5\6\4\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2C-"+
		"\3\2\2\2C.\3\2\2\2C/\3\2\2\2C\60\3\2\2\2C\66\3\2\2\2C<\3\2\2\2C=\3\2\2"+
		"\2D\5\3\2\2\2EF\7\32\2\2FG\7\33\2\2G\7\3\2\2\2HI\7\16\2\2IJ\79\2\2JK\5"+
		"\26\f\2K\t\3\2\2\2LM\5\4\3\2MN\79\2\2NP\7\30\2\2OQ\5\f\7\2PO\3\2\2\2P"+
		"Q\3\2\2\2QR\3\2\2\2RS\7\31\2\2ST\3\2\2\2TU\5\26\f\2U\13\3\2\2\2V[\5\16"+
		"\b\2WX\7\64\2\2XZ\5\16\b\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\"+
		"\r\3\2\2\2][\3\2\2\2^_\5\4\3\2_`\79\2\2`\17\3\2\2\2af\5\34\17\2bc\7\64"+
		"\2\2ce\5\34\17\2db\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\21\3\2\2\2h"+
		"f\3\2\2\2ij\5\4\3\2jo\5\24\13\2kl\7\64\2\2ln\5\24\13\2mk\3\2\2\2nq\3\2"+
		"\2\2om\3\2\2\2op\3\2\2\2p\23\3\2\2\2qo\3\2\2\2ru\79\2\2st\7\65\2\2tv\5"+
		"\34\17\2us\3\2\2\2uv\3\2\2\2v\25\3\2\2\2w{\7\34\2\2xz\5\32\16\2yx\3\2"+
		"\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177\7\35\2\2\177"+
		"\27\3\2\2\2\u0080\u0081\79\2\2\u0081\u0082\7\30\2\2\u0082\u0083\7\31\2"+
		"\2\u0083\u0084\5\26\f\2\u0084\31\3\2\2\2\u0085\u0086\5\34\17\2\u0086\u0087"+
		"\7\63\2\2\u0087\u00c3\3\2\2\2\u0088\u0089\5\22\n\2\u0089\u008a\7\63\2"+
		"\2\u008a\u00c3\3\2\2\2\u008b\u008c\7\13\2\2\u008c\u008d\7\30\2\2\u008d"+
		"\u008e\5\34\17\2\u008e\u0091\7\31\2\2\u008f\u0092\5\26\f\2\u0090\u0092"+
		"\5\32\16\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\u0098\3\2\2\2"+
		"\u0093\u0096\7\f\2\2\u0094\u0097\5\26\f\2\u0095\u0097\5\32\16\2\u0096"+
		"\u0094\3\2\2\2\u0096\u0095\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u0093\3\2"+
		"\2\2\u0098\u0099\3\2\2\2\u0099\u00c3\3\2\2\2\u009a\u009b\7\22\2\2\u009b"+
		"\u009e\7\30\2\2\u009c\u009f\5\22\n\2\u009d\u009f\5\34\17\2\u009e\u009c"+
		"\3\2\2\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a2\7\63\2\2\u00a1\u00a3\5\34\17\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3"+
		"\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\7\63\2\2\u00a5\u00a7\5\34\17"+
		"\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00ab"+
		"\7\31\2\2\u00a9\u00ac\5\26\f\2\u00aa\u00ac\5\32\16\2\u00ab\u00a9\3\2\2"+
		"\2\u00ab\u00aa\3\2\2\2\u00ac\u00c3\3\2\2\2\u00ad\u00ae\7\23\2\2\u00ae"+
		"\u00af\7\30\2\2\u00af\u00b0\5\34\17\2\u00b0\u00b3\7\31\2\2\u00b1\u00b4"+
		"\5\26\f\2\u00b2\u00b4\5\32\16\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2\2"+
		"\2\u00b4\u00c3\3\2\2\2\u00b5\u00b6\7\25\2\2\u00b6\u00c3\7\63\2\2\u00b7"+
		"\u00b8\7\24\2\2\u00b8\u00c3\7\63\2\2\u00b9\u00bb\7\r\2\2\u00ba\u00bc\5"+
		"\34\17\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00c3\7\63\2\2\u00be\u00c3\5\26\f\2\u00bf\u00c3\5\n\6\2\u00c0\u00c3\5"+
		"\30\r\2\u00c1\u00c3\7\63\2\2\u00c2\u0085\3\2\2\2\u00c2\u0088\3\2\2\2\u00c2"+
		"\u008b\3\2\2\2\u00c2\u009a\3\2\2\2\u00c2\u00ad\3\2\2\2\u00c2\u00b5\3\2"+
		"\2\2\u00c2\u00b7\3\2\2\2\u00c2\u00b9\3\2\2\2\u00c2\u00be\3\2\2\2\u00c2"+
		"\u00bf\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\33\3\2\2"+
		"\2\u00c4\u00c5\b\17\1\2\u00c5\u00c6\7\26\2\2\u00c6\u00cd\5\4\3\2\u00c7"+
		"\u00c8\7\32\2\2\u00c8\u00c9\5\34\17\2\u00c9\u00ca\7\33\2\2\u00ca\u00cc"+
		"\3\2\2\2\u00cb\u00c7\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d2\5\6"+
		"\4\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d9\3\2\2\2\u00d5\u00d6\7\32\2\2\u00d6\u00d7\5"+
		"\34\17\2\u00d7\u00d8\7\33\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d5\3\2\2\2"+
		"\u00da\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u0109"+
		"\3\2\2\2\u00dd\u00de\7\26\2\2\u00de\u00df\5\4\3\2\u00df\u00e0\7\30\2\2"+
		"\u00e0\u00e1\7\31\2\2\u00e1\u0109\3\2\2\2\u00e2\u00e3\7\26\2\2\u00e3\u00ea"+
		"\5\4\3\2\u00e4\u00e5\7\32\2\2\u00e5\u00e6\5\34\17\2\u00e6\u00e7\7\33\2"+
		"\2\u00e7\u00e9\3\2\2\2\u00e8\u00e4\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8"+
		"\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00f0\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"\u00ef\5\6\4\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f0\u00ee\3\2\2\2\u00f1\u0109\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3"+
		"\u00f5\7\32\2\2\u00f4\u00f6\7-\2\2\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00fd\7\33\2\2\u00f8\u00fa\7\30\2\2\u00f9"+
		"\u00fb\5\f\7\2\u00fa\u00f9\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\3\2"+
		"\2\2\u00fc\u00fe\7\31\2\2\u00fd\u00f8\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u0100\7\36\2\2\u0100\u0109\5\26\f\2\u0101\u0109\5"+
		"\36\20\2\u0102\u0103\7\30\2\2\u0103\u0104\5\34\17\2\u0104\u0105\7\31\2"+
		"\2\u0105\u0109\3\2\2\2\u0106\u0107\t\2\2\2\u0107\u0109\5\34\17\16\u0108"+
		"\u00c4\3\2\2\2\u0108\u00dd\3\2\2\2\u0108\u00e2\3\2\2\2\u0108\u00f3\3\2"+
		"\2\2\u0108\u0101\3\2\2\2\u0108\u0102\3\2\2\2\u0108\u0106\3\2\2\2\u0109"+
		"\u0141\3\2\2\2\u010a\u010b\f\r\2\2\u010b\u010c\t\3\2\2\u010c\u0140\5\34"+
		"\17\16\u010d\u010e\f\f\2\2\u010e\u010f\t\4\2\2\u010f\u0140\5\34\17\r\u0110"+
		"\u0111\f\13\2\2\u0111\u0112\t\5\2\2\u0112\u0140\5\34\17\f\u0113\u0114"+
		"\f\n\2\2\u0114\u0115\t\6\2\2\u0115\u0140\5\34\17\13\u0116\u0117\f\t\2"+
		"\2\u0117\u0118\t\7\2\2\u0118\u0140\5\34\17\n\u0119\u011a\f\b\2\2\u011a"+
		"\u011b\7-\2\2\u011b\u0140\5\34\17\t\u011c\u011d\f\7\2\2\u011d\u011e\7"+
		",\2\2\u011e\u0140\5\34\17\b\u011f\u0120\f\6\2\2\u0120\u0121\7.\2\2\u0121"+
		"\u0140\5\34\17\7\u0122\u0123\f\5\2\2\u0123\u0124\7*\2\2\u0124\u0140\5"+
		"\34\17\6\u0125\u0126\f\4\2\2\u0126\u0127\7+\2\2\u0127\u0140\5\34\17\5"+
		"\u0128\u0129\f\3\2\2\u0129\u012a\7\65\2\2\u012a\u0140\5\34\17\3\u012b"+
		"\u012c\f\25\2\2\u012c\u012e\7\30\2\2\u012d\u012f\5\20\t\2\u012e\u012d"+
		"\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0140\7\31\2\2"+
		"\u0131\u0132\f\24\2\2\u0132\u0133\78\2\2\u0133\u0140\79\2\2\u0134\u0135"+
		"\f\20\2\2\u0135\u0140\t\b\2\2\u0136\u013b\f\17\2\2\u0137\u0138\7\32\2"+
		"\2\u0138\u0139\5\34\17\2\u0139\u013a\7\33\2\2\u013a\u013c\3\2\2\2\u013b"+
		"\u0137\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013e\u0140\3\2\2\2\u013f\u010a\3\2\2\2\u013f\u010d\3\2\2\2\u013f"+
		"\u0110\3\2\2\2\u013f\u0113\3\2\2\2\u013f\u0116\3\2\2\2\u013f\u0119\3\2"+
		"\2\2\u013f\u011c\3\2\2\2\u013f\u011f\3\2\2\2\u013f\u0122\3\2\2\2\u013f"+
		"\u0125\3\2\2\2\u013f\u0128\3\2\2\2\u013f\u012b\3\2\2\2\u013f\u0131\3\2"+
		"\2\2\u013f\u0134\3\2\2\2\u013f\u0136\3\2\2\2\u0140\u0143\3\2\2\2\u0141"+
		"\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\35\3\2\2\2\u0143\u0141\3\2\2"+
		"\2\u0144\u0145\t\t\2\2\u0145\37\3\2\2\2%\')\64:ACP[fou{\u0091\u0096\u0098"+
		"\u009e\u00a2\u00a6\u00ab\u00b3\u00bb\u00c2\u00cd\u00d3\u00db\u00ea\u00f0"+
		"\u00f5\u00fa\u00fd\u0108\u012e\u013d\u013f\u0141";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}