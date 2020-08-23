import java.util.*;
import java.io.*;

public class RBTREES
{
    List<List<Integer>> red;
    List<List<Integer>> black;
    int red_count, black_count;
    char color_black, color_red;
    int route;

    void DFSREC(List<List<Integer>> list, char[] color, HashSet<Integer> seen, int curr, int val )
    {
        if(color_black!= color[curr-1])
        {

        }
    }

    void BFS(List<List<Integer>> list, HashSet<Integer> seen)
    {
        Queue<Integer> q=new LinkedList<>();
        seen.add(1);
        q.add(1);
        boolean bool= true;

        while(!q.isEmpty())
        {
            int size= q.size();
            List<Integer> temp= new ArrayList<>();

            for(int i=0;i<size;i++)
            {
                int curr= q.poll();
                temp.add(curr);

                for(int j: list.get(curr))
                {
                    if(seen.contains(j)) continue;
                    q.add(j);
                    seen.add(j);
                }
            }

            if(bool)
            {
                red.add(temp);
                red_count+= temp.size();
            }
            else
            {
                black.add(temp);
                black_count+= temp.size();
            }
            bool=!bool;
        }
    }
    void solve()
    {
        out= new PrintWriter(System.out);
        in=new InputReader(System.in);

        int t= in.nextInt();

        for(int ii=0;ii<t;ii++)
        {
            red= new ArrayList<>();
            black= new ArrayList<>();
            red_count=0;
            black_count=0;

            int n= in.nextInt();
            List<List<Integer>> list=new ArrayList<>();
            for(int i=0;i<n+1;i++)
                list.add(new ArrayList<>());

            for(int i=1;i<n;i++)
            {
                int u= in.nextInt(), v= in.nextInt();
                list.get(u).add(v);
                list.get(v).add(u);
            }

            //sc.nextLine();
            char[] color= in.readString().toCharArray();
            HashSet<Integer> seen=new HashSet<>();
            BFS(list, seen);

            int b=0, r=0;
            color_black= '1';
            color_red= '0';
            for(char c: color)
                if(c=='1')
                    b++;
                else
                    r++;

            if(red_count< black_count)
            {
                List<List<Integer>> swap= red;
                red= black;
                black= swap;

                red_count= red_count^black_count;
                black_count= red_count^black_count;
                red_count= red_count^black_count;
            }

            if(r<b)
            {
                r= r^b;
                b= r^b;
                r= r^b;
                color_red= '1';
                color_black= '0';
            }

            if(b!= black_count || red_count!=r)
                out.println(-1);
            else
            {

            }
        }
        out.close();
    }

    public static void main(String[] args) throws Exception {
        new RBTREES().solve();
    }

    InputReader in;
    PrintWriter out;
    String INPUT = "";

    static class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private InputReader.SpaceCharFilter filter;

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
