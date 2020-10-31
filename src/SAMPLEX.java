import java.util.*;
import java.io.*;


class SAMPLEX {
    InputReader in;
    PrintWriter out;

    int binarySearch(int[] arr, int start, int last, int find)
    {
        while(start<= last)
        {
            int mid= start+ (last- start)/2;

            if(arr[mid]== find)
                return mid;
            else if(arr[mid]< find)
                start= mid+1;
            else
                last= mid-1;
        }
        return last;
    }

    void solve()
    {
        int t= in.nextInt();

        for(int ii=0;ii<t;ii++)
        {
            int n= in.nextInt(), x= in.nextInt(), p= in.nextInt(), k= in.nextInt();
            int[] arr= new int[n];

            for(int i=0;i<n;i++)
                arr[i]= in.nextInt();

            Arrays.sort(arr);
            int index= binarySearch(arr, 0, arr.length-1, x);
            int smaller= index+1, ans=-1;

            if(smaller< p)
            {
                if(p <=k)
                    ans=Math.abs(smaller- p);
            }
            else
            {
                if(p >=k)
                    ans=Math.abs(smaller- p);
            }

            if(ans!=-1 && arr[index]!= x)
                out.println(ans+1);
            else
                out.println(ans);

        }
    }

    void run() throws Exception {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new SAMPLEX().run();
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

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            long res = 0;
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
