package day_16;

import utils.PuzzleUtils;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class SolutionDay16
{
    public static void main(String[] args)
    {
        System.out.println("Day 16: Reindeer Maze");
        star1();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String filename = "testinput2.txt";
        String[][] input = PuzzleUtils.readInputAs2DArray(filePath + "/day_16/" + filename);

        Point startPosition = getStartPosition(input);
        System.out.println("Starting position: " + startPosition);

        /*
        Koordinaten sind:
        0------> x
        |
        |
        v
        y

        entspricht also input[y][x]
         */

        Map<Point, Integer> distanceMap = new HashMap<>(); // if point not in map then distance is infinity
        distanceMap.put(startPosition, 0);
        Set<Positioning> visited = new HashSet<>();
        Set<Positioning> alreadyAdded = new HashSet<>();

        alreadyAdded.add(new Positioning(startPosition.x, startPosition.y, Direction.EAST, 0));

        LinkedList<Positioning> pg = new LinkedList<>();
        pg.add(new Positioning(startPosition.x, startPosition.y, Direction.EAST, 0));

        Point endPosition = getEndPosition(input);

        System.out.println("Number nodes: " + getNumberNodes(input));

        while(pg.size() > 0)
        {
            Positioning current = pg.poll();
            List<Positioning> neighbors = current.getAdjacentPositionings(input);
            for (var neighbor : neighbors) {
                updateDistance(distanceMap, current.getPoint(), neighbor);
                alreadyAdded.add(neighbor);
                if (!visited.contains(neighbor) || !alreadyAdded.contains(neighbor)) {
                    pg.add(neighbor);
                }
            }
            visited.add(current);
        }

        System.out.println("Score: " + distanceMap.get(endPosition));
    }

    public static void updateDistance(Map<Point, Integer> distanceMap, Point current, Positioning neighbor) {
        int currentDistance = distanceMap.get(current);
        Point neighborPoint = neighbor.getPoint();
        int newDistance = neighbor.getScore();
        if (!distanceMap.containsKey(neighborPoint) || newDistance < distanceMap.get(neighborPoint)) {
            distanceMap.put(neighborPoint, newDistance);
        }
    }

    public static Point getStartPosition(String[][] maze)
    {
        int rowNumber = maze.length;
        int columnNumber = maze[0].length;

        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (maze[i][j].equals("S"))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    public static Point getEndPosition(String[][] maze)
    {
        int rowNumber = maze.length;
        int columnNumber = maze[0].length;

        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (maze[i][j].equals("E"))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    public static int getNumberNodes(String[][] maze)
    {
        int rowNumber = maze.length;
        int columnNumber = maze[0].length;

        int number = 0;

        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (!maze[i][j].equals("#"))
                {
                    number++;
                }
            }
        }

        return number;
    }
}
