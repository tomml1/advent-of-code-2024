import java.io.BufferedReader;
import java.io.FileReader;

public class SolutionDay4 {
    public static void main(String[] args)
    {
        star1();
        //star2();
    }

    public static void star1()
    {
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thoma\\Github\\advent-of-code-2024\\day-4\\input.txt")))
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            String everything = sb.toString();

            System.out.println("Alles: " + everything);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
}