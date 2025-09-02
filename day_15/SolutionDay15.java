package day_15;

import java.io.File;
import java.util.Arrays;
import utils.PuzzleUtils;
import java.awt.Point;
import java.util.concurrent.TimeUnit;

public class SolutionDay15
{
    public static void main(String[] args)
    {
        //star1("testinputmace.txt", "testinputmoves.txt"); //2028
        //star1("testinputmace2.txt", "testinputmoves2.txt"); //10092
        star2();
    }

    public static void star1(String fileNameMaze, String fileNameMoves)
    {
        String[][] maze = getMaze(fileNameMaze);
        String[] moves = getMoves(fileNameMoves);

        int rowNumber = maze.length;
        int columnNumber = maze[0].length;

        Point startPosition = getStartPosition(maze);

        /*
        for (int i = 0; i < rowNumber; i++)
        {
            System.out.println(Arrays.toString(maze[i]));
        }

        System.out.println("---");

        System.out.println(Arrays.toString(moves));

        System.out.println("---");

        System.out.println(startPosition);
        */

        for (int i = 0; i < moves.length; i++)
        {
            //System.out.println("-----");
            //System.out.println("Direction: " + moves[i]);
            move(startPosition, moves[i], maze);
            
            //visualize(maze);
        }


        int solution = 0;
        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (maze[i][j].equals("O"))
                {
                    solution += 100 * i;
                    solution += j;
                }
            }
        }

        System.out.println(solution);
    }

    public static void visualize(String[][] maze)
    {
        int rowNumber = maze.length;
        for (int i = 0; i < rowNumber; i++)
        {
            System.out.println(Arrays.toString(maze[i]));
        }

        try
        {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void visualizeBetter(String[][] maze)
    {
        int rowNumber = maze.length;
        for (int i = 0; i < rowNumber; i++)
        {
            String row = "";
            for (int j = 0; j < maze[0].length; j++)
            {
                row += maze[i][j];
            }
            System.out.println(row);
        }

        try
        {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String[][] getMaze(String filename)
    {
        String filePath = new File("").getAbsolutePath();
        return PuzzleUtils.readInputAs2DArray(filePath + "/day_15/" + filename);
    }

    public static String[] getMoves(String filename)
    {
        String filePath = new File("").getAbsolutePath();
        return PuzzleUtils.readInputAsString(filePath + "/day_15/" + filename).split("");
    }

    public static Point getStartPosition(String[][] maze)
    {
        int rowNumber = maze.length;
        int columnNumber = maze[0].length;

        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (maze[i][j].equals("@"))
                {
                    return new Point(j, i);
                }
            }
        }

        return null;
    }

    public static void move(Point position, String direction, String[][] maze)
    {
        int posX = (int) position.getX();
        int posY = (int) position.getY();
        if (direction.equals("^"))
        {
            if (maze[posY - 1][posX].equals("#"))
            {
                return;
            }
            else if (maze[posY - 1][posX].equals("O"))
            {
                int i = 1;
                while (maze[posY - i][posX].equals("O"))
                {
                    i++;
                }
                if (maze[posY - i][posX].equals("#"))
                {
                    return;
                }
                if (maze[posY - i][posX].equals("."))
                {
                    maze[posY - i][posX] = "O";
                    maze[posY][posX] = ".";
                    maze[posY - 1][posX] = "@";
                    position.setLocation(posX, posY - 1);
                }
            }
            else
            {
                maze[posY][posX] = ".";
                maze[posY - 1][posX] = "@";
                position.setLocation(posX, posY - 1);
            }
        }
        if (direction.equals(">"))
        {
            if (maze[posY][posX + 1].equals("#"))
            {
                return;
            }
            else if (maze[posY][posX + 1].equals("O"))
            {
                int i = 1;
                while (maze[posY][posX + i].equals("O"))
                {
                    i++;
                }
                if (maze[posY][posX + i].equals("#"))
                {
                    return;
                }
                if (maze[posY][posX + i].equals("."))
                {
                    maze[posY][posX + i] = "O";
                    maze[posY][posX] = ".";
                    maze[posY][posX + 1] = "@";
                    position.setLocation(posX + 1, posY);
                }
            }
            else
            {
                maze[posY][posX] = ".";
                maze[posY][posX + 1] = "@";
                position.setLocation(posX + 1, posY);
            }
        }
        if (direction.equals("v"))
        {
            if (maze[posY + 1][posX].equals("#"))
            {
                return;
            }
            else if (maze[posY + 1][posX].equals("O"))
            {
                int i = 1;
                while (maze[posY + i][posX].equals("O"))
                {
                    i++;
                }
                if (maze[posY + i][posX].equals("#"))
                {
                    return;
                }
                if (maze[posY + i][posX].equals("."))
                {
                    maze[posY + i][posX] = "O";
                    maze[posY][posX] = ".";
                    maze[posY + 1][posX] = "@";
                    position.setLocation(posX, posY + 1);
                }
            }
            else
            {
                maze[posY][posX] = ".";
                maze[posY + 1][posX] = "@";
                position.setLocation(posX, posY + 1);
            }
        }
        if (direction.equals("<"))
        {
            if (maze[posY][posX - 1].equals("#"))
            {
                return;
            }
            else if (maze[posY][posX - 1].equals("O"))
            {
                int i = 1;
                while (maze[posY][posX - i].equals("O"))
                {
                    i++;
                }
                if (maze[posY][posX - i].equals("#"))
                {
                    return;
                }
                if (maze[posY][posX - i].equals("."))
                {
                    maze[posY][posX - i] = "O";
                    maze[posY][posX] = ".";
                    maze[posY][posX - 1] = "@";
                    position.setLocation(posX - 1, posY);
                }
            }
            else
            {
                maze[posY][posX] = ".";
                maze[posY][posX - 1] = "@";
                position.setLocation(posX - 1, posY);
            }
        }
    }

    public static void star2()
    {
        String[][] maze = getMaze("inputmace.txt");
        String[] moves = getMoves("inputmoves.txt");
        String[][] expandedMaze = expandMaze(maze);
        //visualizeBetter(expandedMaze);

        Point startPosition = getStartPosition(expandedMaze);

        Boolean possible = true;
        Possibility poss = new Possibility(true);

        for (var move : moves)
        {
            //System.out.println("current move: " + move);
            if (move.equals("<") || move.equals(">"))
            {
                //String[][] testMaze = expandedMaze.clone();
                String[][] testMaze = deepCopy(expandedMaze);
                //System.out.println("Koordinaten startPosition: " + startPosition.getX() + ", " + startPosition.getY());
                Point currentPosition = new Point(startPosition);
                moveBoxesHorizontally(currentPosition, move, testMaze, poss);
                //System.out.println("possible: " + poss.getPossible());
                if(poss.getPossible())
                {
                    //System.out.println("wird überschrieben");
                    expandedMaze = testMaze;
                    //System.out.println("Koordinaten currentPosition: " + currentPosition.getX() + ", " + currentPosition.getY());

                    startPosition = currentPosition;
                }
            }
            else if (move.equals("^") || move.equals("v"))
            {
                //String[][] testMaze = expandedMaze.clone();
                String[][] testMaze = deepCopy(expandedMaze);
                //System.out.println("Koordinaten startPosition: " + startPosition.getX() + ", " + startPosition.getY());
                Point currentPosition = new Point(startPosition);
                moveBoxesRecursively(currentPosition, move, testMaze, poss);
                //System.out.println("possible: " + poss.getPossible());
                if(poss.getPossible())
                {
                    //System.out.println("wird überschrieben");
                    expandedMaze = testMaze;
                    //System.out.println("Koordinaten currentPosition: " + currentPosition.getX() + ", " + currentPosition.getY());
                    startPosition = currentPosition;
                }
            }
            poss.setPossible(true);
            //visualizeBetter(expandedMaze);
        }

        int rowNumber = expandedMaze.length;
        int columnNumber = expandedMaze[0].length;

        int solution = 0;
        for (int i = 0; i < rowNumber; i++)
        {
            for (int j = 0; j < columnNumber; j++)
            {
                if (expandedMaze[i][j].equals("["))
                {
                    solution += 100 * i;
                    solution += j;
                }
            }
        }
        visualizeBetter(expandedMaze);
        System.out.println(solution);
    }

    public static <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    public static String[][] expandMaze(String[][] maze)
    {
        String[][] expandedMaze = new String[maze.length][maze[0].length * 2];
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                if (maze[i][j].equals("#"))
                {
                    expandedMaze[i][j*2] = "#";
                    expandedMaze[i][j*2 + 1] = "#";
                }
                if (maze[i][j].equals("O"))
                {
                    expandedMaze[i][j*2] = "[";
                    expandedMaze[i][j*2 + 1] = "]";
                }
                if (maze[i][j].equals("."))
                {
                    expandedMaze[i][j*2] = ".";
                    expandedMaze[i][j*2 + 1] = ".";
                }
                if (maze[i][j].equals("@"))
                {
                    expandedMaze[i][j*2] = "@";
                    expandedMaze[i][j*2 + 1] = ".";
                }
            }
        }
        return expandedMaze;
    }

    public static boolean isBox(Point position, String[][] maze)
    {
        int posX = (int) position.getX();
        int posY = (int) position.getY();

        if (maze[posY][posX].equals("[") || maze[posY][posX].equals("]"))
        {
            return true;
        }

        return false;
    }

    public static void moveBoxesHorizontally(Point position, String direction, String[][] maze, Possibility possible) {
        int directionIncrement;
        if (direction.equals("<"))
        {
            directionIncrement = -1;
        } else if
        (direction.equals(">")) {
            directionIncrement = 1;
        } else
        {
            throw new IllegalArgumentException();
        }

        int posX = (int) position.getX();
        int posY = (int) position.getY();

        if (maze[posY][posX + directionIncrement].equals("[") || maze[posY][posX + directionIncrement].equals("]"))
        {
            moveBoxesHorizontally(new Point(posX + directionIncrement, posY), direction, maze, possible);
        }

        if (maze[posY][posX + directionIncrement].equals("."))
        {
            maze[posY][posX + directionIncrement] = maze[posY][posX];
            maze[posY][posX] = ".";
            position.setLocation(posX + directionIncrement, posY);
            return;
        }
        else if (maze[posY][posX + directionIncrement].equals("#"))
        {
            possible.setPossible(false);
            //System.out.println("HIER STOPPEN");
            return;
        }
    }

    public static void moveBoxesRecursively(Point position, String direction, String[][] maze, Possibility possible)
    {
        //BFS to determine if moving is possible
        int directionIncrement;
        if (direction.equals("^"))
        {
            directionIncrement = -1;
        }
        else if (direction.equals("v"))
        {
            directionIncrement = 1;
        }
        else
        {
            throw new IllegalArgumentException();
        }

        int posX = (int) position.getX();
        int posY = (int) position.getY();


        if (maze[posY + directionIncrement][posX].equals("["))
        {
            moveBoxesRecursively(new Point(posX, posY + directionIncrement), direction, maze, possible);
            moveBoxesRecursively(new Point(posX + 1, posY + directionIncrement), direction, maze, possible);
        }
        else if (maze[posY + directionIncrement][posX].equals("]"))
        {
            moveBoxesRecursively(new Point(posX, posY + directionIncrement), direction, maze, possible);
            moveBoxesRecursively(new Point(posX - 1, posY + directionIncrement), direction, maze, possible);
        }

        if (maze[posY + directionIncrement][posX].equals("."))
        {
            maze[posY + directionIncrement][posX] = maze[posY][posX];
            maze[posY][posX] = ".";
            position.setLocation(posX, posY + directionIncrement);
            return;
        }
        else if (maze[posY + directionIncrement][posX].equals("#"))
        {
            possible.setPossible(false);
            //System.out.println("HIER STOPPEN");
            return;
        }
    }
}
