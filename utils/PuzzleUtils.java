package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleUtils {
    public static String readInputAsString(String filename)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            String everything = sb.toString();

            return everything;
        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return "Big Oopsie";
        }
    }

    public static List<String> readInputAsList(String filename)
    {
        List<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line = br.readLine();

            while (line != null)
            {
                list.add(line);
                line = br.readLine();
            }

            return list;
        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return Arrays.asList("Big Oopsie");
        }
    }

    public static String[][] readInputAs2DArray(String filename)
    {
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(filename)))
        {
            while ((lnr.readLine()) != null)
            {
                //
            }

            try(BufferedReader br = new BufferedReader(new FileReader(filename)))
            {
                int lineNumber = 0;
                String line = br.readLine();

                String[][] lines = new String[lnr.getLineNumber()][line.split("").length];

                while (line != null)
                {
                    lines[lineNumber] = line.split("");
                    line = br.readLine();
                    lineNumber++;
                }

                return lines;
            }
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Map<Integer, List<Integer>> readInputAsMap(String filename)
    {
        Map<Integer, List<Integer>> map = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line = br.readLine();

            while (line != null)
            {
                String[] lineArray = line.split("\\|");
                //System.out.println(Arrays.toString(lineArray));
                List<Integer> possibleValue = map.get(Integer.parseInt(lineArray[0]));
                if (possibleValue != null)
                {
                    possibleValue.add(Integer.parseInt(lineArray[1]));
                    map.put(Integer.parseInt(lineArray[0]), possibleValue);
                }
                else
                {
                    map.put(Integer.parseInt(lineArray[0]), new ArrayList<Integer>(Arrays.asList(Integer.parseInt(lineArray[1]))));
                }
                line = br.readLine();
            }

            return map;
        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static List<int[]> readInputAsIntArray(String filename)
    {
        List<int[]> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line = br.readLine();

            while (line != null)
            {
                int[] lineArray = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                list.add(lineArray);
                line = br.readLine();
            }

            return list;
        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
