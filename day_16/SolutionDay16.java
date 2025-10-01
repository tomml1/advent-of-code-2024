package day_16;

import utils.PuzzleUtils;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class SolutionDay16
{
    public static void main(String[] args)
    {
        System.out.println("Day 16: Reindeer Maze");
        star1();
    }

    public static void star1() {
        String filePath = new File("").getAbsolutePath();
        String filename = "input.txt";
        String[][] input = PuzzleUtils.readInputAs2DArray(filePath + "/day_16/" + filename);

        Point startPosition = getStartPosition(input);
        Positioning startPositioning = new Positioning(startPosition.x, startPosition.y, Direction.EAST, 0, null);
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

        alreadyAdded.add(startPositioning);

        LinkedList<Positioning> pg = new LinkedList<>();
        pg.add(startPositioning);

        Point endPosition = getEndPosition(input);
        System.out.println("End position: " + endPosition);

        System.out.println("Number nodes: " + getNumberNodes(input));

        Map<String, Positioning> nodeMap = createNodeMap(input);

        System.out.println("Number nodes with directions: " + nodeMap.size());

        nodeMap.put("x=" + startPosition.x + ",y=" + startPosition.y + "E", startPositioning);

        PriorityQueue<Positioning> pq2 = new PriorityQueue<>(Comparator.comparing(Positioning::getScore));

        pq2.addAll(nodeMap.values());

        while (!pq2.isEmpty()) {
            Positioning current = pq2.poll();
            List<Positioning> neighbors = current.getAdjacentPositionings2(input);
            for (var neighbor : neighbors) {
                String key = "x=" + neighbor.getX() + ",y=" + neighbor.getY() + neighbor.getDirection().toString();
                Positioning mapNeighbor = nodeMap.get(key);
                int newDistance = current.getScore() + neighbor.getScore();
                if (newDistance < mapNeighbor.getScore()) {
                    mapNeighbor.setScore(newDistance);
                    mapNeighbor.setPrevious(new ArrayList<>(List.of(current)));
                    pq2.remove(mapNeighbor);
                    pq2.add(mapNeighbor);
                } else if (newDistance == mapNeighbor.getScore()) {
                    mapNeighbor.addPrevious(neighbor);
                }
            }
            current.setComplete(true);
        }

        String endKey = "x=" + endPosition.x + ",y=" + endPosition.y;
        System.out.println("Ende E: " + nodeMap.get(endKey + "EAST"));
        System.out.println("Ende N: " + nodeMap.get(endKey + "NORTH"));
        System.out.println("Ende W: " + nodeMap.get(endKey + "WEST"));
        System.out.println("Ende S: " + nodeMap.get(endKey + "SOUTH"));

        Positioning minEnd = Stream.of(nodeMap.get(endKey + "EAST"), nodeMap.get(endKey + "NORTH"), nodeMap.get(endKey + "WEST"), nodeMap.get(endKey + "SOUTH"))
                .filter(Objects::nonNull)
                .min(Comparator.comparing(Positioning::getScore)).get();

        System.out.println("Minimum steps to reach the end: " + minEnd.getScore());

        printMaze(input);

        input[minEnd.getY()][minEnd.getX()] = "O";

        List<Positioning> previous = minEnd.getPrevious();

        startPositioning.setPrevious(new ArrayList<>());

        while (!previous.isEmpty()) {
            Set<Positioning> nextPrevious = new HashSet<>();
            for (var prev : previous) {
                input[prev.getY()][prev.getX()] = "O";
                nextPrevious.addAll(prev.getPrevious());
            }
            previous = new ArrayList<>(nextPrevious);
        }

        int part2Score = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j].equals("O")) {
                    part2Score++;
                }
            }
        }

        System.out.println("Part 2 Score: " + part2Score);
    }

    public static void printMaze(String[][] maze)
    {
        int rowNumber = maze.length;
        int columnNumber = maze[0].length;

        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static Map<String, Positioning> createNodeMap(String[][] maze)
    {
        int rowNumber = maze.length;
        int columnNumber = maze[0].length;

        Map<String, Positioning> nodeMap = new HashMap<>();

        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (!maze[i][j].equals("#"))
                {
                    nodeMap.put("x=" + j + ",y=" + i + "EAST", new Positioning(j, i, Direction.EAST, Integer.MAX_VALUE, null));
                    nodeMap.put("x=" + j + ",y=" + i + "NORTH", new Positioning(j, i, Direction.NORTH, Integer.MAX_VALUE, null));
                    nodeMap.put("x=" + j + ",y=" + i + "SOUTH", new Positioning(j, i, Direction.SOUTH, Integer.MAX_VALUE, null));
                    nodeMap.put("x=" + j + ",y=" + i + "WEST", new Positioning(j, i, Direction.WEST, Integer.MAX_VALUE, null));
                }
            }
        }

        return nodeMap;
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
