package day_6;

import utils.PuzzleUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolutionDay6
{
    public static void main(String[] args)
    {
        //star1();
        star2();
    }

    public static void star1()
    {
        Tuple<Integer, Integer> positionGuard = new Tuple<>(0, 0);
        String filePath = new File("").getAbsolutePath();
        String[][] matrix = PuzzleUtils.readInputAs2DArray(filePath + "/day_6/input.txt");

        System.out.println("Rows: " + matrix.length);
        System.out.println("Columns: " + matrix[0].length);

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                //System.out.print(matrix[i][j] + " ");
                if (matrix[i][j].equals("^"))
                {
                    positionGuard = new Tuple<Integer, Integer>(i, j);
                }
            }
            //System.out.println();
        }
        Set<Tuple<Integer, Integer>> visitedPositions = moveAndTurn(positionGuard, matrix);

        System.out.println("Path: " + visitedPositions.toString());

        for(Tuple<Integer, Integer> position : visitedPositions)
        {
            matrix[position.x][position.y] = "X";
        }

        for(String[] row : matrix)
        {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("solution: " + visitedPositions.size());
    }

    public static Set<Tuple<Integer, Integer>> moveAndTurn(Tuple<Integer, Integer> startPosition, String[][] matrix)
    {
        Set<Tuple<Integer, Integer>> visitedPositions = new HashSet<>();
        String direction = "UP";
        boolean outOfBounds = false;
        Tuple<Integer, Integer> position = startPosition;

        while (!outOfBounds)
        {
            if (direction.equals("UP"))
            {
                if (position.x - 1 < 0)
                {
                    visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                    outOfBounds = true;
                }
                else
                {
                    if (matrix[position.x - 1][position.y].equals("#"))
                    {
                        //set direction to RIGHT
                        direction = "RIGHT";
                    }
                    else
                    {
                        visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                        //set position to position.x - 1, position.y
                        position = new Tuple<Integer, Integer>(position.x - 1, position.y);
                    }
                }
            }
            if (direction.equals("DOWN"))
            {
                if (position.x + 1 > matrix.length - 1)
                {
                    visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                    outOfBounds = true;
                }
                else
                {
                    if (matrix[position.x + 1][position.y].equals("#"))
                    {
                        //set direction to LEFT
                        direction = "LEFT";
                    }
                    else
                    {
                        visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                        //set position to position.x + 1, position.y
                        position = new Tuple<Integer, Integer>(position.x + 1, position.y);
                    }
                }
            }
            if (direction.equals("LEFT"))
            {
                if (position.y - 1 < 0)
                {
                    visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                    outOfBounds = true;
                }
                else
                {
                    if (matrix[position.x][position.y - 1].equals("#"))
                    {
                        //set direction to UP
                        direction = "UP";
                    }
                    else
                    {
                        visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                        //set position to position.x, position.y - 1
                        position = new Tuple<Integer, Integer>(position.x, position.y - 1);
                    }
                }
            }
            if (direction.equals("RIGHT"))
            {
                if (position.y + 1 > matrix[0].length - 1)
                {
                    visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                    outOfBounds = true;
                }
                else
                {
                    if (matrix[position.x][position.y + 1].equals("#"))
                    {
                        //set direction to DOWN
                        direction = "DOWN";
                    }
                    else
                    {
                        visitedPositions.add(new Tuple<Integer, Integer>(position.x, position.y));
                        //set position to position.x, position.y + 1
                        position = new Tuple<Integer, Integer>(position.x, position.y + 1);
                    }
                }
            }
        }
        return visitedPositions;
    }

    public static void star2()
    {
        int solution = 0;
        Tuple<Integer, Integer> positionGuard = new Tuple<>(0, 0);
        String filePath = new File("").getAbsolutePath();
        String[][] matrix = PuzzleUtils.readInputAs2DArray(filePath + "/day_6/input.txt");

        System.out.println("Rows: " + matrix.length);
        System.out.println("Columns: " + matrix[0].length);

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                //System.out.print(matrix[i][j] + " ");
                if (matrix[i][j].equals("^"))
                {
                    positionGuard = new Tuple<Integer, Integer>(i, j);
                }
            }
        }
        Set<Tuple<Integer, Integer>> originalPositions = moveAndTurn(positionGuard, matrix);
        for (Tuple<Integer, Integer> possibleObstacle : originalPositions)
        {
            if (!possibleObstacle.equals(positionGuard))
            {
                String[][] copiedMap = copyMapWithObstacleOnPoint(matrix, possibleObstacle);
                solution += moveAndTurnAndCheckLoop(positionGuard, copiedMap);
            }
        }
        System.out.println("Solution: " + solution);
    }

    public static int moveAndTurnAndCheckLoop(Tuple<Integer, Integer> startPosition, String[][] matrix)
    {
        Map<Tuple<Integer, Integer>, List<String>> obstacleMap = new HashMap<>();
        String direction = "UP";
        boolean outOfBounds = false;
        Tuple<Integer, Integer> position = startPosition;

        //addToMap(obstacleMap, startPosition, "bottomToTop");

        while (!outOfBounds)
        {
            if (direction.equals("UP"))
            {
                if (position.x - 1 < 0)
                {
                    //addToMap(obstacleMap, new Tuple<Integer, Integer>(position.x, position.y), "bottomToTop");
                    return 0;
                }
                else
                {
                    if (matrix[position.x - 1][position.y].equals("#"))
                    {
                        //set direction to RIGHT
                        direction = "RIGHT";
                    }
                    else
                    {
                        Tuple<Integer, Integer> toAdd = new Tuple<Integer, Integer>(position.x, position.y);
                        if (obstacleMap.get(toAdd) != null && obstacleMap.get(toAdd).contains("bottomToTop"))
                        {
                            return 1;
                        }
                        addToMap(obstacleMap, toAdd, "bottomToTop");
                        //set position to position.x - 1, position.y
                        position = new Tuple<Integer, Integer>(position.x - 1, position.y);
                    }
                }
            }
            if (direction.equals("DOWN"))
            {
                if (position.x + 1 > matrix.length - 1)
                {
                    //addToMap(obstacleMap, new Tuple<Integer, Integer>(position.x, position.y), "topToBottom");;
                    return 0;
                }
                else
                {
                    if (matrix[position.x + 1][position.y].equals("#"))
                    {
                        //set direction to LEFT
                        direction = "LEFT";
                    }
                    else
                    {
                        Tuple<Integer, Integer> toAdd = new Tuple<Integer, Integer>(position.x, position.y);
                        if (obstacleMap.get(toAdd) != null && obstacleMap.get(toAdd).contains("topToBottom"))
                        {
                            return 1;
                        }
                        addToMap(obstacleMap, toAdd, "topToBottom");
                        //set position to position.x + 1, position.y
                        position = new Tuple<Integer, Integer>(position.x + 1, position.y);
                    }
                }
            }
            if (direction.equals("LEFT"))
            {
                if (position.y - 1 < 0)
                {
                    //addToMap(obstacleMap, new Tuple<Integer, Integer>(position.x, position.y), "rightToLeft");;
                    return 0;
                }
                else
                {
                    if (matrix[position.x][position.y - 1].equals("#"))
                    {
                        //set direction to UP
                        direction = "UP";
                    }
                    else
                    {
                        Tuple<Integer, Integer> toAdd = new Tuple<Integer, Integer>(position.x, position.y);
                        if (obstacleMap.get(toAdd) != null && obstacleMap.get(toAdd).contains("rightToLeft"))
                        {
                            return 1;
                        }
                        addToMap(obstacleMap, toAdd, "rightToLeft");
                        //set position to position.x, position.y - 1
                        position = new Tuple<Integer, Integer>(position.x, position.y - 1);
                    }
                }
            }
            if (direction.equals("RIGHT"))
            {
                if (position.y + 1 > matrix[0].length - 1)
                {
                    //addToMap(obstacleMap, new Tuple<Integer, Integer>(position.x, position.y), "leftToRight");;
                    return 0;
                }
                else
                {
                    if (matrix[position.x][position.y + 1].equals("#"))
                    {
                        //set direction to DOWN
                        direction = "DOWN";
                    }
                    else
                    {
                        Tuple<Integer, Integer> toAdd = new Tuple<Integer, Integer>(position.x, position.y);
                        if (obstacleMap.get(toAdd) != null && obstacleMap.get(toAdd).contains("leftToRight"))
                        {
                            return 1;
                        }
                        addToMap(obstacleMap, toAdd, "leftToRight");
                        //set position to position.x, position.y + 1
                        position = new Tuple<Integer, Integer>(position.x, position.y + 1);
                    }
                }
            }
        }
        return 0;
        //System.out.println(obstacleMap.size());
        //System.out.println("Solution: " + solution);
    }
    public static void addToMap(Map<Tuple<Integer, Integer>, List<String>> map, Tuple<Integer, Integer> tuple, String direction)
    {
        if (map.get(tuple) != null)
        {
            map.get(tuple).add(direction);
        }
        else
        {
            map.put(tuple, new ArrayList<>(Arrays.asList(direction)));
        }
    }

    public static String[][] copyMapWithObstacleOnPoint (String[][] matrix, Tuple<Integer, Integer> newObstacle)
    {
        String[][] copy = Arrays.stream(matrix).map(String[]::clone).toArray(String[][]::new);
        copy[newObstacle.x][newObstacle.y] = "#";
        return copy;
    }

    public static void checkIfLoop()
    {

    }
}

class Tuple<X, Y> {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tuple)){
            return false;
        }

        Tuple<X,Y> other_ = (Tuple<X,Y>) other;

        // this may cause NPE if nulls are valid values for x or y. The logic may be improved to handle nulls properly, if needed.
        return other_.x.equals(this.x) && other_.y.equals(this.y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }
}