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

        int tokenPrizeA = 3,
            tokenPrizeB = 1;
        
        int solution = 0;

        for(Day13SingleInput singleInput : input)
        {
            int cost = Integer.MAX_VALUE;
            for (int i = 0; i < 101; i++)
            {
                for (int j = 0; j < 101; j++)
                {
                    int resX = i*singleInput.ax + j*singleInput.bx;
                    int resY = i*singleInput.ay + j*singleInput.by;
                    
                    if (resX == singleInput.px && resY == singleInput.py)
                    {
                        int currentCost = tokenPrizeA * i + tokenPrizeB * j;
                        if (currentCost < cost)
                        {
                            cost = currentCost;
                        }
                    }
                }
            }

            if (cost < Integer.MAX_VALUE)
            {
                solution += cost;
            }
        }

        System.out.println(solution);
    }

    public static List<Day13SingleInput> getInput()
    {
        String filePath = new File("").getAbsolutePath();
        List<Day13SingleInput> input = PuzzleUtils.getInputForDay13(filePath + "/day_13/input.txt");
        return input;
    }
}
