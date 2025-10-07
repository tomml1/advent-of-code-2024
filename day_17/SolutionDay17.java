package day_17;

import utils.PuzzleUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SolutionDay17 {
    public static void main(String[] args)
    {
        System.out.println("Day 17: Chronospatial Computer");
        //star1();
        star2();
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

    public static void star2()
    {
        long sol = 0;
        for (int i = 0; i <= 15; i++) {
            sol += Math.pow(8, i) + 7;
        }

        if (Long.MAX_VALUE > sol) {
            System.out.println("works");
            System.out.println(sol);
        }

        // rechte Liste ist immer eins größer als linke Liste
        // program = 2,4,1,1,7,5,0,3,4,7,1,6,5,5,3,0
        // a0 = 7
        // a1 = 0
        // a2 = 2, 4
        // case a2 = 2
        // a3 = 6

        //                                                              program = 2,4,1,1,7,5,0,3,4,7,1,6,5,5,3,0
        List<Integer> possibleSolutions = recursiveSolver2(List.of(7,0,2,6,4,2,0,3,5,6,5,1,4,2,7), List.of(2,4,1,1,7,5,0,3,4,7,1,6,5,5,3,0));
        System.out.println("possibleSolutions: " + possibleSolutions);

        long base = 0;
        List<Integer> a_is = List.of(7,0,2,6,4,2,0,3,5,6,5,1,4,2,7,2);
        for (int i = a_is.size() - 1; i >= 0; i--) {
            base += (a_is.get(a_is.size() - 1 - i) * (long) Math.pow(8, i));
        }
        System.out.println("base: " + base);
        System.out.println(base < Long.MAX_VALUE);
        System.out.println("base " + base);
        System.out.println(decompiledProgram(base));
    }

    public static List<Integer> decompiledProgram(long registerInputA) {
        long registerA = registerInputA; // This is the variable we want to find
        long registerB = 0; // Initial value from input
        long registerC = 0; // Initial value from input
        List<Integer> output = new ArrayList<>();

        //System.out.println("Binary Register A: " + Integer.toBinaryString(registerA));
        //registerA = 25_358_015;
        do {
            registerB = (registerA & 0b111) ^ 0b001;
            registerC = registerA >> registerB;
            registerA = registerA >> 3;
            registerB = (registerB ^ registerC) ^ 0b110;

            Integer outputInteger = Math.toIntExact(registerB & 0b111);
            output.add(outputInteger);
        } while (registerA != 0);

        //System.out.println("Decompiled program result:");
        //System.out.println("Register A = " + registerA);
        //System.out.println("Register B = " + registerB);
        //System.out.println("Register C = " + registerC);
        //System.out.println("Output: " + output);
        return output;
    }

    public static List<Integer> recursiveSolver2(List<Integer> a_is, List<Integer> solveFor) {
        List<Integer> possibleSolutions = new ArrayList<>();
        long base = 0;


        for (int i = a_is.size() - 1; i >= 0; i--) {
            base += (a_is.get(a_is.size() - 1 - i) * (long) Math.pow(8, i + 1));
        }

        for (int i = 0; i <= 7; i++) {
            if (solveFor.equals(decompiledProgram(base + i))) {
                //wenn richtigen output gefunden
                possibleSolutions.add(i);
            }
        }

        return possibleSolutions;
    }
}
