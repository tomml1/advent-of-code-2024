package day_11;

import utils.PuzzleUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import day_6.Tuple;

public class SolutionDay11
{
    public static void main(String[] args)
    {
        //star1();
        star2();
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

    public static void star2()
    {
        Map<Tuple<String, Integer>, Long> cache = new HashMap<>();

        String filePath = new File("").getAbsolutePath();
        String[] input = PuzzleUtils.readInputAsString(filePath + "/day_11/input.txt").split(" ");

        System.out.println(Arrays.toString(input));

        long solution = 0L;

        for (int i = 0; i < input.length; i++)
        {
            solution += blink(input[i], 75, cache);
        }

        System.out.println(solution);
    }

    public static Long blink(String stone, int iterations, Map<Tuple<String, Integer>, Long> cache)
    {
        if (iterations == 0)
        {
            return 1L;
        }
        Tuple<String, Integer> cacheEntry = new Tuple<String, Integer>(stone, iterations);
        if(cache.get(cacheEntry) == null)
        {
            long result = 0;
            if (Long.parseLong(stone) == 0)
            {
                result = blink("1", iterations - 1, cache);
            }
            else if (stone.length() % 2 == 0)
            {
                int mid = stone.length() / 2;
                result += blink(stone.substring(0, mid), iterations - 1, cache);
                result += blink(String.valueOf(Long.parseLong(stone.substring(mid))), iterations - 1, cache);
            }
            else
            {
                long newNumber = Long.parseLong(stone) * 2024;
                result = blink(String.valueOf(newNumber), iterations - 1, cache);
            }
            cache.put(cacheEntry, result);
        }
        return cache.get(cacheEntry);
    }
}
