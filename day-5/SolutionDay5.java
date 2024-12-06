package day_5;

import utils.PuzzleUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SolutionDay5 {
    public static void main(String[] args)
    {
        //star1();
        star2();
    }

    public static void star1()
    {
        int solution = 0;
        String filePath = new File("").getAbsolutePath();
        Map<Integer, List<Integer>> inputRules = PuzzleUtils.readInputAsMap(filePath + "/day-5/input1.txt");
        List<int[]> inputPages = PuzzleUtils.readInputAsIntArray(filePath + "/day-5/input2.txt");
        outerLoop:
        for (int[] pageList : inputPages)
        {
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
            if (rules!= null && rules.contains(i))
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

    public static void star2()
    {
        int solution = 0;

        String filePath = new File("").getAbsolutePath();
        Map<Integer, List<Integer>> rules = PuzzleUtils.readInputAsMap(filePath + "/day-5/input1.txt");

        List<int[]> badPages = getBadPages();

        System.out.println("Bad ones: ");
        for (int[] badPage : badPages)
        {
            System.out.println(Arrays.toString(badPage));
        }

        List<int[]> fixedPages = reorder(badPages, rules);

        System.out.println("Fixed ones: ");
        for (int[] fixedPage : fixedPages)
        {
            System.out.println(Arrays.toString(fixedPage));
            solution += addToSolution(fixedPage);
        }

        System.out.println("Solution: " + solution);
    }

    public static List<int[]> getBadPages()
    {
        {
            List<int[]> badPages = new ArrayList<>();
            String filePath = new File("").getAbsolutePath();
            Map<Integer, List<Integer>> inputRules = PuzzleUtils.readInputAsMap(filePath + "/day-5/input1.txt");
            List<int[]> inputPages = PuzzleUtils.readInputAsIntArray(filePath + "/day-5/input2.txt");

            for (int[] page : inputPages)
            {
                System.out.println(Arrays.toString(page));
            }

            outerLoop:
            for (int[] pageList : inputPages)
            {
                for (int i = 0; i < pageList.length; i++)
                {
                    List<Integer> pagesBefore = getElementsBefore(pageList, i);
                    boolean check = checkCorrectOrder(pagesBefore, inputRules.get(pageList[i]));
                    if (check != true)
                    {
                        badPages.add(pageList);
                        continue outerLoop;
                    }
                }
            }
            return badPages;
        }
    }

    public static List<int[]> reorder(List<int[]> badPages, Map<Integer, List<Integer>> rules)
    {
        List<int[]> fixedPages = new ArrayList<>();
        for (int[] badPage : badPages) {
            List<Integer> fixedPage = new ArrayList<>();
            while (badPage.length > 1)
            {
                for (int i = 0; i < badPage.length; i++) {
                    if (!violatesRest(remove(badPage, i), badPage[i], rules)) {
                        fixedPage.add(badPage[i]);
                        badPage = remove(badPage, i);
                    }
                }
            }
            fixedPage.add(badPage[0]);
            int[] fixePageArray = fixedPage.stream().mapToInt(Integer::intValue).toArray();
            fixedPages.add(fixePageArray);
        }
        return fixedPages;
    }

    public static boolean violatesRest(int[] rest, int value, Map<Integer, List<Integer>> rules)
    {
        List<Integer> mustnViolate = new ArrayList<>();
        for (int i = 0; i < rest.length; i++)
        {
            List<Integer> rule = rules.get(rest[i]);
            if (rule != null)
            {
                mustnViolate.addAll(rule);
            }
        }
        if (mustnViolate.contains(value))
        {
            return true;
        }
        return false;
    }

    public static int[] remove(int[] arr, int in) {
        int[] arr2 = new int[arr.length - 1];

        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == in)
            {
                continue;
            }

            arr2[k++] = arr[i];
        }

        return arr2;
    }
}
