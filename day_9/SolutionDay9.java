package day_9;

import utils.PuzzleUtils;

import java.io.File;
import java.util.*;
import day_9.FreeSpace;

public class SolutionDay9
{
    public static void main(String[] args)
    {
        //star1();
        star2();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String input = PuzzleUtils.readInputAsString(filePath + "/day_9/input.txt");
        List<String> parsedInputAsList = getParsedInput(input);

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

    public static List<String> getParsedInput(String input)
    {
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
        return parsedInputAsList;
    }

    public static void star2()
    {
        String filePath = new File("").getAbsolutePath();
        String input = PuzzleUtils.readInputAsString(filePath + "/day_9/testinput.txt");
        LinkedList<Segment> parsedInputAsList = getInputAsSegmentList(input);

        int right = parsedInputAsList.size() - 1;

        while(right >= 0)
        {
            int left = 0;
            Segment currentRight = parsedInputAsList.get(right);
            if (currentRight instanceof FreeSpace)
            {
                right--;
                continue;
            }
            
            Segment currentLeft = parsedInputAsList.get(left);

            while(currentLeft != currentRight)
            {
                if (currentLeft instanceof FreeSpace && currentRight instanceof FileBlock)
                {
                    int sizeDifference = currentLeft.size - currentRight.size;
                    if (sizeDifference >= 0)
                    {
                        parsedInputAsList.set(left, currentRight);
                        if (sizeDifference > 0)
                        {
                            FreeSpace spaceToRight = new FreeSpace(currentRight.getSize());
                            parsedInputAsList.set(right, spaceToRight);
                            FreeSpace remainingSpace = new FreeSpace(sizeDifference);
                            parsedInputAsList.add(left + 1, remainingSpace);
                            right += 1;
                        }
                        else
                        {
                            parsedInputAsList.set(right, currentLeft);
                        }
                        break;
                    }
                }
                left++;
                currentLeft = parsedInputAsList.get(left);
            }
            right--;
        }
        System.out.println(getStringRepresentation(parsedInputAsList));
    }

    public static LinkedList<Segment> getInputAsSegmentList(String input)
    {
        LinkedList<Segment> parsedInputAsList = new LinkedList<>();
        int currentNumber = 0;

        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (i % 2 == 0)
            {
                FileBlock fb = new FileBlock(Integer.parseInt(String.valueOf(c)), String.valueOf(currentNumber));
                parsedInputAsList.add(fb);
                currentNumber++;
            }
            else
            {
                FreeSpace fs = new FreeSpace(Integer.parseInt(String.valueOf(c)));
                parsedInputAsList.add(fs);
            }
        }
        return parsedInputAsList;
    }

    public static String getStringRepresentation(List<Segment> list)
    {
        String stringRepresentation = "";
        for (Segment segment: list)
        {
            stringRepresentation += segment.getContent().repeat(segment.getSize());
        }

        return stringRepresentation;
    }
}
