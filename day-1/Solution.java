import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution 
{
    public static void main(String[] args)
    {
        int distance = 0;
        List<Integer> leftArray = new ArrayList<Integer>();
        List<Integer> rightArray = new ArrayList<Integer>();

        try(BufferedReader br = new BufferedReader(new FileReader("input.txt")))
        {
            String line = br.readLine();

            while (line != null)
            {
                String[] splitLine = line.split("\\s+");
                leftArray.add(Integer.parseInt(splitLine[0]));
                rightArray.add(Integer.parseInt(splitLine[1]));
                line = br.readLine();
            }

            Collections.sort(leftArray);
            Collections.sort(rightArray);

            for (int i = 0; i < leftArray.size(); i++)
            {
                distance += Math.abs(rightArray.get(i) - leftArray.get(i));
            }
            
            System.out.println("Distance: " + distance);
        }
        catch(Exception e) 
        {
            //
        }
    }
}