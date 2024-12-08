package day_7;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import utils.PuzzleUtils;

public class SolutionDay7
{
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        String filePath = new File("").getAbsolutePath();
        Map<Long, long[]> input = PuzzleUtils.readInputDay7(filePath + "/day_7/input.txt");
        Long totalSolution = 0L;
        nextLine:
        for (Map.Entry<Long, long[]> line : input.entrySet()) {
            System.out.println(line.getKey() + "/" + Arrays.toString(line.getValue()));
            Integer binaryCombinationNumber = ((int) Math.pow(2, line.getValue().length - 1)) - 1;
            //System.out.println("BinCombNumber: " + binaryCombinationNumber);
            for (int i = 0; i <= binaryCombinationNumber; i++)
            {
                String format = "%" +  Integer.toBinaryString(binaryCombinationNumber).length() + "s";
                String binaryCombinationString = String.format(format, Integer.toBinaryString(i)).replace(" ", "0");
                //System.out.println("BinCombString: " + binaryCombinationString);
                Long result = line.getValue()[0];
                for (int j = 0; j < binaryCombinationString.length(); j++){
                    char c = binaryCombinationString.charAt(j);
                    //System.out.println("Current operation: " + c);        
                    if (c == '0')
                    {
                        result += line.getValue()[j + 1]; 
                    }
                    else
                    {
                        result *= line.getValue()[j + 1];
                    }
                }
                //System.out.println("berechnet: " + result + ", was es sein sollte: " + line.getKey());
                if (result.equals(line.getKey()))
                {
                    totalSolution += line.getKey();
                    continue nextLine;
                }
            }
        }

        System.out.println("Ergebnis: " + totalSolution);
    }
}
