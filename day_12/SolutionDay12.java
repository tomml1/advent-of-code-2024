package day_12;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utils.PuzzleUtils;
import java.awt.Point;

public class SolutionDay12
{
    public static void main(String[] args)
    {
        //star1();
        star2();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        String[][] input = PuzzleUtils.readInputAs2DArray(filePath + "/day_12/input.txt");

        int rows = input.length;
        int columns = input[0].length;

        List<Point> alreadyVisited = new ArrayList<>();

        int solution = 0;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (alreadyVisited.contains(new Point(i, j)))
                {
                    continue;
                }
                else
                {
                    Point startPoint = new Point(i, j);
                    List<Point> startPoints = Arrays.asList(startPoint);

                    List<Point> neighbouringPoints = checkNeighbours(startPoints, input);

                    while(!(startPoints.containsAll(neighbouringPoints) && neighbouringPoints.containsAll(startPoints)))
                    {
                        startPoints = neighbouringPoints;
                        neighbouringPoints = checkNeighbours(startPoints, input);
                    }

                    //System.out.println("Region: \n" + startPoints);

                    solution += getScore(startPoints, input);

                    alreadyVisited.addAll(startPoints);
                }
            }
        }

        System.out.println("Ergebnis: " + solution);
    }

    public static List<Point> checkNeighbours(List<Point> points, String[][] input)
    {
        Set<Point> newPoints = new HashSet<>();

        for(Point point : points)
        {
            int x = (int)point.getX();
            int y = (int)point.getY();
            String value = input[x][y];
            int rowNumber = input.length;
            int columnNumber = input[0].length;

            if (0 <= x - 1 && input[x-1][y].equals(value))
            {
                newPoints.add(new Point(x - 1, y));
            }
            if (x + 1 < rowNumber && input[x+1][y].equals(value))
            {
                newPoints.add(new Point(x + 1, y));
            }
            if (0 <= y - 1 && input[x][y-1].equals(value))
            {
                newPoints.add(new Point(x, y - 1));
            }
            if (y + 1 < columnNumber && input[x][y+1].equals(value))
            {
                newPoints.add(new Point(x, y + 1));
            }
        }

        newPoints.addAll(points);
        
        return new ArrayList<>(newPoints);
    }

    public static int getScore(List<Point> points, String[][] input)
    {
        
        int elements = points.size();
        int circumference = 0;
        for (Point p : points)
        {
            if(!points.contains(new Point(p.x-1, p.y)))
            {
                circumference += 1;
            }
            if(!points.contains(new Point(p.x+1, p.y)))
            {
                circumference += 1;
            }
            if(!points.contains(new Point(p.x, p.y-1)))
            {
                circumference += 1;
            }
            if(!points.contains(new Point(p.x, p.y+1)))
            {
                circumference += 1;
            }
        }
        System.out.println("Region " + input[points.get(0).x][points.get(0).y] + ", size " + elements + ", circumference " + circumference);

        return elements * circumference;
    }

    public static void star2()
    {
        String filePath = new File("").getAbsolutePath();
        String[][] input = PuzzleUtils.readInputAs2DArray(filePath + "/day_12/input.txt");

        int rows = input.length;
        int columns = input[0].length;

        List<Point> alreadyVisited = new ArrayList<>();

        int solution = 0;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (alreadyVisited.contains(new Point(i, j)))
                {
                    continue;
                }
                else
                {
                    Point startPoint = new Point(i, j);
                    List<Point> startPoints = Arrays.asList(startPoint);

                    List<Point> neighbouringPoints = checkNeighbours(startPoints, input);

                    while(!(startPoints.containsAll(neighbouringPoints) && neighbouringPoints.containsAll(startPoints)))
                    {
                        startPoints = neighbouringPoints;
                        neighbouringPoints = checkNeighbours(startPoints, input);
                    }

                    solution += getScorePart2(startPoints, input);

                    alreadyVisited.addAll(startPoints);
                }
            }
        }

        System.out.println("Ergebnis: " + solution);
    }

    public static int getScorePart2(List<Point> points, String[][] input)
    {
        int rows = input.length;
        int columns = input[0].length;
        int elements = points.size();
        int circumference = 0;

        //for rows
        List<Integer> affectedColumns = new ArrayList<>();
        boolean addOuterRowEdge = false;
        for (int i = 0; i < rows; i++)
        {
            List<Integer> newColumns = new ArrayList<>();
            List<Integer> removedColumns = new ArrayList<>();
            for (int j = 0; j < columns; j++)
            {
                if (points.contains(new Point(i, j)))
                {
                    if (!affectedColumns.contains(j))
                    {
                        affectedColumns.add(j);
                        newColumns.add(j);
                    }
                    if (i == rows - 1)
                    {
                        addOuterRowEdge = true;
                    }
                }
            }

            List<Integer> iterateAffectedColumns = new ArrayList<>(affectedColumns);
            for (Integer p : iterateAffectedColumns)
            {
                if (!points.contains(new Point(i, p)))
                {
                    removedColumns.add(p);
                    affectedColumns.remove(p);
                }
            }
            
            circumference += getScoreForColumnOrRow(newColumns);
            circumference += getScoreForColumnOrRow(removedColumns);
        }


        //for columns
        List<Integer> affectedRows = new ArrayList<>();
        boolean addOuterColumnEdge = false;
        for (int j = 0; j < columns; j++)
        {
            List<Integer> newRows = new ArrayList<>();
            List<Integer> removedRows = new ArrayList<>();
            for (int i = 0; i < columns; i++)
            {
                if (points.contains(new Point(i, j)))
                {
                    if (!affectedRows.contains(i))
                    {
                        affectedRows.add(i);
                        newRows.add(i);
                    }
                    if (j == columns - 1)
                    {
                        addOuterColumnEdge = true;
                    }
                }
            }

            List<Integer> iterateAffectedRows = new ArrayList<>(affectedRows);
            for (Integer p : iterateAffectedRows)
            {
                if (!points.contains(new Point(p, j)))
                {
                    removedRows.add(p);
                    affectedRows.remove(p);
                }
            }

            circumference += getScoreForColumnOrRow(newRows);
            circumference += getScoreForColumnOrRow(removedRows);
        }

        if(addOuterColumnEdge)
        {
            circumference += 1;
        }
        if(addOuterRowEdge)
        {
            circumference += 1;
        }

        
        System.out.println("Region " + input[points.get(0).x][points.get(0).y] + ", size " + elements + ", circumference " + circumference);

        return elements * circumference;
    }

    public static int getScoreForColumnOrRow(List<Integer> columnsOrRows)
    {
        int score = 0;
        for (Integer i : columnsOrRows)
        {
            if (!columnsOrRows.contains(i - 1))
            {
                score += 1;
            }
        }

        return score;
    }
}
