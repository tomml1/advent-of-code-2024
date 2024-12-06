package day_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolutionDay1
{
    public static void main(String[] args)
    {
        //star1();
        star2();
    }

    public static void star1()
    {
        int distance = 0;
        List<Integer> leftArray = new ArrayList<Integer>();
        List<Integer> rightArray = new ArrayList<Integer>();

        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-1\\input.txt")))
        {
            String line = br.readLine();

            while (line != null)
            {
                String[] splitLine = line.split("\\s+");
                leftArray.add(Integer.parseInt(splitLine[0]));
                rightArray.add(Integer.parseInt(splitLine[1]));
                line = br.readLine();
            }

            Collections.sort(leftArray);
            Collections.sort(rightArray);

            for (int i = 0; i < leftArray.size(); i++)
            {
                distance += Math.abs(rightArray.get(i) - leftArray.get(i));
            }
            
            System.out.println("Distance: " + distance);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static void star2()
    {
        int distance = 0;
        List<Integer> leftArray = new ArrayList<Integer>();
        List<Integer> rightArray = new ArrayList<Integer>();

        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-1\\input.txt")))
        {
            String line = br.readLine();
            while (line != null)
            {
                String[] splitLine = line.split("\\s+");
                leftArray.add(Integer.parseInt(splitLine[0]));
                rightArray.add(Integer.parseInt(splitLine[1]));
                line = br.readLine();
            }

            Map<Integer, Long> counts = rightArray.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            for (int i = 0; i < leftArray.size(); i++)
            {
                int number = leftArray.get(i);
                distance += number * counts.getOrDefault(number, 0L);
            }
            
            System.out.println("Distance: " + distance);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
}