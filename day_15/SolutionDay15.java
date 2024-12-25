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
        star1();
    }

    public static void star1()
    {
        String[][] maze = getMaze();
        String[] moves = getMoves();

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

    public static String[][] getMaze()
    {
        String filePath = new File("").getAbsolutePath();
        return PuzzleUtils.readInputAs2DArray(filePath + "/day_15/inputmace.txt");
    }

    public static String[] getMoves()
    {
        String filePath = new File("").getAbsolutePath();
        return PuzzleUtils.readInputAsString(filePath + "/day_15/inputmoves.txt").split("");
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
}
