import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolutionDay3
{
    public static void main(String[] args)
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
            Pattern p = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Matcher m = p.matcher(everything);

            while(m.find()) {
                System.out.println(m.group(0));
                solution += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
            }

            System.out.println("Ergebins: " + solution);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
}
