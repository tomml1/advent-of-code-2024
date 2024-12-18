package day_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public class SolutionDay2
{
    public static void main(String[] args)
    {
        star1();
        star2();
    }

    public static void star1()
    {
        Map<String, Integer> solutionMap = new HashMap<>(Map.ofEntries(
            entry("safe", 0),
            entry("unsafe", 0)
        ));

        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-2\\input.txt")))
        {
            String line = br.readLine();

            while (line != null)
            {
                int[] splitLine = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                //System.out.println(Arrays.toString(splitLine));

                int lineSize = splitLine.length;

                if (lineSize < 2)
                {
                    throw new IllegalArgumentException();
                }
                else 
                {
                    if (lineSafe(splitLine))
                    {
                        solutionMap.put("safe", solutionMap.get("safe") + 1);
                    }
                    else
                    {
                        solutionMap.put("unsafe", solutionMap.get("unsafe") + 1);
                    }
                }

                line = br.readLine();
            }

            System.out.println("Safe: " + solutionMap.toString());
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static boolean lineSafe(int[] splitLine)
    {
        int lineSize = splitLine.length;
        int checkIfOnlyDecreasingOrIncreasing = 0;

        for (int i = 0; i < lineSize - 1; i++)
        {
            int difference = splitLine[i] - splitLine[i + 1];

            if (difference == 0)
            {
                return false;
            }

            checkIfOnlyDecreasingOrIncreasing += difference < 0 ? 1 : -1;
            boolean correctDistance = 1 <= Math.abs(difference) && Math.abs(difference) <= 3;

            if (!correctDistance)
            {
                return false;
            }
        }

        if (Math.abs(checkIfOnlyDecreasingOrIncreasing) != (lineSize - 1))
        {
            return false;
        }

        return true;
    }

    public static void star2()
    {
        Map<String, Integer> solutionMap = new HashMap<>(Map.ofEntries(
            entry("safe", 0),
            entry("unsafe", 0)
        ));

        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-2\\input.txt")))
        {
            String line = br.readLine();

            while (line != null)
            {
                int[] splitLine = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                //System.out.println(Arrays.toString(splitLine));

                int lineSize = splitLine.length;

                if (lineSize < 2)
                {
                    throw new IllegalArgumentException();
                }
                else 
                {
                    if (lineSafeDampener(splitLine))
                    {
                        solutionMap.put("safe", solutionMap.get("safe") + 1);
                    }
                    else
                    {
                        solutionMap.put("unsafe", solutionMap.get("unsafe") + 1);
                    }
                }

                line = br.readLine();
            }

            System.out.println("Safe: " + solutionMap.toString());
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static int[] remove(int[] arr, int in) {
        int[] arr2 = new int[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == in)
            {
                continue;
            }

            arr2[k++] = arr[i];
        }

        return arr2;
    }

    public static boolean lineSafeDampener(int[] splitLine)
    {
        int i = 0;
        if (lineSafe(splitLine))
        {
            return true;
        }
        while (i < splitLine.length) {
            if (lineSafe(remove(splitLine, i)))
            {
                return true;
            }
            i++;
        }
        return false;
    }
}