import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolutionDay5 {
    public static void main(String[] args)
    {
        star1();
    }

    public static void star1()
    {
        int solution = 0;
        Map<Integer, List<Integer>> inputRules = PuzzleUtils.readInputAsMap("C:\\\\Users\\\\thoma\\\\Github\\\\advent-of-code-2024\\\\day-5\\\\input1.txt");
        List<int[]> inputPages = PuzzleUtils.readInputAsIntArray("C:\\\\Users\\\\thoma\\\\Github\\\\advent-of-code-2024\\\\day-5\\\\input2.txt");
        //System.out.println(inputRules.toString());
        //System.out.println(inputPages.toString());
        outerLoop:
        for (int[] pageList : inputPages)
        {
            //System.out.println(Arrays.toString(pageList));
            for (int i = 0; i < pageList.length; i++)
            {
                List<Integer> pagesBefore = getElementsBefore(pageList, i);
                boolean check = checkCorrectOrder(pagesBefore, inputRules.get(pageList[i]));
                if (check != true)
                {
                    continue outerLoop;
                }
            }
            solution += addToSolution(pageList);
        }
        System.out.println("Ergebnis: " + solution);
    }

    public static List<Integer> getElementsBefore(int[] pageList, int k)
    {
        List<Integer> returnList = new ArrayList<>();
        for (int i = 0; i < k; i++)
        {
            returnList.add(pageList[i]);
        }
        return returnList;
    }

    public static boolean checkCorrectOrder(List<Integer> pagesBefore, List<Integer> rules)
    {
        for (Integer i : pagesBefore)
        {
            if (rules.contains(i))
            {
                return false;
            }
        }
        return true;
    }

    public static int addToSolution(int[] pageList)
    {
        int n = pageList.length;
        int mid = n / 2;
        return pageList[mid];
    }
}
