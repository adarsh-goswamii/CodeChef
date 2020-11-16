import java.util.*;
import java.io.*;

class Node
{
    int var;
    HashSet<Integer> set;
    int cost;

    public Node(int var, HashSet<Integer> set, int cost) {
        this.var = var;
        this.set = set;
        this.cost = cost;
    }

    public Node()
    {
        var=0;
        set= new HashSet<>();
        cost= 0;
    }
}

class RB2CNF {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve()
    {
        int t= ni();
        for (int ii = 0; ii < t; ii++)
        {
            int n= ni(), m= ni();
            char[] arr= ns(n);
            boolean[] values= new boolean[n+1];
            for (int i = 1; i <=n; i++)
                values[i]= arr[i-1]=='1'? true: false;

            int[] cost= new int[n+1];
            for (int i = 1; i <=n; i++)
                cost[i]= ni();

            ArrayList<ArrayList<Integer>> arrayLists= new ArrayList<>();
            for (int i = 0; i <=n; i++)
                arrayLists.add(new ArrayList<>());

            int[][] clause= new int[m][];
            for (int i = 0; i < m; i++)
            {
                int p=ni(), q=ni();
                clause[i]= new int[]{p, q};
                int x= Math.abs(clause[i][0]);
                int y= Math.abs(clause[i][1]);

            }

            Comparator<Node> comp= new Comparator<Node>() {
                @Override
                public int compare(Node a, Node b) {
                    return a.cost- b.cost;
                }
            };

            PriorityQueue<Node> q= new PriorityQueue<Node>(comp);
            for (int i = 0; i < m; i++)
            {
                int x= Math.abs(clause[i][0]);
                int y= Math.abs(clause[i][1]);
                boolean xb= clause[i][0]<0? !values[x]: values[x];
                boolean yb= clause[i][1]<0? !values[y]: values[y];

                if(!(xb || yb))
                {
                    Node node= new Node(x, new HashSet<>(), 0);
                    Node node2= new Node(y, new HashSet<>(), 0);
                    q.add(node);
                    q.add(node2);
                }
            }

            HashSet<Integer> changed= new HashSet<>();
            int min_cost=0;
            while(true)
            {
                Node curr= q.poll();
                //DFSREC(curr.var, curr.set, list);
            }
        }
    }

    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new RB2CNF().run();
    }

    private byte[] inbuf = new byte[1024];
    public int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private double nd() {
        return Double.parseDouble(ns());
    }

    private char nc() {
        return (char) skip();
    }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) map[i] = ns(m);
        return map;
    }

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private void tr(Object... o) {
        if (INPUT.length() > 0) System.out.println(Arrays.deepToString(o));
    }
}

