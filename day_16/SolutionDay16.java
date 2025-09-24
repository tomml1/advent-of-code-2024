package day_16;

import utils.PuzzleUtils;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        String filename = "testinput.txt";
        String[][] input = PuzzleUtils.readInputAs2DArray(filePath + "/day_16/" + filename);

        Point startPosition = getStartPosition(input);

        /*
        Koordinaten sind:
        0------> x
        |
        |
        v
        y

        entspricht also input[y][x]
         */

        String startDirection = "E"; // N, E, S, W
        List<Point> visitedPoints = new ArrayList<>();
        visitedPoints.add(startPosition);
        List<String> possibleDirections = lookAround(input, startPosition, startDirection, visitedPoints);
        Path startPath = new Path(visitedPoints, false, startDirection);
        List<Path> allPaths = new ArrayList<>();
        for(var p : possibleDirections)
        {
            Path path = new Path(new ArrayList<>(visitedPoints), false, p);
            move(path.getCurrentDirection(), startPosition, path.getVisitedPoints());
            allPaths.add(path);
        }

        allPaths = Arrays.asList(startPath);
        int oldHash = allPaths.hashCode();

        allPaths = lookAroundPath(input, startPath);

        int newHash = allPaths.hashCode();

        while (oldHash != newHash) {
            List<Path> newPaths = new ArrayList<>();
            for (var path : allPaths)
            {
                newPaths.addAll(lookAroundPath(input, path));
            }
            allPaths = newPaths;
            oldHash = newHash;
            newHash = allPaths.hashCode();
        }

        List<Path> possiblePaths = allPaths.stream().filter(p -> p.isComplete()).toList();
        Integer score = Integer.MAX_VALUE;
        for (var path : possiblePaths)
        {
            int currentScore = calculateScore(path);
            if (currentScore < score)
            {
                score = currentScore;
            }
        }

        System.out.println("Score: " + score);
    }

    public static int calculateScore(Path path)
    {
        int moves = path.getPath().length() - 1;
        int turns = 0;
        for (int i = 1; i < path.getPath().length(); i++)
        {
            if (path.getPath().charAt(i) != path.getPath().charAt(i - 1))
            {
                turns++;
            }
        }
        int score = moves + turns * 1000;
        return score;
    }

    public static Point move(String direction, Point startPoint, List<Point> visitedPoints)
    {
        Point newPoint = switch (direction) {
            case "N" -> {
                yield new Point(startPoint.x, startPoint.y - 1);
            }
            case "E" -> {
                yield new Point(startPoint.x + 1, startPoint.y);
            }
            case "S" -> {
                yield new Point(startPoint.x, startPoint.y + 1);
            }
            case "W" -> {
                yield new Point(startPoint.x - 1, startPoint.y);
            }
            default -> {
                yield null;
            }
        };

        visitedPoints.add(newPoint);
        return newPoint;
    }

    public static List<Path> lookAroundPath(String[][] maze, Path path)
    {
        Set<String> moveCharacters = Set.of(".", "E");
        Point currentPosition = path.getCurrentPosition();
        String currentDirection = path.getCurrentDirection();
        List<Point> visitedPoints = path.getVisitedPoints();
        int x = currentPosition.x;
        int y = currentPosition.y;

        List<Point> possibleMoves = new ArrayList<>();
        List<String> possibleDirections = new ArrayList<>();
        List<Path> possiblePaths = new ArrayList<>();

        if (path.isComplete() || maze[y][x].equals("E"))
        {
            path.setComplete(true);
            possiblePaths.add(path);
            return possiblePaths;
        }

        // North
        if (moveCharacters.contains(maze[y - 1][x]) && !currentDirection.equals("S"))
        {
            if(!visitedPoints.contains(new Point(x, y - 1)))
            {
                possibleMoves.add(new Point(x, y - 1));
                possibleDirections.add("N");
            }
        }
        // East
        if (moveCharacters.contains(maze[y][x + 1]) && !currentDirection.equals("W"))
        {
            if(!visitedPoints.contains(new Point(x + 1, y)))
            {
                possibleMoves.add(new Point(x + 1, y));
                possibleDirections.add("E");
            }
        }
        // South
        if (moveCharacters.contains(maze[y + 1][x]) && !currentDirection.equals("N"))
        {
            if(!visitedPoints.contains(new Point(x, y + 1)))
            {
                possibleMoves.add(new Point(x, y + 1));
                possibleDirections.add("S");
            }
        }
        // West
        if (moveCharacters.contains(maze[y][x - 1]) && !currentDirection.equals("E"))
        {
            if(!visitedPoints.contains(new Point(x - 1, y)))
            {
                possibleMoves.add(new Point(x - 1, y));
                possibleDirections.add("W");
            }
        }

        for (var direction : possibleDirections)
        {
            Path newPath = path.copy();
            newPath.appendPath(direction);
            move(direction, currentPosition, newPath.getVisitedPoints());
            possiblePaths.add(newPath);
        }

        return possiblePaths;
    }

    public static List<String> lookAround(String[][] maze, Point currentPosition, String currentDirection, List<Point> visitedPoints)
    {
        int x = currentPosition.x;
        int y = currentPosition.y;

        List<Point> possibleMoves = new ArrayList<>();
        List<String> possibleDirections = new ArrayList<>();

        if (maze[y][x].equals("E"))
        {
            return possibleDirections;
        }

        // North
        if (maze[y - 1][x].equals(".") && !currentDirection.equals("S"))
        {
            if(!visitedPoints.contains(new Point(x, y - 1)))
            {
                possibleMoves.add(new Point(x, y - 1));
                possibleDirections.add("N");
            }
        }
        // East
        if (maze[y][x + 1].equals(".") && !currentDirection.equals("W"))
        {
            if(!visitedPoints.contains(new Point(x + 1, y)))
            {
                possibleMoves.add(new Point(x + 1, y));
                possibleDirections.add("E");
            }
        }
        // South
        if (maze[y + 1][x].equals(".") && !currentDirection.equals("N"))
        {
            if(!visitedPoints.contains(new Point(x, y + 1)))
            {
                possibleMoves.add(new Point(x, y + 1));
                possibleDirections.add("S");
            }
        }
        // West
        if (maze[y][x - 1].equals(".") && !currentDirection.equals("E"))
        {
            if(!visitedPoints.contains(new Point(x - 1, y)))
            {
                possibleMoves.add(new Point(x - 1, y));
                possibleDirections.add("W");
            }
        }

        return possibleDirections;
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
}
