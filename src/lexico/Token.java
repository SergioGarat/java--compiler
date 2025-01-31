package lexico;

public class Token {

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
        INST_IF,
        INST_ELSE,
        INST_ELIF,
        INST_FOR,
        INST_WHILE,
        INST_FUNCTION,
        INST_RETURN,
        INST_MAIN,
        INSTR_READ,
        INSTR_PRINT,
        LPAREN,
        RPAREN,
        LBRACKET,
        RBRACKET,
        SEMICOLON,
        COMMA,
        TWO_POINTS,
        DOT,
        ERROR,
        EOF,
    }

    private Tokens id;
    private int line;
    private int column;
    private String lexeme;

    public Token(Tokens in, int line, int column, String lexeme) {
        this.id = in;
        this.line = line;
        this.column = column;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {

        String result = "\t" + this.id.toString();

        result += "[" + this.lexeme + "]";
        result += "(" + this.line + ":" + this.column + ")";
        result += "\n\n";


        return result;
    }
}