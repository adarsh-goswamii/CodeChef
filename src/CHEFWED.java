import java.io.*;
import java.util.*;
class CHEFWED
{
    static int dispute(int[] map)
    {
        int dispute=0;
        for(int i=1;i<101;i++)
            dispute+= map[i]>1? map[i]: 0;
        return dispute;
    }

    static int[] map(int[] family, int start, int end)
    {
        int[] map=new int[101];
        for(int i=start;i<=end;i++)
            map[family[i]]++;
        return map;
    }

    static int call(int family[], int[] map, int start, int end, int k, int dispute)
    {
        int[] map2=new int[101];
        int count=0, max=0, mid=-1;
        for(int i=start;i<=end;i++)
        {
            int val= family[i];
            if(map[val]<=1)
                continue;
            if(map2[val]==0)
                count++;
            else if(map2[val]==1)
                count--;
            if(max<=count)
            {
                mid= i;
                max= count;
            }
            map2[val]++;
        }
        int[] l_map= map(family, start, mid);
        int[] r_map= map(family, mid+1, end);
        int l_dispute= dispute(l_map);
        int r_dispute= dispute(r_map);

        if(dispute <(l_dispute+ r_dispute+ k))
            return dispute+ k;
        else
        {
            for(int i=0;i<101;i++)
                map[i]-= map2[i];


            int left= call(family, l_map, start, mid, k, l_dispute);
            int right= call(family, r_map, mid+1, end, k, r_dispute);
            return left+ right;
        }
    }

    public static void main(String[] args)throws IOException
    {
        //PrintWriter w= new PrintWriter(System.out);
        //InputReader in=new InputReader(System.in);
        FileInputStream fis = new FileInputStream("input.txt");
        InputReader in = new InputReader(fis);
        PrintWriter w = new PrintWriter("output.txt");

        int t= Integer.parseInt(in.nextLine());
        for(int ii=0;ii<t;ii++)
        {
            int n= in.nextInt(), k= in.nextInt();
            int[] family=new int[n];

            for(int i=0;i<n;i++)
                family[i]= in.nextInt();


            int[] map= map(family, 0, family.length-1);
            if(k==1)
            {
                int max=0;
                for(int i: map)
                    max= Math.max(max, i);
                w.println(max);
                continue;
            }
            w.println(call(family, map, 0, family.length-1, k, dispute(map)));
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