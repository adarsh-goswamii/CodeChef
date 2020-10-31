import java.util.*;

class SumPossible
{

    // maximum size of x
    int MAX = 1000;

    // to check whether x is possible or not
    static int []ispossible = new int[1000];
    static void check(int[] arr, int N)
    {

        ispossible[0] = 1;
        Arrays.sort(arr);

        for (int i = 0; i < N; ++i) {
            int val = arr[i];

            // if it is already possible
            if (ispossible[val] == 1)
                continue;

            // make 1 to all it's next elements
            for (int j = 0; j + val < 1000; ++j)
                if (ispossible[j]== 1)
                    ispossible[j + val] = 1;
        }
    }

    // Driver code
    public static void main(String args[])
    {
        int[] arr = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121};
        int N = arr.length;
        check(arr, N);
        int x = 253;
        if (ispossible[x]== 1)
            System.out.println(x+" is possible.");
        else
            System.out.println(x+" is not possible.");

    }
}