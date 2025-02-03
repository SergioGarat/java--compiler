package lexico;

import errores.CompilerError;
import lexico.Token.Tokens;
import sintactico.ParserSym;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;

import java.io.BufferedWriter;
import java.io.FileWriter;

%%                              

%class Lexico
%cup
%public
%line
%column
%full

%{

    public String tkn_dir = "Tokens.txt";
    public static final String BASE_tkn_dir = "src\\output\\";
    private static BufferedWriter out;
    private ComplexSymbolFactory sf;

    public Lexico(java.io.Reader in, ComplexSymbolFactory sf) {
      this(in);
      this.sf = sf;
    }

    public Lexico(java.io.Reader in, ComplexSymbolFactory sf, String filename) {
          this(in);
          this.sf = sf;
          this.tkn_dir = filename+"\\"+this.tkn_dir;

          try{
                  out = new BufferedWriter(new FileWriter(BASE_tkn_dir + tkn_dir, true));
                  out.write("*** Tokens ***\n");
              }catch(Exception e){
                  System.out.println("Error writing Tokens : " + e);
                  e.printStackTrace();
              }
        }

    public int getLine(){
        return yyline + 1;
    }

    public int getColumn(){
        return yycolumn + 1;
    }

    public void closeFile(int line, int column){
        try{
            out.write("Token processing halted due to a syntax or semantic error at line " + line + " and column " + column + ".");
            out.close();
        }catch(Exception e){
            System.out.println("Error closing Tokens file: " + e);
            e.printStackTrace();
        }
    }

    public void writeToken(Token token){
        try{
            out.write(token.toString());
        }catch(Exception e){
            System.out.println("Error writing Tokens: " + e);
            e.printStackTrace();
        }
    }

    private Symbol symbol(String pname, int tipo, String lexema){
        return sf.newSymbol(pname, tipo, new Location(getLine(), getColumn()), new Location(getLine(), yycolumn + yylength()), lexema);
    }

    private Symbol symbol(String pname,int tipo){
        return sf.newSymbol(pname, tipo, new Location(getLine(), getColumn()), new Location(getColumn(), yycolumn + yylength()));
    }
%}

%eof{
    try {
        writeToken(new Token(Tokens.EOF,yyline,yycolumn, ""));
        out.write("\n***** End of File *****");
        out.close();
    }catch(Exception e){
        System.out.println("Error writing Tokens : " + e);
        e.printStackTrace();
    }
%eof}

%eofval{
  return symbol(Tokens.EOF.name(),ParserSym.EOF);
%eofval}


DIGIT           = [0-9]
LETTER          = [a-zA-Z]

BLANK           = (" "|\t|\r|\n)
COMMENT         = ("//".*"//")

ID              = (({LETTER})({LETTER}|{DIGIT})*)
NUMBER          = ("0" | [1-9]{DIGIT}*)
STRING          = \" [^\"]* \"
BOOLEAN         = ("true" | "false")

// Operadores

OP_SUM      = ("+")
OP_SUB      = ("-")
OP_MULT     = ("*")
OP_DIV      = ("/")
OP_MOD      = ("%")
OP_EQ       = ("==")
OP_NEQ      = ("!=")
OP_LT       = ("<")
OP_GT       = (">")
OP_LE       = ("<=")
OP_GE       = (">=")
OP_AND      = ("&&")
OP_OR       = ("||")
OP_NOT      = ("!")
OP_ASSIG    = (":=")

CONSTANT        = ("constant")

// Operaciones

IF         = ("if")
ELSE       = ("else")
ELIF       = ("elif")

WHILE      = ("while")
FOR        = ("for")

FUNC   = ("function")
RETURN     = ("return")
MAIN       = ("main")

// Entrada/salida

READ        = ("read")
PRINT       = ("print")

STRUCT          = ("struct")


// Caracteres especiales

SEMICOLON       = (";")
COMMA           = (",")
TWO_POINTS      = (":")
DOT             = (".")

LPAREN          = ("(")
RPAREN          = (")")
LBRACE          = ("{")
RBRACE          = ("}")
LBRACKET        = ("[")
RBRACKET        = ("]")

%%
{BLANK}              {/*Ignore*/}
{COMMENT}            {/*Ignore*/}

{IF}            {
                        Token token = new Token(Tokens.IF,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.IF.name(), ParserSym.IF);
                     }
{ELSE}          {
                        Token token = new Token(Tokens.ELSE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.ELSE.name(), ParserSym.ELSE);
                     }
{ELIF}          {
                        Token token = new Token(Tokens.ELSE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.ELSE.name(),ParserSym.ELIF);
                     }

{WHILE}         {
                        Token token = new Token(Tokens.WHILE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.WHILE.name(),ParserSym.WHILE);
                     }

{FOR}           {
                        Token token = new Token(Tokens.FOR,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.FOR.name(), ParserSym.FOR);
                    }

{FUNC}      {
                        Token token = new Token(Tokens.FUNC,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.FUNC.name(),ParserSym.FUNC);
                     }
{RETURN}        {
                        Token token = new Token(Tokens.RETURN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.RETURN.name(),ParserSym.RETURN);
                     }
{MAIN}          {
                        Token token = new Token(Tokens.MAIN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.MAIN.name(),ParserSym.MAIN);
                     }

{READ}         {
                        Token token = new Token(Tokens.READ,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.READ.name(),ParserSym.READ);
                     }
{PRINT}        {
                        Token token = new Token(Tokens.PRINT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.PRINT.name(),ParserSym.PRINT);
                     }

{LPAREN}             {
                        Token token = new Token(Tokens.LPAREN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.LPAREN.name(),ParserSym.lparen);
                     }
{RPAREN}             {
                        Token token = new Token(Tokens.RPAREN,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.RPAREN.name(),ParserSym.rparen);
                     }
{LBRACE}             {
                        Token token = new Token(Tokens.LBRACE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.LBRACE.name(),ParserSym.lbrace);
                     }
{RBRACE}             {
                        Token token = new Token(Tokens.RBRACE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.RBRACE.name(),ParserSym.rbrace);
                     }
{LBRACKET}           {
                        Token token = new Token(Tokens.LBRACKET,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.LBRACKET.name(),ParserSym.lbracket);
                     }
{RBRACKET}           {
                        Token token = new Token(Tokens.RBRACKET,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.RBRACKET.name(),ParserSym.rbracket);
                     }
{SEMICOLON}          {
                        Token token = new Token(Tokens.SEMICOLON,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.SEMICOLON.name(),ParserSym.semicolon);
                     }
{COMMA}              {
                        Token token = new Token(Tokens.COMMA,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.COMMA.name(),ParserSym.comma);
                     }
{TWO_POINTS}         {
                        Token token = new Token(Tokens.TWO_POINTS,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.TWO_POINTS.name(),ParserSym.two_points);
                     }
{DOT}                {
                        Token token = new Token(Tokens.DOT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.DOT.name(),ParserSym.dot);
                     }

              //OPERADORES

{OP_SUM}             {
                        Token token = new Token(Tokens.OP_SUM,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_SUM.name(),ParserSym.op_arithmetical_b, yytext());
                     }
{OP_SUB}             {
                        Token token = new Token(Tokens.OP_SUB,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_SUB.name(),ParserSym.op_arithmetical_b, yytext());
                     }
{OP_MULT}            {
                        Token token = new Token(Tokens.OP_MULT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_MULT.name(),ParserSym.op_arithmetical_c, yytext());
                     }
{OP_DIV}             {
                        Token token = new Token(Tokens.OP_DIV,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_DIV.name(),ParserSym.op_arithmetical_c, yytext());
                     }
{OP_MOD}             {
                        Token token = new Token(Tokens.OP_MOD,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_MOD.name(),ParserSym.op_arithmetical_c, yytext());
                     }
{OP_EQ}              {
                        Token token = new Token(Tokens.OP_EQ,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_EQ.name(),ParserSym.op_relational, yytext());
                     }
{OP_NEQ}             {
                        Token token = new Token(Tokens.OP_NEQ,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_NEQ.name(),ParserSym.op_relational, yytext());
                     }
{OP_LT}              {
                        Token token = new Token(Tokens.OP_LT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_LT.name(),ParserSym.op_relational, yytext());
                     }
{OP_GT}              {
                        Token token = new Token(Tokens.OP_GT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_GT.name(),ParserSym.op_relational, yytext());
                     }
{OP_LE}              {
                        Token token = new Token(Tokens.OP_LE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_LE.name(),ParserSym.op_relational, yytext());
                     }
{OP_GE}              {
                        Token token = new Token(Tokens.OP_GE,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_GE.name(),ParserSym.op_relational, yytext());
                     }
{OP_AND}             {
                        Token token = new Token(Tokens.OP_AND,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_AND.name(),ParserSym.op_logical, yytext());
                     }
{OP_OR}              {
                        Token token = new Token(Tokens.OP_OR,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_OR.name(),ParserSym.op_logical, yytext());
                     }
{OP_NOT}             {
                        Token token = new Token(Tokens.OP_NOT,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_NOT.name(),ParserSym.op_logical_not, yytext());
                     }
{OP_ASSIG}           {
                        Token token = new Token(Tokens.OP_ASSIG,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.OP_ASSIG.name(),ParserSym.op_assig, yytext());
                     }

{BOOLEAN}            {
                     Token token = new Token(Tokens.BOOLEAN,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.BOOLEAN.name(),ParserSym.bool, yytext());
                     }
{CONSTANT}           {
                     Token token = new Token(Tokens.CONSTANT,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.CONSTANT.name(),ParserSym.constant, yytext());
                     }

{STRUCT}             {
                     Token token = new Token(Tokens.STRUCT,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.STRUCT.name(),ParserSym.struct, yytext());
                    }


{ID}                 {
                     Token token = new Token(Tokens.ID,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.ID.name(),ParserSym.id, yytext());
                     }
{NUMBER}             {
                     Token token = new Token(Tokens.NUMBER,yyline,yycolumn, yytext());
                     writeToken(token);
                     return symbol(Tokens.NUMBER.name(),ParserSym.number, yytext());
                     }
{STRING}             {
                        Token token = new Token(Tokens.STRING,yyline,yycolumn, yytext());
                        writeToken(token);
                        return symbol(Tokens.STRING.name(),ParserSym.string, yytext());
                     }

//==============================================================================
[^]                  {
                        Token token = new Token(Tokens.ERROR,yyline,yycolumn, yytext());
                        writeToken(token);
                        try {
                           throw new CompilerError("[Lexical error]:" + "[" + getLine() + ":" + getColumn() + "]" + " Unkown symbol: "+"'"+this.yytext()+"'", CompilerError.ErrorType.SYNTAX);
                        } catch (CompilerError ex) {
                            System.err.println("ERROR: " + ex.getMessage());
                        }

                        return symbol(Tokens.ERROR.name(), ParserSym.error);
                     }
//==============================================================================