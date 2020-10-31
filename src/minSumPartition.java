import java.io.*;
import java.util.ArrayList;

class GFG
{
    static int findMin(ArrayList<Integer> arr, int n)
    {
        // Calculate sum of all elements
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr.get(i);

        // Create an array to store
        // results of subproblems
        boolean dp[][] = new boolean[n + 1][sum + 1];

        // Initialize first column as true.
        // 0 sum is possible  with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Initialize top row, except dp[0][0],
        // as false. With 0 elements, no other
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        // Fill the partition table
        // in bottom up manner
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr.get(i-1) <= j)
                    dp[i][j] |= dp[i - 1][j - arr.get(i-1)];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        for (int j = sum / 2; j >= 0; j--)
        {
            // Find the
            if (dp[n][j] == true)
            {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    public static void main (String[] args)
    {
        ArrayList<Integer> arr=new ArrayList<>();
        long sum=0;
        for(int i=1;i<=64;i++)
        {
            arr.add(i*i*i*i);
            sum+= i*i*i*i;
            int ans= findMin(arr, arr.size());
            System.out.print(ans+" ");
            //new Subset().printSubsets(arr, (sum-ans)/2);
        }
    }
}
// This code is contributed by vt_m