//import java.util.*;
import java.util.*;

import java.io.*;
class SUBSFREQ
{
    static long[] ans;
    static final int MOD= 1000000007;

    /**
     *  Function for calculating combinations of form nCk.
     */
    static int nCk(int n, int k)
    {
        int C[] = new int[k + 1];
        C[0] = 1;

        for (int i = 1; i <= n; i++)
        {
            for (int j = Math.min(i, k); j > 0; j--)
                C[j] = C[j] + C[j-1];
        }
        return C[k];
    }

    /**
     *  For Debugging purpose subsequence generator function.
     */
    static void GS(int[] arr, int index, ArrayList<Integer> sub)
    {
        if(index!= arr.length)
        {
            sub.add(arr[index]);
            GS(arr, index+1, sub);
            sub.remove(sub.size()-1);
            GS(arr, index+1, sub);
        }
        else
            System.out.print(sub+", ");
    }

    public static void main(String[] args) throws IOException
    {
        //PrintWriter w= new PrintWriter(System.out);
        //InputReader in=new InputReader(System.in);
        FileInputStream fis = new FileInputStream("input.txt");
        InputReader in = new InputReader(fis);
        PrintWriter w = new PrintWriter("output.txt");

        int t= in.nextInt();
        for(int ii=0;ii<t;ii++)
        {
            int n= in.nextInt();
            int[] arr=new int[n];
            int[] map=new int[n+1];
            ans= new long[n+1];

            for(int i=0;i<n;i++)
                arr[i]= in.nextInt();

            for(int i: arr)
                map[i]++;

            long[] power=new long[n+1];
            power[0]= 1;
            for(int i=1;i<=n;i++)
                power[i]= (power[i-1]<<1)% MOD;

            GS(arr, 0, new ArrayList<Integer>());
            System.out.println();

            for(int i=1;i<=n;i++) // i: value for which we are calculating
            {
                int freq= map[i] ;
                for(int j=freq;j>0;j--) // j: frequency of i
                {
                    int total= 1, mul=nCk(freq, j);
                    for(int k=1;k<map.length;k++) // k: iterating through the array
                    {
                        if(k==i) continue;
                        if(map[k]< j)
                            total+= map[k];
                        else if(map[k]== j)
                        {
                            if(k> i)
                                total+= map[k];
                            else
                            {
                                total+= map[k]-1;
                                mul*= map[k];
                            }
                        }
                        else {
                            if (k > i)
                                total += j;
                            else
                            {
                                total += j- 1;
                                mul*= map[k];
                            }
                        }
                    }

                    ans[i]= (ans[i]+ (power[total-1]* mul)% MOD)% MOD;
                    w.println(mul+" "+i+" for freq "+j+" = "+total+" = "+ans[i]);

                    //w.flush();
                }
            }

            for(int i=1;i<ans.length;i++)
                w.print(ans[i]+" ");
            w.println();
        }
        w.close();
    }

    static class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextint() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String readString() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public String nextLine() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}