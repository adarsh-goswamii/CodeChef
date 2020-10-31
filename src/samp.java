// Java program to print all subsets with given sum
import java.util.*;
class GFG2
{

    // The vector v stores current subset.
    static void printAllSubsetsRec(int arr[], int n, Vector<Integer> v,
                                   int sum)
    {
        // If remaining sum is 0, then print all
        // elements of current subset.
        if (sum == 0) {
            for (int i=0;i<v.size();i++)
                System.out.print( v.get(i) + " ");
            System.out.println();
            return;
        }

        // If no remaining elements,
        if (n == 0)
            return;

        // We consider two cases for every element.
        // a) We do not include last element.
        // b) We include last element in current subset.
        printAllSubsetsRec(arr, n - 1, v, sum);
        Vector<Integer> v1=new Vector<Integer>(v);
        v1.add(arr[n - 1]);
        printAllSubsetsRec(arr, n - 1, v1, sum - arr[n - 1]);
    }

    // Wrapper over printAllSubsetsRec()
    static void printAllSubsets(int arr[], int n, int sum)
    {
        Vector<Integer> v= new Vector<Integer>();
        printAllSubsetsRec(arr, n, v, sum);
    }

    // Driver code
    public static void main(String args[])
    {
        int set[] = {1,16,81,256,625,1296,2401,4096,6561,10000,14641,20736,28561,38416,50625,65536,83521,104976,130321,160000,194481,234256,279841,331776,390625,456976,531441,614656,707281,810000,923521,1048576,1185921,1336336,1500625,1679616,1874161};
        HashSet<Integer> sets=new HashSet<>(Arrays.asList(1500625, 923521, 810000, 707281, 614656, 531441, 456976, 390625, 331776, 279841, 194481, 160000, 130321 ,104976, 83521 ,65536 ,50625 ,28561 ,20736 ,14641 ,6561 ,2401 ,1296 ,625 ,256 ,81 ,16 ,1));

        for(int i: set)
            if(sets.contains(i))
                System.out.print("1");
            else
                System.out.print("0");


    }
}
//contributed by Arnab Kundu


/* This code is contributed by Rajat Mishra */
