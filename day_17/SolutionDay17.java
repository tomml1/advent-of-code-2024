package day_17;

import utils.PuzzleUtils;

import java.io.File;

public class SolutionDay17 {
    public static void main(String[] args)
    {
        System.out.println("Day 17: Chronospatial Computer");
        star1();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String filename = "testinput.txt";
        String wholePath = filePath + "/day_17/" + filename;
        ChronospatialComputer csp = PuzzleUtils.getChronospatialComputer(wholePath);
        System.out.println("Chronospatial Computer initial state:\nRegister A = " + csp.getRegisterA() + "\nRegister B = " + csp.getRegisterB() + "\nRegister C = " + csp.getRegisterC());
        System.out.println("Program: " + csp.getProgram());
    }
}
