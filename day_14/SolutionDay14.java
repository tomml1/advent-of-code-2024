package day_14;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.PuzzleUtils;

import static java.util.Map.entry;

public class SolutionDay14
{
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        List<PointWithVelocity> input = getInput();
        int width = 101, //x
            length = 103, //y
            seconds = 100;

        Map<Integer, Integer> quadrants = new HashMap<>(Map.ofEntries(
            entry(1, 0),
            entry(2, 0),
            entry(3, 0),
            entry(4, 0)
        ));


        for (PointWithVelocity p : input)
        {
            int newX = Math.floorMod(((int)p.startPoint.getX() + seconds * p.vx), width);
            int newY = Math.floorMod(((int)p.startPoint.getY() + seconds * p.vy), length);
            determineQuadrant(quadrants, newX, newY);
        }

        int solution = quadrants.get(1) * quadrants.get(2) * quadrants.get(3) * quadrants.get(4);

        System.out.println(solution);
    }

    public static List<PointWithVelocity> getInput()
    {
        String filePath = new File("").getAbsolutePath();
        return PuzzleUtils.getInputForDay14(filePath + "/day_14/input.txt");
    }

    public static void determineQuadrant(Map<Integer, Integer> quadrants, int x, int y)
    {
        if (x < 50)
        {
            if (y < 51)
            {
                quadrants.put(1, quadrants.get(1) + 1);
            }
            else if (y > 51)
            {
                quadrants.put(3, quadrants.get(3) + 1);
            }
        }
        else if (x > 50)
        {
            if (y < 51)
            {
                quadrants.put(2, quadrants.get(2) + 1);
            }
            else if (y > 51)
            {
                quadrants.put(4, quadrants.get(4) + 1);
            }
        }
    }
}
