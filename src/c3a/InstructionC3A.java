package c3a;

import java.util.Map;

public class InstructionC3A {

    public Code code;
    public String op1, op2, destino;

    public enum Code {
        copy, neg, add, sub, prod, div, mod, and, or, not, skip,
        go_to, jump_cond, LT, LE, EQ, NE, GE, GT, pmb, call, param,
        rtn, read, print
    }

    public InstructionC3A(Code code, String op1, String op2, String destino) {
        this.code = code;
        this.op1 = op1;
        this.op2 = op2;
        this.destino = destino;
    }

    private static final Map<Code, InstructionFormatter> FORMATTERS = Map.ofEntries(
            Map.entry(Code.not, (op1, op2, destino) -> destino + " = not " + op1),
            Map.entry(Code.neg, (op1, op2, destino) -> destino + " = neg " + op1),
            Map.entry(Code.add, (op1, op2, destino) -> destino + " = " + op1 + " + " + op2),
            Map.entry(Code.sub, (op1, op2, destino) -> destino + " = " + op1 + " - " + op2),
            Map.entry(Code.prod, (op1, op2, destino) -> destino + " = " + op1 + " * " + op2),
            Map.entry(Code.div, (op1, op2, destino) -> destino + " = " + op1 + " / " + op2),
            Map.entry(Code.mod, (op1, op2, destino) -> destino + " = " + op1 + " % " + op2),
            Map.entry(Code.and, (op1, op2, destino) -> destino + " = " + op1 + " && " + op2),
            Map.entry(Code.or, (op1, op2, destino) -> destino + " = " + op1 + " || " + op2),
            Map.entry(Code.EQ, (op1, op2, destino) -> destino + " = " + op1 + " == " + op2),
            Map.entry(Code.GE, (op1, op2, destino) -> destino + " = " + op1 + " >= " + op2),
            Map.entry(Code.GT, (op1, op2, destino) -> destino + " = " + op1 + " > " + op2),
            Map.entry(Code.LE, (op1, op2, destino) -> destino + " = " + op1 + " <= " + op2),
            Map.entry(Code.LT, (op1, op2, destino) -> destino + " = " + op1 + " < " + op2),
            Map.entry(Code.NE, (op1, op2, destino) -> destino + " = " + op1 + " != " + op2),
            Map.entry(Code.call, (op1, op2, destino) -> "call " + destino),
            Map.entry(Code.pmb, (op1, op2, destino) -> "pmb " + destino),
            Map.entry(Code.copy, (op1, op2, destino) -> destino + " = " + op1 + (op2 != null ? " " + op2 : "")),
            Map.entry(Code.go_to, (op1, op2, destino) -> "goto " + destino),
            Map.entry(Code.jump_cond, (op1, op2, destino) -> "if " + op1 + " == " + op2 + " goto " + destino),
            Map.entry(Code.read, (op1, op2, destino) -> destino + " = read"),
            Map.entry(Code.print, (op1, op2, destino) -> (op2 != null ? destino + " = \"" + op2 + "\"\n" : "") + "print " + destino),
            Map.entry(Code.param, (op1, op2, destino) -> "param " + destino + "(" + op1 + ")"),
            Map.entry(Code.rtn, (op1, op2, destino) -> "rtn" + (op1 != null ? " " + op1 : "")),
            Map.entry(Code.skip, (op1, op2, destino) -> destino + ": skip")
    );

    public Code getOpCode() {
        return code;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getDest() {
        return destino;
    }

    public static boolean opIsInt(String n) {
        return n.matches("[0-9]+") || n.matches("^-[0-9]+");
    }

    public static boolean opIsBoolean(String n) {
        return n.equals("true") || n.equals("false");
    }

    @Override
    public String toString() {
        InstructionFormatter formatter = FORMATTERS.get(code);
        if (formatter != null) {
            return formatter.format(op1, op2, destino);
        }
        return ""; 
    }

    @FunctionalInterface
    private interface InstructionFormatter {
        String format(String op1, String op2, String destino);
    }

}
