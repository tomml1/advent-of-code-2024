package day_17;

import java.util.function.Function;

public class Instruction {
    private int opcode;
    private String name;
    private OperandType operandType;
    private Function<ChronospatialComputer, Void> function;

    public Instruction(int opcode, String name, OperandType operandType, Function<ChronospatialComputer, Void> function) {
        this.opcode = opcode;
        this.name = name;
        this.operandType = operandType;
        this.function = function;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperandType getOperandType() {
        return operandType;
    }

    public void setOperandType(OperandType operandType) {
        this.operandType = operandType;
    }

    public Function<ChronospatialComputer, Void> getFunction() {
        return function;
    }

    public void setFunction(Function<ChronospatialComputer, Void> function) {
        this.function = function;
    }
}
