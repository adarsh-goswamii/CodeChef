import java.util.*;
import java.io.*;

class DDIMMST {
    InputReader in;
    PrintWriter out;

    void solve()
    {
        int n= in.nextInt(), d= in.nextInt();
        int[][] points=new int[n][d];

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<d;j++)
                points[i][j]= in.nextInt();
        }

        // PRIMS'S ALGORITHM
        prims_mst(n, points);

    }

        class node {
            int vertex;
            int key;
        }

        class comparator implements Comparator<node>
        {
            @Override
            public int compare(node node0, node node1)
            {
                return node0.key - node1.key;
            }
        }

        void prims_mst(int V, int[][] points)
        {

            int ans=0;
            Boolean[] mstset = new Boolean[V];
            node[] e = new node[V];

            int[] parent = new int[V];

            for (int o = 0; o < V; o++)
                e[o] = new node();

            for (int o = 0; o < V; o++)
            {
                mstset[o] = false;

                e[o].key = Integer.MIN_VALUE;
                e[o].vertex = o;
                parent[o] = -1;
            }

            mstset[0] = true;
            e[0].key = 0;

            TreeSet<node> queue = new TreeSet<node>(new comparator());

            for (int o = 0; o < V; o++)
                queue.add(e[o]);

            while (!queue.isEmpty())
            {
                node node0 = queue.pollLast();
                mstset[node0.vertex] = true;
                ans+= node0.key;

                for (int i=0;i<V;i++)
                {

                    // If V is in queue
                    if (mstset[i] == false) {
                        // If the key value of the adjacent vertex is
                        // more than the extracted key
                        // update the key value of adjacent vertex
                        // to update first remove and add the updated vertex
                        int dist= distance(points[i], points[node0.vertex]);
                        if (e[i].key < dist)
                        {
                            queue.remove(e[i]);
                            e[i].key = dist;
                            queue.add(e[i]);
                            parent[i] = node0.vertex;
                        }
                    }
                }
            }

           out.println(ans);
        }

        int distance(int[] pointA, int[] pointB)
        {
            int ret=0;
            for(int i=0;i<pointA.length;i++)
                ret+= Math.abs(pointA[i]- pointB[i]);

            return ret;
        }


    void run() throws Exception {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new DDIMMST().run();
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
