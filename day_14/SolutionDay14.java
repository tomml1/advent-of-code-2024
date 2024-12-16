package day_14;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.PuzzleUtils;
import java.awt.Point;

import static java.util.Map.entry;

public class SolutionDay14
{
    public static void main(String[] args)
    {
        //star1();
        star2();
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

    public static void star2()
    {
        List<PointWithVelocity> input = getInput();
        int width = 101, //x
            length = 103, //y
            seconds = 10000;


        for (int i = 100; i < seconds; i++)
        {
            simulate(i, input, width, length);
        }
    }

    public static void simulate(int seconds, List<PointWithVelocity> input, int width, int length)
    {
        Set<Point> newPoints = new HashSet<>();

        for (PointWithVelocity p : input)
        {
            int newX = Math.floorMod(((int)p.startPoint.getX() + seconds * p.vx), width);
            int newY = Math.floorMod(((int)p.startPoint.getY() + seconds * p.vy), length);
            Point newPoint = new Point(newX, newY);
            newPoints.add(newPoint);
        }

        if (newPoints.size() == 500)
        {
            System.out.println("maybe: " + seconds);
        }
    }
}
