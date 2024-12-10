package day_10;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utils.PuzzleUtils;
import java.awt.Point;

public class SolutionDay10
{
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        int[][] input = PuzzleUtils.readInputAs2DIntArray(filePath + "/day_10/input.txt");


        Set<Point> startPoints = new HashSet();

        int rowNumber = input.length;
        int columnNumber = input[0].length;


        for (int i = 0; i < rowNumber; i++)
        {
            System.out.println(Arrays.toString(input[i]));
            for (int j = 0; j < columnNumber; j ++)
            {
                if (input[i][j] == 0)
                {
                    startPoints.add(new Point(i, j));
                }
            }
        }

        int solution = 0;
        for (Point p : startPoints)
        {
            Set<Point> neighbouringPoints = checkNeighbours(Set.of(p), input);
        
            while(!startPoints.equals(neighbouringPoints))
            {
                solution += getScore(neighbouringPoints, input);
                startPoints = neighbouringPoints;
                neighbouringPoints = checkNeighbours(startPoints, input);
            }
        }

        System.out.println("Ergebnis: " + solution);
    }

    public static int getScore(Set<Point> points, int[][] input)
    {
        int solution = 0;
        for (Point p : points)
        {
            int x = (int)p.getX();
            int y = (int)p.getY();
            if (input[x][y] == 9)
            {
                solution += 1;
            }
        }
        return solution;
    }

    public static Set<Point> checkNeighbours(Set<Point> points, int[][] input)
    {
        Set<Point> newPoints = new HashSet<>();

        for(Point point : points)
        {
            int x = (int)point.getX();
            int y = (int)point.getY();
            int value = input[x][y];
            int rowNumber = input.length;
            int columnNumber = input[0].length;

            if (0 <= x - 1 && input[x-1][y] == value + 1)
            {
                newPoints.add(new Point(x - 1, y));
            }
            if (x + 1 < rowNumber && input[x+1][y] == value + 1)
            {
                newPoints.add(new Point(x + 1, y));
            }
            if (0 <= y - 1 && input[x][y-1] == value + 1)
            {
                newPoints.add(new Point(x, y - 1));
            }
            if (y + 1 < columnNumber && input[x][y+1] == value + 1)
            {
                newPoints.add(new Point(x, y + 1));
            }
        }
        

        return newPoints;
    }
}
