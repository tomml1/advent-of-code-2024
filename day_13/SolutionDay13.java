package day_13;

import java.io.File;
import java.util.List;
import utils.PuzzleUtils;

public class SolutionDay13
{
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        List<Day13SingleInput> input = getInput();
        //should be 320
        System.out.println(input.size());
    }

    public static List<Day13SingleInput> getInput()
    {
        String filePath = new File("").getAbsolutePath();
        List<Day13SingleInput> input = PuzzleUtils.getInputForDay13(filePath + "/day_13/input.txt");
        return input;
    }
}
