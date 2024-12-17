package day_13;

import java.io.File;
import java.util.List;
import utils.PuzzleUtils;

public class SolutionDay13
{
    public static void main(String[] args)
    {
        //star1();
        star2();
    }

    public static void star1()
    {
        List<Day13SingleInput> input = getInput();

        long tokenPrizeA = 3,
            tokenPrizeB = 1;
        
        long solution = 0;

        for(Day13SingleInput singleInput : input)
        {
            long cost = Long.MAX_VALUE;
            for (int i = 0; i < 101; i++)
            {
                for (int j = 0; j < 101; j++)
                {
                    long resX = i*singleInput.ax + j*singleInput.bx;
                    long resY = i*singleInput.ay + j*singleInput.by;
                    
                    if (resX == singleInput.px && resY == singleInput.py)
                    {
                        long currentCost = tokenPrizeA * i + tokenPrizeB * j;
                        if (currentCost < cost)
                        {
                            cost = currentCost;
                        }
                    }
                }
            }

            if (cost < Long.MAX_VALUE)
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

    public static void star2()
    {
        List<Day13SingleInput> input = getInput();
        long solution = 0;
        for (Day13SingleInput singleInput : input)
        {
            singleInput.px += 10000000000000L;
            singleInput.py += 10000000000000L;
            
            //Cramer's rule https://en.wikipedia.org/wiki/Cramer%27s_rule
            long i = (singleInput.px * singleInput.by - singleInput.bx * singleInput.py) / (singleInput.ax * singleInput.by - singleInput.bx * singleInput.ay);
            long j = (singleInput.py * singleInput.ax - singleInput.ay * singleInput.px) / (singleInput.ax * singleInput.by - singleInput.bx * singleInput.ay);

            if (singleInput.ax * i + singleInput.bx * j == singleInput.px && singleInput.ay * i + singleInput.by * j == singleInput.py)
            {
                solution += 3 * i + j;
            }
        }

        System.out.println(solution);
    }
}
