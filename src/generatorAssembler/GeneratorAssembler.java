package generatorAssembler;

import backend.BackTables;
import backend.Proc;
import backend.Var;
import c3a.GeneratorC3A;
import c3a.InstructionC3A;
import c3a.InstructionC3A.Code;
import errores.SymTabError;
import symbolsTable.SymbolsTable;
import symbolsTable.Type;
import symbolsTable.Type.Tipo;
import symbolsTable.Type.TipoSubyacente;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * GAS - Gnu Assembler constructor
 */
public class GeneratorAssembler {

    private BufferedWriter writer;
    private String PATH = "src\\output\\";
    // Symbols Table
    private SymbolsTable symbolsTable;
    // TS + TV
    private BackTables backend;

    private GeneratorC3A c3a_g;
    private StringBuilder assemblyBuilder = new StringBuilder();
    private final EnumMap<Code, Consumer<InstructionC3A>> instructionHandlers = new EnumMap<>(Code.class);
    private Set<String> requiredComparers = new HashSet<>();
    private Boolean HasPrint = false;

    /**
     * Constructor for GeneratorAssembler
     * @param symbolTable the symbols table
     * @param backend the backend tables
     * @param c3a_g the C3A generator
     * @param filename the output filename
     */
    public GeneratorAssembler(SymbolsTable symbolTable, BackTables backend, GeneratorC3A c3a_g, String filename) {
        this.symbolsTable = symbolTable;
        this.backend = backend;
        this.c3a_g = c3a_g;
        this.PATH += filename +"\\"+ "AssemblerCode.s";
        initializeHandlers();
    }

    /**
     * Generates the assembler code and writes it to a file
     */
    public void generateAssembler() {
        try {
            File GASfile = new File(PATH);
            if (!GASfile.exists()) {
                GASfile.createNewFile();
            }
            writeToOutput(GASfile);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneratorAssembler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: CANNOT WRITE IN FILE");
        } catch (IOException e) {
            System.out.println("ERROR: CANNOT CREATE ASSEMBLY FILE");
        } catch (Exception e) {
            System.out.println("UNEXPECTED ERROR: " + e.getMessage());
        }
    }

    /**
     * Writes the generated assembler code to the output file
     * @param fileGAS the output file
     * @throws IOException if an I/O error occurs
     * @throws SymTabError if a symbol table error occurs
     */
    private void writeToOutput(File fileGAS) throws IOException, SymTabError {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileGAS), StandardCharsets.UTF_8));

        headerWrite();
        // Convert from C3A to Assembly
        for (InstructionC3A ins : c3a_g.getAllC3DirInstr()) {
            toAssembly(ins);
        }
        footerWrite();
        // Write to file
        writer.write(assemblyBuilder.toString());
        writer.close();
    }

    /**
     * Writes a line to the assembly builder
     * @param input the line to write
     */
    private void writeLine(String input) {
        assemblyBuilder.append(input).append('\n');
    }

    /**
     * Adds a comment line to the assembly builder
     * @param instruction the instruction to comment
     */
    public void commentLine(InstructionC3A instruction) {
        assemblyBuilder.append("# ").append(instruction.toString().replace("\n", "\n# ")).append('\n');
    }


    /**
     * Initializes the handlers for different C3A instructions
     */
    private void initializeHandlers() {
            // Basic instructions
            instructionHandlers.put(Code.skip, this::skipInstruction);
            instructionHandlers.put(Code.rtn, this::returnInstruction);
            instructionHandlers.put(Code.go_to, i -> writeLine("jmp " + i.getDest()));

            // Arithmetic operations
            instructionHandlers.put(Code.add, i -> calculateSumRes(i, "add"));
            instructionHandlers.put(Code.sub, i -> calculateSumRes(i, "sub"));
            instructionHandlers.put(Code.mod, i -> calculateDivision(i, "idiv", 2));
            instructionHandlers.put(Code.prod, i -> calculateMulu(i, "imul"));
            instructionHandlers.put(Code.div, i -> calculateDivision(i, "idiv", 1));

            // Function handling
            instructionHandlers.put(Code.call, this::callInstruction);
            instructionHandlers.put(Code.param, this::paramInstruction);
            instructionHandlers.put(Code.pmb, this::pmbInstruction);
            instructionHandlers.put(Code.copy, this::copyInstruction);

            // Control flow
            instructionHandlers.put(Code.jump_cond, this::jumpCondInstruction);

            // Logic operations
            instructionHandlers.put(Code.and, this::logicalInstruction);
            instructionHandlers.put(Code.or, this::logicalInstruction);
            instructionHandlers.put(Code.not, this::unaryInstruction);
            instructionHandlers.put(Code.neg, this::unaryInstruction);

            // Comparisons
            instructionHandlers.put(Code.LT, i -> substractCMP(i, Code.LT));
            instructionHandlers.put(Code.LE, i -> substractCMP(i, Code.LE));
            instructionHandlers.put(Code.EQ, i -> substractCMP(i, Code.EQ));
            instructionHandlers.put(Code.NE, i -> substractCMP(i, Code.NE));
            instructionHandlers.put(Code.GE, i -> substractCMP(i, Code.GE));
            instructionHandlers.put(Code.GT, i -> substractCMP(i, Code.GT));

            // I/O operations
            instructionHandlers.put(Code.read, this::inputInstruction);
            instructionHandlers.put(Code.print, i -> {
                HasPrint = true;
                outputInstruction(i);
            });

    }

    /**
     * Converts a C3A instruction to assembly
     * @param instruction the C3A instruction
     * @throws SymTabError if a symbol table error occurs
     */
    public void toAssembly(InstructionC3A instruction) throws SymTabError {
        commentLine(instruction);
        Consumer<InstructionC3A> handler = instructionHandlers.get(instruction.getOpCode());
        if (handler != null) {
            handler.accept(instruction);
        } else {
            throw new SymTabError("No handler found for instruction: " + instruction.getOpCode());
        }

        writeLine("");
    }

    /**
     * Generates the header of the assembly program
     */
    private void headerWrite() {
        writeLine(".global main");
        // C functions declaration
        writeLine(".extern printf, scanf");
        writeLine(".data");
        declareStringVariables();
        writeLine(".text");
    }

    /**
     * Generates the footer of the assembly program
     */
    private void footerWrite() {
        writeLine("# exit");
        writeLine("\n# auxiliar functions");
        writeCMPFunctions();
        writePrintBoolFunction();
    }

    /**
     * Declares string variables in the assembly program
     */
    private void declareStringVariables() {
        // Only declare as global string values
        for (Var var : backend.getAllVars()) {
            if (var.getType() == TipoSubyacente.TS_STRING) {
                writeLine(var.getName() + ": .asciz " + "\"" + var.getValue() + "\"");
            }
        }
        //Print formats for int and boolean.
        writeLine("format_int: .asciz \"%d\"");
        writeLine("true_label : .asciz \"true\"");
        writeLine("false_label : .asciz \"false\"");
    }

    /**
     * Writes the print boolean function in the assembly program
     */
    private void writePrintBoolFunction() {
        if(!HasPrint) return;

        writeLine("print_bool :");
        writeLine("cmpw $0,%di");
        writeLine("je print_false");
        writeLine("mov $true_label, %rdi");
        writeLine("jmp print_bool_val");
        writeLine("print_false : mov $false_label, %rdi");
        writeLine("print_bool_val : xor %rax, %rax");
        writeLine("call printf");
        writeLine("ret");

    }

    /**
     * Handles the conditional jump instruction
     * @param instruction the C3A instruction
     */
    private void jumpCondInstruction(InstructionC3A instruction) {

        writeLine("cmpw $1," + backend.getVarAssembler(instruction.getOp1()));
        writeLine("je " + instruction.getDest());
    }

    /**
     * Handles the input instruction
     * @param instruction the C3A instruction
     */
    private void inputInstruction(InstructionC3A instruction) {
        String dest = backend.getVarAssembler(instruction.getDest());
        writeLine("xor %rax, %rax");
        writeLine("mov $format_int, %rdi");
        writeLine("leaq " + dest + ", %rsi");
        writeLine("call scanf");
    }

    /**
     * Handles unary instructions (not, neg)
     * @param instruction the C3A instruction
     */
    private void unaryInstruction(InstructionC3A instruction) {
        Var variable = backend.getVar(instruction.getOp1());
        String suffix = "l";
        String register = "%edi";
        if (variable.getType() == Type.TipoSubyacente.TS_BOOLEAN) {
            suffix = "w";
            register = "%di";
        }
        writeLine("mov" + suffix + " " + variable.getDirection() + ", " + register);
        writeLine(instruction.getOpCode() + suffix + " " + register);
        writeLine("mov" + suffix + " " + register + ", " + backend.getVarAssembler(instruction.getDest()));
    }

    /**
     * Handles logical instructions (and, or)
     * @param instruction the C3A instruction
     */
    private void logicalInstruction(InstructionC3A instruction) {
        // For sure that are boolean values
        // AND or OR
        writeLine("movw " + backend.getVarAssembler(instruction.getOp1()) + ", %di");
        writeLine("movw " + backend.getVarAssembler(instruction.getOp2()) + ", %ax");
        writeLine(instruction.getOpCode() + "w" + " %ax, %di");
        writeLine("movw %di, " + backend.getVarAssembler(instruction.getDest()));
    }

    /**
     * Handles the output instruction
     * @param instruction the C3A instruction
     */
    private void outputInstruction(InstructionC3A instruction) {
        /* when we call output, op1 stores type of dest in string format */
        if (instruction.getOp1().equals(Type.TipoSubyacente.TS_NUMBER.toString())) {
            writeLine("mov $format_int, %rdi");
            writeLine("xor %rsi, %rsi");
            writeLine("movl " + backend.getVarAssembler(instruction.getDest()) + ", %esi");
            writeLine("xor %rax, %rax");
            writeLine("call printf");
        }
        if (instruction.getOp1().equals(Type.TipoSubyacente.TS_STRING.toString())) {
            writeLine("mov $" + instruction.getDest() + ", %rdi");
            writeLine("xor %rax, %rax");
            writeLine("call printf");
        }
        if (instruction.getOp1().equals(Type.TipoSubyacente.TS_BOOLEAN.toString())) {
            writeLine("movw " + backend.getVarAssembler(instruction.getDest()) + ", %di");
            writeLine("call print_bool");
        }
    }

    /**
     * Handles the skip instruction
     * @param instruction the C3A instruction
     */
    private void skipInstruction(InstructionC3A instruction) {
        writeLine(instruction.getDest() + ':');
    }

    /**
     * Handles the return instruction
     * @param instruction the C3A instruction
     */
    private void returnInstruction(InstructionC3A instruction) {
        // is function with return value, op1 register that stores return value
        if (instruction.getOp1() != null) {
            Var variable = backend.getVar(instruction.getOp1());
            writeLine("# Moving function result into %eax or %ax");
            String suffix = "l";
            String register = "%eax";
            if (variable.getType() == Type.TipoSubyacente.TS_BOOLEAN) {
                suffix = "w";
                register = "%ax";
            }
            writeLine("mov" + suffix + " " + variable.getDirection() + ", " + register);
        }

        // delete all reservated space
        Proc proc = backend.getProc(instruction.getDest());
        writeLine("# Delete all reserved space");
        writeLine("addq $" + proc.getMemorySize() + ", %rsp");
        writeLine("leave");
        writeLine("ret");
    }

    /**
     * Handles arithmetic calculations (sum and subtraction)
     * @param instruction the C3A instruction
     * @param type the type of arithmetic operation (add or sub)
     */
    private void calculateSumRes(InstructionC3A instruction, String type) {
        String op1 = GetOperand(instruction, 1);
        String op2 = GetOperand(instruction, 2);
        // For sure that are
        writeLine("movl " + op1 + ", %edi");
        writeLine("movl " + op2 + ", %eax");
        writeLine(type + "l" + " %eax, %edi");
        writeLine("movl %edi, " + backend.getVarAssembler(instruction.getDest()));
    }

    /**
     * Handles division and modulus calculations
     * @param instruction the C3A instruction
     * @param type the type of division operation (idiv)
     * @param code the code indicating division (1) or modulus (2)
     */
    private void calculateDivision(InstructionC3A instruction, String type, int code) {
        String op1 = GetOperand(instruction, 1);
        String op2 = GetOperand(instruction, 2);
        writeLine("movl " + op1 + ", %eax");
        writeLine("cdq");
        writeLine("movl " + op2 + ", %edi");
        writeLine(type + "l" + " %edi");
        checkDivisionStatus(instruction, code);
    }


    /**
     * Checks the status of the division operation
     * @param instruction the C3A instruction
     * @param code the code indicating division (1) or modulus (2)
     */
    private void checkDivisionStatus(InstructionC3A instruction, int code) {
        if (code == 1) {
            //Division
            writeLine("movl %eax, " + backend.getVarAssembler(instruction.getDest()));
        } else if (code == 2) {
            //modulus
            writeLine("movl %edx, " + backend.getVarAssembler(instruction.getDest()));
        }
    }

    /**
     * Handles multiplication calculations
     * @param instruction the C3A instruction
     * @param type the type of multiplication operation (imul)
     */
    private void calculateMulu(InstructionC3A instruction, String type) {
        String op1 = GetOperand(instruction, 1);
        String op2 = GetOperand(instruction, 2);
        writeLine("movl " + op1 + ", " + "%edi");
        writeLine("movl " + op2 + ", " + "%eax");
        writeLine(type + "l" + " %eax" + ", %edi");
        writeLine("movl %edi, " + backend.getVarAssembler(instruction.getDest()));
    }

    /**
     * Gets the operand for the instruction
     * @param instruction the C3A instruction
     * @param op the operand number (1 or 2)
     * @return the operand as a string
     */
    private String GetOperand(InstructionC3A instruction, int op){
        if(op == 1){
            return InstructionC3A.opIsInt(instruction.getOp1()) ? "$" + instruction.getOp1() : backend.getVarAssembler(instruction.getOp1());
        } else if (op == 2) {
            return InstructionC3A.opIsInt(instruction.getOp2()) ? "$" + instruction.getOp2() : backend.getVarAssembler(instruction.getOp2());
        }
        return "";
    }

    /**
     * Handles the call instruction
     * @param instruction the C3A instruction
     */
    private void callInstruction(InstructionC3A instruction) {
        writeLine("xor %rax, %rax   # clean return register");
        writeLine("call " + instruction.getDest());
        writeLine("# pop all params");
        int numParams = Integer.parseInt(instruction.getOp1());
        for (int i = 0; i < numParams; i++) {
            writeLine("pop %rdx");
        }
    }

    /**
     * Handles the parameter instruction
     * @param instruction the C3A instruction
     */
    private void paramInstruction(InstructionC3A instruction) {
        Var variable = backend.getVar(instruction.getOp1());
        String code = "movslq";
        if (variable.getType() == Type.TipoSubyacente.TS_BOOLEAN) {
            code = "movswq";
        }
        writeLine(code + " " + variable.getDirection() + ", %rdx");
        writeLine("push %rdx");
    }

    /**
     * Handles comparison instructions
     * @param instruction the C3A instruction
     * @param type the type of comparison (LT, LE, EQ, NE, GE, GT)
     */
    private void substractCMP(InstructionC3A instruction, Code type) {
        boolean isNumCmp;
        if (InstructionC3A.opIsInt(instruction.getOp1()) || InstructionC3A.opIsInt(instruction.getOp2())) {
            isNumCmp = true;
        } else {
            Var var = backend.getVar(instruction.getOp1());
            if (var == null) {
                var = backend.getVar(instruction.getOp2());
            }
            isNumCmp = var.getType() == Type.TipoSubyacente.TS_NUMBER;
        }
        String suffix = "l";
        String register1 = "%edi";
        String register2 = "%esi";
        if (!isNumCmp) {
            suffix = "w";
            register1 = "%di";
            register2 = "%si";
        }
        writeLine("mov" + suffix + " " + checkLiteral(instruction.getOp1()) + ", " + register1);
        writeLine("mov" + suffix + " " + checkLiteral(instruction.getOp2()) + ", " + register2);
        writeLine("xor %rax, %rax # clean return value register");
        String functionLabel = getCMPFunctionLabel(type, isNumCmp);
        writeLine("call " + functionLabel);
        writeLine("movw %ax," + backend.getVarAssembler(instruction.getDest()) + " # get return value");
    }

    /**
     * Checks if the operand is a literal (integer or boolean) and returns its assembly representation.
     * @param operand the operand to check
     * @return the assembly representation of the operand
     */
    private String checkLiteral(String operand) {
        boolean opInt = InstructionC3A.opIsInt(operand);
        boolean opBool = InstructionC3A.opIsBoolean(operand);
        // number
        if (opInt) {
            return "$" + operand;
        }
        // boolean
        if (opBool) {
            // true
            if (operand.equals("true")) {
                return "$1";
            }
            // false
            return "$0";
        }
        return backend.getVarAssembler(operand);
    }

    /**
     * Generates the assembly code for the copy instruction.
     * @param instruction the C3A instruction
     */
    private void copyInstruction(InstructionC3A instruction) {
        if (instruction.getOp1().equals("return")) {
            Proc procedure = backend.getProc(instruction.getOp2());
            String suffix = "l";
            String register = "%eax";
            if (procedure.getType() == Type.TipoSubyacente.TS_BOOLEAN) {
                suffix = "w";
                register = "%ax";
            }
            writeLine("mov" + suffix + " " + register + ", " + backend.getVarAssembler(instruction.getDest()));
        } else {
            Var variable = backend.getVar(instruction.getDest());
            boolean isNum = variable.getType() == Type.TipoSubyacente.TS_NUMBER;
            String suffix = "l";
            String register = "%edi";
            if (!isNum) {
                suffix = "w";
                register = "%di";
            }
            writeLine("mov" + suffix + " " + checkLiteral(instruction.getOp1()) + ", " + register);
            writeLine("mov" + suffix + " " + register + ", " + backend.getVarAssembler(instruction.getDest()));
        }
    }

    /**
     * Generates the assembly code for the procedure prologue.
     * @param instruction the C3A instruction
     */
    private void pmbInstruction(InstructionC3A instruction) {
        writeLine("push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.");
        writeLine("mov %rsp, %rbp");
        //Declarar parametros del procedimiento como variables.
        String backFunId = instruction.getDest();
        String funId = backFunId.replace("PROC_", "");
        Type type = null;
        try {
            type = symbolsTable.get(funId);
        }catch (SymTabError e){
            System.out.println("ERROR: " + e.getMessage());
        }

        if (type == null || type.getTipo() != Tipo.dfun) {
            throw new Error("Invalid function");
        }

        //save space for local variables
        Proc proc = backend.getProc(backFunId);

        int procsize = proc.getMemorySize();
        int alignedSize = (procsize + 15) & ~15;
        //si alineamos al memoria a 16 funciona
        writeLine("sub $" + alignedSize + ", %rsp");
    }

    /**
     * Gets the label for the comparison function based on the comparison type and whether it is a numeric comparison.
     * @param code the comparison type
     * @param numCmp whether it is a numeric comparison
     * @return the label for the comparison function
     */
    private String getCMPFunctionLabel(Code code, boolean numCmp) {
        String base = switch (code) {
            case EQ -> numCmp ? "CMP_EQ_NUM" : "CMP_EQ";
            case NE -> numCmp ? "CMP_NE_NUM" : "CMP_NE";
            case LT -> "CMP_LT";
            case LE -> "CMP_LE";
            case GT -> "CMP_GT";
            case GE -> "CMP_GE";
            default -> "";
        };
        requiredComparers.add(base);
        return base;
    }

    /**
     * Writes the comparison functions to the assembly code.
     */
    private void writeCMPFunctions() {
        // Using LinkedHashSet for insertion-order iteration
        new LinkedHashSet<>(requiredComparers).forEach(funcName -> {
            if (CMP_FUNCTIONS.containsKey(funcName)) {
                generateComparisonFunction(funcName);
            }
        });
    }

    /**
     * Configuration for comparison functions. Helper record
     */
    private record CMPConfig(String jumpInstruction, String reg1, String reg2) {}

    /**
     * Map of comparison functions to their parameters.
     */
    private static final Map<String, CMPConfig> CMP_FUNCTIONS = Map.ofEntries(
            Map.entry("CMP_LT", new CMPConfig("jge", "esi", "edi")),
            Map.entry("CMP_LE", new CMPConfig("jg", "esi", "edi")),
            Map.entry("CMP_GT", new CMPConfig("jle", "esi", "edi")),
            Map.entry("CMP_GE", new CMPConfig("jl", "esi", "edi")),
            Map.entry("CMP_EQ", new CMPConfig("jne", "di", "si")),
            Map.entry("CMP_EQ_NUM", new CMPConfig("jne", "edi", "esi")),
            Map.entry("CMP_NE", new CMPConfig("je", "di", "si")),
            Map.entry("CMP_NE_NUM", new CMPConfig("je", "edi", "esi"))
    );

    /**
     * Generates the assembly code for a comparison function.
     * @param funcName the name of the comparison function
     */
    private void generateComparisonFunction(String funcName) {
        CMPConfig config = CMP_FUNCTIONS.get(funcName);
        String falseLabel = funcName + "_FALSE";

        writeLine("# " + funcName.replace("_", " ") + " comparison");
        writeLine(funcName + ":");
        writeLine("\tcmp %" + config.reg1() + ", %" + config.reg2());
        writeLine("\t" + config.jumpInstruction() + " " + falseLabel);
        writeLine("\tmov $1, %ax");
        writeLine("\tret");
        writeLine(falseLabel + ":");
        writeLine("\tmov $0, %ax");
        writeLine("\tret\n");
    }
}
