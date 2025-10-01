package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import day_13.Day13SingleInput;
import java.util.regex.Matcher;
import day_14.PointWithVelocity;
import day_17.ChronospatialComputer;

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
            return null;
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

    public static Map<Long, long[]> readInputDay7(String filename)
    {
        Map<Long, long[]> input = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line = br.readLine();

            while (line != null)
            {
                String[] resultAndFactors = line.split(":");
                Long result = Long.parseLong(resultAndFactors[0]);
                long[] factors = Arrays.stream(resultAndFactors[1].trim().split(" ")).mapToLong(Long::parseLong).toArray();
                input.put(result, factors);
                line = br.readLine();
            }
            return input;
        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public static int[][] readInputAs2DIntArray(String filename)
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

                int[][] lines = new int[lnr.getLineNumber()][line.split("").length];

                while (line != null)
                {
                    lines[lineNumber] = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
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

    public static List<PointWithVelocity> getInputForDay14(String filename)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line = br.readLine();
            List<PointWithVelocity> input = new ArrayList<>();

            while (line != null)
            {
                Pattern p = Pattern.compile("p=(-{0,1}\\d+),(-{0,1}\\d+) v=(-{0,1}\\d+),(-{0,1}\\d+)");
                Matcher m = p.matcher(line);

                if(m.find())
                {
                    PointWithVelocity pointLine = new PointWithVelocity(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)));
                    input.add(pointLine);
                }
                
                line = br.readLine();
            }
            return input;
        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Day13SingleInput> getInputForDay13(String filename)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            List<Day13SingleInput> input = new ArrayList<>();

            while (line != null)
            {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }

            String wholeFile = sb.toString();

            Pattern p = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)\nButton B: X\\+(\\d+), Y\\+(\\d+)\nPrize: X=(\\d+), Y=(\\d+)");
            Matcher m = p.matcher(wholeFile);

            while (m.find())
            {
                Day13SingleInput singleInput = new Day13SingleInput(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)));
                input.add(singleInput);
            }

            return input;
        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public static ChronospatialComputer getChronospatialComputer(String filename)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }

            String wholeFile = sb.toString();

            Pattern registerAPattern = Pattern.compile("Register A: (\\d+)");
            Pattern registerBPattern = Pattern.compile("Register B: (\\d+)");
            Pattern registerCPattern = Pattern.compile("Register C: (\\d+)");
            Pattern programPattern = Pattern.compile("Program: ([\\d+|,]+)");

            Matcher matcherRegisterA = registerAPattern.matcher(wholeFile);
            Matcher matcherRegisterB = registerBPattern.matcher(wholeFile);
            Matcher matcherRegisterC = registerCPattern.matcher(wholeFile);
            Matcher matcherProgram = programPattern.matcher(wholeFile);

            matcherRegisterA.find();
            matcherRegisterB.find();
            matcherRegisterC.find();
            matcherProgram.find();
            int registerA = Integer.parseInt(matcherRegisterA.group(1));
            int registerB = Integer.parseInt(matcherRegisterB.group(1));
            int registerC = Integer.parseInt(matcherRegisterC.group(1));
            String programString = matcherProgram.group(1);
            List<Integer> program = Arrays.stream(programString.split(",")).mapToInt(Integer::parseInt).boxed().toList();

            ChronospatialComputer csp = new ChronospatialComputer(registerA, registerB, registerC, program);

            return csp;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
