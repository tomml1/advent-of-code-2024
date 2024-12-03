import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolutionDay3
{
    public static void main(String[] args)
    {
        //star1();
        star2();
    }

    public static void star1()
    {
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-3\\input.txt")))
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            int solution = doMultiplications(sb.toString());

            System.out.println("Ergebins: " + solution);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static int doMultiplications(String rawString)
    {
        int solution = 0;
        Pattern p = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher m = p.matcher(rawString);

        while(m.find()) {
            //System.out.println(m.group(0));
            solution += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
        }
        return solution;
    } 

    public static void star2()
    {
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-3\\input.txt")))
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            int solution = 0;
            String everything = sb.toString();
            String firstPart = everything.split("(do\\(\\)|don't\\\\(\\\\))")[0];

            String[] everythingSplitByDont = everything.split("don't\\(\\)");
            String relevantForLastPart = everythingSplitByDont[everythingSplitByDont.length - 1];
            String[] relevantForLastPartSplitByDo = relevantForLastPart.split("do\\(\\)");
            
            String lastPart = "";
            int i = 1;

            while (i < relevantForLastPartSplitByDo.length)
            {
                lastPart += relevantForLastPartSplitByDo[i];
                i++;
            }

            solution += doMultiplications(firstPart);
            solution += doMultiplications(lastPart);
            Pattern p = Pattern.compile("do\\(\\)(.*?)don't\\(\\)");
            Matcher m = p.matcher(everything);

            while(m.find()) {
                solution += doMultiplications(m.group(1));
            }

            System.out.println("Ergebins: " + solution);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
}
