// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: src/lexico/lexico.flex

package lexico;

import errores.CompilerError;
import lexico.Token.Tokens;
import sintactico.ParserSym;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;

import java.io.BufferedWriter;
import java.io.FileWriter;


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class Lexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  2,  3,  3,  2,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     1,  4,  5,  0,  0,  6,  7,  0,  8,  9, 10, 11, 12, 13,  0, 14, 
    15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 17, 18, 19, 20, 21,  0, 
     0, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 
    22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,  0,  0,  0,  0,  0, 
     0, 23, 22, 24, 25, 26, 27, 22, 28, 29, 22, 22, 30, 31, 32, 33, 
    34, 22, 35, 36, 37, 38, 22, 39, 22, 22, 22, 40, 41, 42,  0,  0, 
     0,  0,  0,  0,  0,  3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\1\1\4\1\1\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\2\14\1\15"+
    "\1\16\1\17\1\1\1\20\12\21\1\22\1\1\1\23"+
    "\1\24\1\0\1\25\1\26\1\0\1\27\1\30\1\31"+
    "\1\32\5\21\1\33\5\21\1\34\1\0\4\21\1\35"+
    "\7\21\1\2\1\21\1\36\1\37\1\21\1\40\1\21"+
    "\1\41\1\21\1\42\3\21\1\43\1\21\1\44\2\21"+
    "\1\45\2\21\1\46\1\47";

  private static int [] zzUnpackAction() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\53\0\53\0\126\0\201\0\53\0\254\0\53"+
    "\0\53\0\53\0\53\0\53\0\53\0\327\0\53\0\u0102"+
    "\0\u012d\0\53\0\u0158\0\u0183\0\u01ae\0\u01d9\0\u0204\0\u022f"+
    "\0\u025a\0\u0285\0\u02b0\0\u02db\0\u0306\0\u0331\0\u035c\0\53"+
    "\0\u0387\0\53\0\53\0\201\0\53\0\53\0\u03b2\0\53"+
    "\0\53\0\53\0\53\0\u03dd\0\u0408\0\u0433\0\u045e\0\u0489"+
    "\0\u01d9\0\u04b4\0\u04df\0\u050a\0\u0535\0\u0560\0\53\0\u058b"+
    "\0\u05b6\0\u05e1\0\u060c\0\u0637\0\u01d9\0\u0662\0\u068d\0\u06b8"+
    "\0\u06e3\0\u070e\0\u0739\0\u0764\0\u058b\0\u078f\0\u01d9\0\u01d9"+
    "\0\u07ba\0\u01d9\0\u07e5\0\u01d9\0\u0810\0\u01d9\0\u083b\0\u0866"+
    "\0\u0891\0\u01d9\0\u08bc\0\u01d9\0\u08e7\0\u0912\0\u01d9\0\u093d"+
    "\0\u0968\0\u01d9\0\u01d9";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\2\1\4\1\5\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\2\26\1\27\1\26"+
    "\1\30\1\31\1\26\1\32\1\26\1\33\2\26\1\34"+
    "\1\35\1\26\1\36\1\26\1\37\1\40\1\41\1\42"+
    "\77\0\1\43\26\0\5\44\1\45\45\44\7\0\1\46"+
    "\61\0\1\47\53\0\2\20\56\0\1\50\52\0\1\51"+
    "\52\0\1\52\52\0\1\53\45\0\2\26\5\0\22\26"+
    "\22\0\2\26\5\0\13\26\1\54\6\26\22\0\2\26"+
    "\5\0\10\26\1\55\11\26\22\0\2\26\5\0\1\26"+
    "\1\56\11\26\1\57\4\26\1\60\1\26\22\0\2\26"+
    "\5\0\5\26\1\61\14\26\22\0\2\26\5\0\1\26"+
    "\1\62\20\26\22\0\2\26\5\0\15\26\1\63\4\26"+
    "\22\0\2\26\5\0\4\26\1\64\15\26\22\0\2\26"+
    "\5\0\15\26\1\65\4\26\22\0\2\26\5\0\6\26"+
    "\1\66\13\26\54\0\1\67\1\0\2\47\2\0\12\47"+
    "\1\70\34\47\17\0\2\26\5\0\12\26\1\71\7\26"+
    "\22\0\2\26\5\0\7\26\1\72\6\26\1\73\3\26"+
    "\22\0\2\26\5\0\10\26\1\74\11\26\22\0\2\26"+
    "\5\0\15\26\1\75\4\26\22\0\2\26\5\0\12\26"+
    "\1\76\7\26\22\0\2\26\5\0\7\26\1\77\12\26"+
    "\22\0\2\26\5\0\7\26\1\100\12\26\22\0\2\26"+
    "\5\0\1\26\1\101\15\26\1\102\2\26\22\0\2\26"+
    "\5\0\20\26\1\103\1\26\22\0\2\26\5\0\7\26"+
    "\1\104\12\26\3\0\2\47\2\0\12\47\1\105\34\47"+
    "\17\0\2\26\5\0\16\26\1\106\3\26\22\0\2\26"+
    "\5\0\5\26\1\107\14\26\22\0\2\26\5\0\4\26"+
    "\1\110\15\26\22\0\2\26\5\0\16\26\1\103\3\26"+
    "\22\0\2\26\5\0\2\26\1\111\17\26\22\0\2\26"+
    "\5\0\12\26\1\112\7\26\22\0\2\26\5\0\12\26"+
    "\1\113\7\26\22\0\2\26\5\0\3\26\1\114\16\26"+
    "\22\0\2\26\5\0\20\26\1\115\1\26\22\0\2\26"+
    "\5\0\4\26\1\116\15\26\22\0\2\26\5\0\10\26"+
    "\1\117\11\26\22\0\2\26\5\0\17\26\1\120\2\26"+
    "\22\0\2\26\5\0\17\26\1\121\2\26\22\0\2\26"+
    "\5\0\17\26\1\122\2\26\22\0\2\26\5\0\15\26"+
    "\1\123\4\26\22\0\2\26\5\0\4\26\1\124\15\26"+
    "\22\0\2\26\5\0\1\26\1\125\20\26\22\0\2\26"+
    "\5\0\7\26\1\126\12\26\22\0\2\26\5\0\12\26"+
    "\1\127\7\26\22\0\2\26\5\0\12\26\1\130\7\26"+
    "\22\0\2\26\5\0\13\26\1\131\6\26\22\0\2\26"+
    "\5\0\17\26\1\132\2\26\22\0\2\26\5\0\12\26"+
    "\1\133\7\26\3\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2451];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\2\1\1\11\1\1\6\11\1\1\1\11"+
    "\2\1\1\11\15\1\1\11\1\1\2\11\1\0\2\11"+
    "\1\0\4\11\13\1\1\11\1\0\43\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    public Lexico(java.io.Reader in, ComplexSymbolFactory sf) {
      this(in);
      this.symbolFactory = sf;

    }

    public Lexico(java.io.Reader in, ComplexSymbolFactory sf, String filename) {
          this(in);
          this.symbolFactory = sf;
          this.TOKENS_PATH = filename+"\\"+this.TOKENS_PATH;

          try{
                  out = new BufferedWriter(new FileWriter(BASE_TOKENS_PATH + TOKENS_PATH, true));
                  out.write("*** Tokens ***\n");
              }catch(Exception e){
                  System.out.println("Error writing Tokens : " + e);
                  e.printStackTrace();
              }

        }

    public String TOKENS_PATH = "Tokens.txt";

    public static final String TOKENS_ERROR_PATH = "src\\output\\Error_Tokens.txt";

    public static final String BASE_TOKENS_PATH = "src\\output\\";

    private static BufferedWriter out;

    private ComplexSymbolFactory symbolFactory;

    public void closeTokensFile(int line, int column){
        try{
            out.write("Stopped processing tokens due to a syntax or semantic error on line : " + line + " and column : "+ column);
            out.close();
        }catch(Exception e){
            System.out.println("Error closing Tokens file : " + e);
            e.printStackTrace();
        }
    }

    public int getLine(){
        return yyline + 1;
    }

    public int getColumn(){
        return yycolumn + 1;
    }

    public void writeToken(Token token){
        try{
            out.write(token.toString());
        }catch(Exception e){
            System.out.println("Error writing Tokens : " + e);
            e.printStackTrace();
        }
    }

    private Symbol symbol(String plainname,int type){
        return symbolFactory.newSymbol(plainname, type, new Location(getLine(), getColumn()), new Location(getColumn(), yycolumn+yylength()));
    }

    private Symbol symbol(String plainname, int type, String lexeme){
        return symbolFactory.newSymbol(plainname, type, new Location(getLine(), getColumn()), new Location(getLine(), yycolumn+yylength()), lexeme);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexico(java.io.Reader in) {
      /*
      try{
        out = new BufferedWriter(new FileWriter(BASE_TOKENS_PATH + TOKENS_PATH, true));
        out.write("*** Tokens ***\n");
    }catch(Exception e){
        System.out.println("Error writing Tokens : " + e);
        e.printStackTrace();
    }
     */
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    return ZZ_CMAP[input];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    try {
        writeToken(new Token(Tokens.EOF,yyline,yycolumn, ""));
        out.write("\n\n*** All token data shown! ***");
        out.close();
    }catch(Exception e){
        System.out.println("Error writing Tokens : " + e);
        e.printStackTrace();
    }
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          {   return symbol(Tokens.EOF.name(),ParserSym.EOF);
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { Token token = new Token(Tokens.ERROR,yyline,yycolumn, yytext());
                        writeToken(token);
                        try {
                           throw new CompilerError("[Lexical error]:" + "[" + getLine() + ":" + getColumn() + "]" + " Unkown symbol: "+"'"+this.yytext()+"'", CompilerError.ErrorType.SYNTAX);
                        } catch (CompilerError ex) {
                            System.err.println("ERROR: " + ex.getMessage());
                        }

                        return symbol(Tokens.ERROR.name(), ParserSym.error);
            }
            // fall through
          case 40: break;
          case 2:
            { /*Ignore*/
            }
            // fall through
          case 41: break;
          case 3:
            { Token token = new Token(Tokens.OP_NOT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_NOT.name(),ParserSym.op_logical_not, yytext());
            }
            // fall through
          case 42: break;
          case 4:
            { Token token = new Token(Tokens.OP_MOD,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_MOD.name(),ParserSym.op_arithmetical_c, yytext());
            }
            // fall through
          case 43: break;
          case 5:
            { Token token = new Token(Tokens.LPAREN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.LPAREN.name(),ParserSym.lparen);
            }
            // fall through
          case 44: break;
          case 6:
            { Token token = new Token(Tokens.RPAREN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.RPAREN.name(),ParserSym.rparen);
            }
            // fall through
          case 45: break;
          case 7:
            { Token token = new Token(Tokens.OP_MULT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_MULT.name(),ParserSym.op_arithmetical_c, yytext());
            }
            // fall through
          case 46: break;
          case 8:
            { Token token = new Token(Tokens.OP_SUM,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_SUM.name(),ParserSym.op_arithmetical_b, yytext());
            }
            // fall through
          case 47: break;
          case 9:
            { Token token = new Token(Tokens.COMMA,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.COMMA.name(),ParserSym.comma);
            }
            // fall through
          case 48: break;
          case 10:
            { Token token = new Token(Tokens.OP_SUB,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_SUB.name(),ParserSym.op_arithmetical_b, yytext());
            }
            // fall through
          case 49: break;
          case 11:
            { Token token = new Token(Tokens.OP_DIV,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_DIV.name(),ParserSym.op_arithmetical_c, yytext());
            }
            // fall through
          case 50: break;
          case 12:
            { Token token = new Token(Tokens.NUMBER,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.NUMBER.name(),ParserSym.number, yytext());
            }
            // fall through
          case 51: break;
          case 13:
            { Token token = new Token(Tokens.TWO_POINTS,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.TWO_POINTS.name(),ParserSym.two_points);
            }
            // fall through
          case 52: break;
          case 14:
            { Token token = new Token(Tokens.SEMICOLON,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.SEMICOLON.name(),ParserSym.semicolon);
            }
            // fall through
          case 53: break;
          case 15:
            { Token token = new Token(Tokens.OP_LT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_LT.name(),ParserSym.op_relational, yytext());
            }
            // fall through
          case 54: break;
          case 16:
            { Token token = new Token(Tokens.OP_GT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_GT.name(),ParserSym.op_relational, yytext());
            }
            // fall through
          case 55: break;
          case 17:
            { Token token = new Token(Tokens.ID,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.ID.name(),ParserSym.id, yytext());
            }
            // fall through
          case 56: break;
          case 18:
            { Token token = new Token(Tokens.LBRACKET,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.LBRACKET.name(),ParserSym.lbracket);
            }
            // fall through
          case 57: break;
          case 19:
            { Token token = new Token(Tokens.RBRACKET,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.RBRACKET.name(),ParserSym.rbracket);
            }
            // fall through
          case 58: break;
          case 20:
            { Token token = new Token(Tokens.OP_NEQ,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_NEQ.name(),ParserSym.op_relational, yytext());
            }
            // fall through
          case 59: break;
          case 21:
            { Token token = new Token(Tokens.STRING,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.STRING.name(),ParserSym.string, yytext());
            }
            // fall through
          case 60: break;
          case 22:
            { Token token = new Token(Tokens.OP_AND,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_AND.name(),ParserSym.op_logical, yytext());
            }
            // fall through
          case 61: break;
          case 23:
            { Token token = new Token(Tokens.OP_ASSIG,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_ASSIG.name(),ParserSym.op_assig, yytext());
            }
            // fall through
          case 62: break;
          case 24:
            { Token token = new Token(Tokens.OP_LE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_LE.name(),ParserSym.op_relational, yytext());
            }
            // fall through
          case 63: break;
          case 25:
            { Token token = new Token(Tokens.OP_EQ,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_EQ.name(),ParserSym.op_relational, yytext());
            }
            // fall through
          case 64: break;
          case 26:
            { Token token = new Token(Tokens.OP_GE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_GE.name(),ParserSym.op_relational, yytext());
            }
            // fall through
          case 65: break;
          case 27:
            { Token token = new Token(Tokens.INST_IF,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_IF.name(), ParserSym.inst_if);
            }
            // fall through
          case 66: break;
          case 28:
            { Token token = new Token(Tokens.OP_OR,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_OR.name(),ParserSym.op_logical, yytext());
            }
            // fall through
          case 67: break;
          case 29:
            { Token token = new Token(Tokens.INST_FOR,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_FOR.name(), ParserSym.inst_for);
            }
            // fall through
          case 68: break;
          case 30:
            { Token token = new Token(Tokens.INST_ELSE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_ELSE.name(),ParserSym.inst_elif);
            }
            // fall through
          case 69: break;
          case 31:
            { Token token = new Token(Tokens.INST_ELSE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_ELSE.name(), ParserSym.inst_else);
            }
            // fall through
          case 70: break;
          case 32:
            { Token token = new Token(Tokens.INST_MAIN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_MAIN.name(),ParserSym.inst_main);
            }
            // fall through
          case 71: break;
          case 33:
            { Token token = new Token(Tokens.INSTR_READ,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INSTR_READ.name(),ParserSym.instr_read);
            }
            // fall through
          case 72: break;
          case 34:
            { Token token = new Token(Tokens.BOOLEAN,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.BOOLEAN.name(),ParserSym.bool, yytext());
            }
            // fall through
          case 73: break;
          case 35:
            { Token token = new Token(Tokens.INSTR_PRINT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INSTR_PRINT.name(),ParserSym.instr_print);
            }
            // fall through
          case 74: break;
          case 36:
            { Token token = new Token(Tokens.INST_WHILE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_WHILE.name(),ParserSym.inst_while);
            }
            // fall through
          case 75: break;
          case 37:
            { Token token = new Token(Tokens.INST_RETURN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_RETURN.name(),ParserSym.inst_return);
            }
            // fall through
          case 76: break;
          case 38:
            { Token token = new Token(Tokens.CONSTANT,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.CONSTANT.name(),ParserSym.constant, yytext());
            }
            // fall through
          case 77: break;
          case 39:
            { Token token = new Token(Tokens.INST_FUNCTION,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.INST_FUNCTION.name(),ParserSym.inst_function);
            }
            // fall through
          case 78: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
