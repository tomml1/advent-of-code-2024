package day_9;

import utils.PuzzleUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionDay9
{
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String input = PuzzleUtils.readInputAsString(filePath + "/day_9/input.txt");
        List<String> parsedInputAsList = new ArrayList<>();
        int currentNumber = 0;

        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (i % 2 == 0)
            {
                parsedInputAsList.addAll(Collections.nCopies(Integer.parseInt(String.valueOf(c)), String.valueOf(currentNumber)));
                currentNumber++;
            }
            else
            {
                parsedInputAsList.addAll(Collections.nCopies(Integer.parseInt(String.valueOf(c)), "."));
            }
        }

        int left = 0;
        int right = parsedInputAsList.size() - 1;

        while (left < right)
        {
            if (parsedInputAsList.get(left).equals(".") && !parsedInputAsList.get(right).equals("."))
            {
                parsedInputAsList.set(left, parsedInputAsList.get(right));
                parsedInputAsList.set(right, ".");
                left++;
                right--;
            }
            else
            {
                if (!parsedInputAsList.get(left).equals("."))
                {
                    left++;
                }
                if (parsedInputAsList.get(right).equals("."))
                {
                    right--;
                }
            }
        }

        int index = 0;
        long solution = 0;
        while (!parsedInputAsList.get(index).equals("."))
        {
            solution += Long.parseLong(parsedInputAsList.get(index)) * index;
            index++;
        }

        System.out.println("Ergebnis: " + solution);
    }
}
