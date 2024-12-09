package day_9;

import utils.PuzzleUtils;

import java.io.File;

public class SolutionDay9
{
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String input = PuzzleUtils.readInputAsString(filePath + "/day_9/testinput.txt");
        String parsedInput = "";
        int currentNumber = 0;

        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (i % 2 == 0)
            {
                parsedInput += String.valueOf(currentNumber).repeat(Integer.parseInt(String.valueOf(c)));
                currentNumber++;
            }
            else
            {
                parsedInput += ".".repeat(Integer.parseInt(String.valueOf(c)));
            }
        }

        System.out.println(parsedInput);
    }
}
