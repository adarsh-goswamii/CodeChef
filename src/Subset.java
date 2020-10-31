// A Java program to print all subsets of a set
import java.io.IOException;
import java.util.*;

class Subset
{
    static boolean bool;
    public static void printSubsets(List<Integer> set, long summ, long sum, List<Integer> arr, int index)
    {
        if(index!= set.size() && !bool)
        {

            if(sum!= summ)
            {
                arr.add(set.get(index));
                printSubsets(set, summ, sum+set.get(index), arr, index+1);
                Integer remove= set.get(index);
                arr.remove(remove);
                printSubsets(set, summ, sum, arr, index+1);
                //System.out.println(arr);
            }
            else
            {
                printer(set, arr, sum);
                bool = true;
                return;
            }
        }
    }

    public static void printer(List<Integer> set, List<Integer> br, long sum)
    {
        //System.out.print(sum+"= ");
        Collections.reverse(set);
        for(int ii: set)
            if(br.contains(ii))
                System.out.print("0");
            else
                System.out.print("1");

        System.out.print(" , ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> arr=new ArrayList<>();//(Arrays.asList(1, 16, 81, 256, 625, 1296, 2401, 4096, 6561, 10000, 14641, 20736, 28561, 38416, 50625, 65536, 83521, 104976, 130321, 160000, 194481, 234256, 279841, 331776, 390625, 456976, 531441, 614656, 707281, 810000, 923521));
        long sum= 0;


        HashSet<Integer> set=new HashSet<>(Arrays.asList(63, 62, 43, 48,49,51,52,54,55,56,57,59,60));

        for(int i=1;i<=64;i++) {
            arr.add(i * i * i * i);
            sum+= i*i*i*i;
            ArrayList<Integer> temp=new ArrayList<>(arr);
            Collections.reverse(temp);
            bool= false;
            //long val= new GFG().findMin(arr, arr.size());
            System.out.println(i);
            if(i==37)
                printSubsets(temp, sum/ 2, 0, new ArrayList<>(), 0);
        }
    }
}
