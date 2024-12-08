package day_8;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.PuzzleUtils;

import java.awt.Point;

public class SolutionDay8
{
    public static void main(String[] args)
    {
        star1();
        star2();
    }   

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String[][] input = PuzzleUtils.readInputAs2DArray(filePath + "/day_8/input.txt");

        Map<String, List<Point>> locations = new HashMap<>();

        int numberRows = input.length;
        int numberColumns = input[0].length;

        for (int i = 0; i < numberRows; i++)
        {
            for (int j = 0; j < numberColumns; j++)
            {
                if (!input[i][j].equals("."))
                {
                    addToMap(input[i][j], new Point(i, j), locations);
                }
            }
        }

        System.out.println(locations);

        Set<Point> antinodes = new HashSet<>();

        for (Map.Entry<String, List<Point>> antennas : locations.entrySet())
        {
            //System.out.println("Key: " + antennas.getKey());
            //System.out.println("Value: " + antennas.getValue().toString());
            for (Point point : antennas.getValue())
            {
                List<Point> remainingPoints = new ArrayList<>(antennas.getValue());
                remainingPoints.remove(point);
                for (Point remainingPoint : remainingPoints)
                {
                    int xDir = (int) (point.getX() - remainingPoint.getX());
                    int yDir = (int) (point.getY() - remainingPoint.getY());

                    Point antinode = new Point((int) point.getX() + xDir, (int) point.getY() + yDir);

                    if (antinode.getX() >= 0 && antinode.getX() < numberRows && antinode.getY() >= 0 && antinode.getY() < numberColumns && !antinodes.contains(antinode))
                    {
                        antinodes.add(antinode);
                    }
                }
            }
        }

        for (Point point : antinodes)
        {
            input[(int)point.getX()][(int)point.getY()] = "#";
        }

        System.out.println("Ergebnis: " + antinodes.size());

        for (int i = 0; i < numberRows; i++)
        {
            System.out.println(Arrays.toString(input[i]));
        }
    }

    public static void addToMap(String key, Point value, Map<String, List<Point>> map)
    {
        if (map.get(key) != null)
        {
            List<Point> list = map.get(key);
            list.add(value);
            map.put(key, list);
        }
        else
        {
            map.put(key, new ArrayList<>(Arrays.asList(value)));
        }
    }

    public static void star2()
    {
        String filePath = new File("").getAbsolutePath();
        String[][] input = PuzzleUtils.readInputAs2DArray(filePath + "/day_8/input.txt");

        Map<String, List<Point>> locations = new HashMap<>();

        int numberRows = input.length;
        int numberColumns = input[0].length;

        for (int i = 0; i < numberRows; i++)
        {
            for (int j = 0; j < numberColumns; j++)
            {
                if (!input[i][j].equals("."))
                {
                    addToMap(input[i][j], new Point(i, j), locations);
                }
            }
        }

        System.out.println(locations);

        Set<Point> antinodes = new HashSet<>();

        for (Map.Entry<String, List<Point>> antennas : locations.entrySet())
        {
            for (Point point : antennas.getValue())
            {
                List<Point> remainingPoints = new ArrayList<>(antennas.getValue());
                remainingPoints.remove(point);
                antinodes.add(point);
                for (Point remainingPoint : remainingPoints)
                {
                    int xDir = (int) (point.getX() - remainingPoint.getX());
                    int yDir = (int) (point.getY() - remainingPoint.getY());
                    boolean outOfBounds = false;
                    int multiplier = 1;
                    while(!outOfBounds)
                    {
                        Point antinode = new Point((int) point.getX() + multiplier * xDir, (int) point.getY() + multiplier * yDir);

                        if (antinode.getX() >= 0 && antinode.getX() < numberRows && antinode.getY() >= 0 && antinode.getY() < numberColumns)
                        {
                            if (!antinodes.contains(antinode))
                            {
                                antinodes.add(antinode);
                            }
                        }
                        else
                        {
                            outOfBounds = true;
                        }
                        multiplier++;
                    }
                }
            }
        }

        System.out.println("Ergebnis: " + antinodes.size());
    }
}