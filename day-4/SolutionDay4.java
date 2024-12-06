import utils.PuzzleUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolutionDay4 {
    public static void main(String[] args)
    {
        String[][] input = PuzzleUtils.readInputAs2DArray("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-4\\input.txt");

        //star1();
        star2(input);
    }

    public static void star1()
    {
        String[][] everything = PuzzleUtils.readInputAs2DArray("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-4\\input.txt");

        List<String> lines = getRows(everything);
        List<String> columns = getColumns(everything);
        List<String> diagonals1 = getDiagonalsVonLinksObenNachRechtsUnten(everything);
        List<String> diagonals2 = getDiagonalsVonLinksUntenNachRechtsOben(everything);

        int solution = 0;
        solution += findOccurrences(lines);
        solution += findOccurrences(columns);
        solution += findOccurrences(diagonals1);
        solution += findOccurrences(diagonals2);

        System.out.println("Ergebnis: " + solution);
    }

    public static List<String> getRows(String[][] matrix)
    {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++)
        {
            String[] line = matrix[i];
            String lineInput = "";
            for (int j = 0; j < line.length; j++)
            {
                lineInput += matrix[i][j];
            }
            list.add(lineInput);
        }
        return list;
    }

    public static List<String> getColumns(String[][] matrix)
    {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++)
        {
            String[] line = matrix[i];
            String lineInput = "";
            for (int j = 0; j < line.length; j++)
            {
                lineInput += matrix[j][i];
            }
            list.add(lineInput);
        }
        return list;
    }

    public static List<String> getDiagonalsVonLinksObenNachRechtsUnten(String[][] matrix)
    {
        List<String> list = new ArrayList<>();
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        int i = 0;
        for (int k = 0; k < columnCount; k++)
        {
            String lineInput = "";
            while (i < rowCount && (i + k) < columnCount)
            {
                lineInput += matrix[i][i + k];
                i++;
            }
            i = 0;
            list.add(lineInput);
        }
        
        for (int k = 1; k < rowCount; k++)
        {
            String lineInput = "";
            while (i + k < rowCount && i < columnCount)
            {
                lineInput += matrix[i + k][i];
                i++;
            }
            i = 0;
            list.add(lineInput);
        }

        return list;
    }

    public static List<String> getDiagonalsVonLinksUntenNachRechtsOben(String[][] matrix)
    {
        List<String> list = new ArrayList<>();
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        int i = 0;
        for (int k = rowCount - 1; k >= 0; k--)
        {
            String lineInput = "";
            while (i < rowCount && k - i >= 0)
            {
                lineInput += matrix[k - i][i];
                i++;
            }
            i = 0;
            list.add(lineInput);
        }

        for (int j = 1; j < columnCount; j++)
        {
            String lineInput = "";
            int l = rowCount - 1;
            int k = 0;
            while (l - k >= 0 && j + k < columnCount)
            {
                lineInput += matrix[l - k][j + k];
                k++;
            }
            list.add(lineInput);
        }

        return list;
    }

    public static int findOccurrences(List<String> list)
    {
        int result = 0;
        Pattern p1 = Pattern.compile("XMAS");
        Pattern p2 = Pattern.compile("SAMX");
            
        for (String el : list)
        {
            Matcher m1 = p1.matcher(el);
            while(m1.find()) {
                result += 1;
            }

            Matcher m2 = p2.matcher(el);
            while(m2.find()) {
                result += 1;
            }
        }

        return result;
    }

    public static void star2(String[][] input)
    {
        int rows = input.length;
        int columns = input[0].length;
        int solution = 0;
        for (int i = 1; i < rows - 1; i++)
        {
            for (int j = 1; j < columns - 1; j++)
            {
                if (input[i][j].equals("A"))
                {
                    String loru = input[i-1][j-1] + input[i+1][j+1];
                    String luro = input[i+1][j-1] + input[i-1][j+1];
                    if (loru.matches("SM|MS") && luro.matches("SM|MS"))
                    {
                        solution += 1;
                    }
                }
            }
        }
        System.out.println("Ergebnis: " + solution);
    }
}