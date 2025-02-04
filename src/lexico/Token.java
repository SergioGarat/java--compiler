package lexico;

public class Token {

    private Tokens identifier;
    private int col;
    private int linea;
    private String lexema;

    public Token(Tokens id, int linea, int col, String lexema) {
        this.identifier = id;
        this.linea = linea;
        this.col = col;
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "\t" + identifier + "[" + lexema + "](" + linea + ":" + col + ")\n\n";
    }

    public enum Tokens {
        ID,
        DIGIT,
        CHARACTER,
        NUMBER,
        STRING,
        BOOLEAN,
        STRUCT,
        CONSTANT,
        OP_SUM,
        OP_SUB,
        OP_MULT,
        OP_DIV,
        OP_MOD,
        OP_EQ,
        OP_NEQ,
        OP_LT,
        OP_GT,
        OP_LE,
        OP_GE,
        OP_AND,
        OP_OR,
        OP_NOT,
        OP_ASSIG,
        IF,
        ELSE,
        ELIF,
        FOR,
        WHILE,
        FUNC,
        RETURN,
        MAIN,
        READ,
        PRINT,
        LPAREN,
        RPAREN,
        LBRACE,
        RBRACE,
        LBRACKET,
        RBRACKET,
        SEMICOLON,
        COMMA,
        TWO_POINTS,
        ERROR,
        EOF,
    }

}