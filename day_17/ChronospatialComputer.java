package day_17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChronospatialComputer {
    private int registerA;
    private int registerB;
    private int registerC;
    private List<Integer> program;
    private int instructionPointer = 0;
    private List<Integer> output = new ArrayList<>();
    Map<Integer, Instruction> instructions = new HashMap<>();

    public ChronospatialComputer(int registerA, int registerB, int registerC, List<Integer> program) {
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        this.program = program;
    }

    public int getRegisterA() {
        return registerA;
    }

    public void setRegisterA(int registerA) {
        this.registerA = registerA;
    }

    public int getRegisterB() {
        return registerB;
    }

    public void setRegisterB(int registerB) {
        this.registerB = registerB;
    }

    public int getRegisterC() {
        return registerC;
    }

    public void setRegisterC(int registerC) {
        this.registerC = registerC;
    }

    public List<Integer> getProgram() {
        return program;
    }

    public void setProgram(List<Integer> program) {
        this.program = program;
    }

    public int getInstructionPointer() {
        return instructionPointer;
    }

    public void setInstructionPointer(int instructionPointer) {
        this.instructionPointer = instructionPointer;
    }

    public List<Integer> getOutput() {
        return output;
    }

    public void setOutput(List<Integer> output) {
        this.output = output;
    }

    public void addToOutput(int value) {
        this.output.add(value);
    }

    public Map<Integer, Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(Map<Integer, Instruction> instructions) {
        this.instructions = instructions;
    }

    public void setupInstructions() {
        Instruction adv = new Instruction(0, "adv", OperandType.COMBO, (csp) -> {;
            long numerator = csp.getRegisterA();
            int operand = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand = csp.handleOperand(OperandType.COMBO, operand);
            long denominator = (long) Math.pow(2, handledOperand);
            int divisionResult = (int) (numerator / denominator);
            csp.setRegisterA(divisionResult);
            csp.setInstructionPointer(csp.getInstructionPointer() + 2);
            return null;
        });
        Instruction bxl = new Instruction(1, "bxl", OperandType.LITERAL, (csp) -> {;
            int operand1 = csp.getRegisterB();
            int operand2 = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand2 = csp.handleOperand(OperandType.LITERAL, operand2);
            int result = operand1 ^ handledOperand2;
            csp.setRegisterB(result);
            csp.setInstructionPointer(csp.getInstructionPointer() + 2);
            return null;
        });
        Instruction bst = new Instruction(2, "bst", OperandType.COMBO, (csp) -> {;
            int operand = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand = csp.handleOperand(OperandType.COMBO, operand);
            int result = handledOperand % 8;
            csp.setRegisterB(result);
            csp.setInstructionPointer(csp.getInstructionPointer() + 2);
            return null;
        });
        Instruction jnz = new Instruction(3, "jnz", OperandType.LITERAL, (csp) -> {;
            if (registerA == 0) {
                csp.setInstructionPointer(csp.getInstructionPointer() + 2);
                return null;
            }
            int operand = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand = csp.handleOperand(OperandType.LITERAL, operand);
            csp.setInstructionPointer(handledOperand);
            return null;
        });
        Instruction bxc = new Instruction(4, "bxc", OperandType.NO_TYPE, (csp) -> {;
            int operand = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand = csp.handleOperand(OperandType.NO_TYPE, operand);
            int result = csp.getRegisterB() ^ csp.getRegisterC();
            csp.setRegisterB(result);
            csp.setInstructionPointer(csp.getInstructionPointer() + 2);
            return null;
        });
        Instruction out = new Instruction(5, "out", OperandType.COMBO, (csp) -> {;
            int operand = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand = csp.handleOperand(OperandType.COMBO, operand);
            int result = handledOperand % 8;
            //System.out.print(result);
            csp.addToOutput(result);
            csp.setInstructionPointer(csp.getInstructionPointer() + 2);
            return null;
        });
        Instruction bdv = new Instruction(6, "bdv", OperandType.COMBO, (csp) -> {;
            long numerator = csp.getRegisterA();
            int operand = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand = csp.handleOperand(OperandType.COMBO, operand);
            long denominator = (long) Math.pow(2, handledOperand);
            int divisionResult = (int) (numerator / denominator);
            csp.setRegisterB(divisionResult);
            csp.setInstructionPointer(csp.getInstructionPointer() + 2);
            return null;
        });
        Instruction cdv = new Instruction(7, "cdv", OperandType.COMBO, (csp) -> {;
            long numerator = csp.getRegisterA();
            int operand = csp.getProgram().get(csp.getInstructionPointer() + 1);
            int handledOperand = csp.handleOperand(OperandType.COMBO, operand);
            long denominator = (long) Math.pow(2, handledOperand);
            int divisionResult = (int) (numerator / denominator);
            csp.setRegisterC(divisionResult);
            csp.setInstructionPointer(csp.getInstructionPointer() + 2);
            return null;
        });

        instructions.put(adv.getOpcode(), adv);
        instructions.put(bxl.getOpcode(), bxl);
        instructions.put(bst.getOpcode(), bst);
        instructions.put(jnz.getOpcode(), jnz);
        instructions.put(bxc.getOpcode(), bxc);
        instructions.put(out.getOpcode(), out);
        instructions.put(bdv.getOpcode(), bdv);
        instructions.put(cdv.getOpcode(), cdv);
    }

    public void runProgram() {
        while (instructionPointer < program.size()) {
            Instruction instruction = instructions.get(program.get(instructionPointer));
            System.out.println("Current instruction: " + instruction.getName() + " at position " + instructionPointer);
            instruction.getFunction().apply(this);
        }

        System.out.println("Program finished");
        System.out.println("Final state:\nRegister A = " + getRegisterA() + "\nRegister B = " + getRegisterB() + "\nRegister C = " + getRegisterC());
        String output = "";
        for (Integer i : getOutput()) {
            output += i + ",";
        }
        output = output.substring(0, output.length() - 1);
        System.out.println("Output: " + output);
    }

    public Integer handleOperand(OperandType operandType, Integer operand) {
        return switch (operandType) {
            case LITERAL, NO_TYPE -> operand;
            case COMBO -> {
                if (0 <= operand && operand < 4) {
                    yield operand;
                } else if (operand == 4) {
                    yield registerA;
                } else if (operand == 5) {
                    yield registerB;
                } else if (operand == 6) {
                    yield registerC;
                } else {
                    throw new IllegalArgumentException("Invalid operand: " + operand);
                }
            }
        };
    }
}
