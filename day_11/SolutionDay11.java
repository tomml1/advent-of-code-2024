package day_11;

import utils.PuzzleUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionDay11
{
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        //int number = Integer.parseInt("0010");
        //System.out.println(number == 10);
        String filePath = new File("").getAbsolutePath();
        String[] input = PuzzleUtils.readInputAsString(filePath + "/day_11/input.txt").split(" ");

        System.out.println(Arrays.toString(input));

        //String[] nextIteration = getNextIteration(input);

        //System.out.println(Arrays.toString(nextIteration));

        for (int i = 0; i < 25; i++)
        {
            input = getNextIteration(input);
            System.out.println("Iteration: " + i);
            System.out.println(Arrays.toString(input));
        }

        System.out.println(input.length);
    }

    public static String[] getNextIteration(String[] input)
    {
        List<String> nextIterationList = new ArrayList<String>();
        for (int i = 0; i < input.length; i++)
        {
            if (Long.parseLong(input[i]) == 0)
            {
                nextIterationList.add("1");
            }
            else if (input[i].length() % 2 == 0)
            {
                int mid = input[i].length() / 2;
                nextIterationList.add(input[i].substring(0, mid));
                nextIterationList.add(String.valueOf(Long.parseLong(input[i].substring(mid))));
            }
            else
            {
                long newNumber = Long.parseLong(input[i]) * 2024;
                nextIterationList.add(String.valueOf(newNumber));
            }
        }
        return nextIterationList.toArray(new String[nextIterationList.size()]);
    }
}
