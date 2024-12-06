package day_6;

import utils.PuzzleUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class SolutionDay6
{
    public static void main(String[] args)
    {
        star1();
        //star2();
    }

    public static void star1()
    {
        Tuple<Integer, Integer> positionGuard = new Tuple<>(0, 0);
        Set<Tuple<Integer, Integer>> visitedPositions = new HashSet<>();
        String currentDirection = "UP";
        String filePath = new File("").getAbsolutePath();
        String[][] matrix = PuzzleUtils.readInputAs2DArray(filePath + "/day-6/input.txt");

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

        System.out.println("Position: " + positionGuard);
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