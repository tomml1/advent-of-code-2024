package day_17;

import utils.PuzzleUtils;

import java.io.File;
import java.util.List;

public class SolutionDay17 {
    public static void main(String[] args)
    {
        System.out.println("Day 17: Chronospatial Computer");
        star1();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String filename = "input.txt";
        String wholePath = filePath + "/day_17/" + filename;
        ChronospatialComputer csp = PuzzleUtils.getChronospatialComputer(wholePath);
        //csp = new ChronospatialComputer(0,0,9, List.of(2,6));
        //csp = new ChronospatialComputer(10,0,0, List.of(5,0,5,1,5,4));
        //csp = new ChronospatialComputer(2024,0,0, List.of(0,1,5,4,3,0));
        //csp = new ChronospatialComputer(0,29,0, List.of(1,7));
        //csp = new ChronospatialComputer(0,2024,43690, List.of(4,0));
        System.out.println("Chronospatial Computer initial state:\nRegister A = " + csp.getRegisterA() + "\nRegister B = " + csp.getRegisterB() + "\nRegister C = " + csp.getRegisterC());
        System.out.println("Program: " + csp.getProgram());

        csp.setupInstructions();
        csp.runProgram();
    }
}
