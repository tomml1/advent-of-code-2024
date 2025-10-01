package day_17;

import java.util.List;

public class ChronospatialComputer {
    private int registerA;
    private int registerB;
    private int registerC;
    private List<Integer> program;

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
}
