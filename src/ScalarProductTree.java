import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

class ScalarProductTree {
    InputStream is;
    OutputStream out = new BufferedOutputStream ( System.out );
    String INPUT = "";

    void solve() throws IOException
    {
        int n= ni(), q= ni();
        ArrayList<ArrayList<Integer>> arrayLists= new ArrayList<>();
        int[] weight= new int[n+1];
        int[] parent= new int[n+1];
        long[] pre= new long[n+1];
        for(int i=1;i<=n;i++)
            weight[i]= ni();

        for (int i = 0; i <= n; i++)
            arrayLists.add(new ArrayList<>());

        for (int i = 0; i < n-1; i++)
        {
            int par= ni(), child= ni();
            parent[child]= par;
            arrayLists.get(par).add(child);
        }
        parent[1]= -1;

        Queue<Long> queue= new LinkedList<>();
        queue.add(1l);
        queue.add(0l);

        int lvl=0;
        int[] level= new int[n+1];

        while(!queue.isEmpty())
        {
            int size= queue.size();

            for(int i=0;i<size/2;i++)
            {
                long lcurr= queue.poll();
                int curr= (int) lcurr;

                long val= queue.poll();
                val= (val+ weight[curr]* weight[curr])&0xFFFFFFFFL;
                pre[(int)curr]= val;
                level[curr]= lvl;

                for(int j: arrayLists.get(curr))
                {
                    queue.add((long)j);
                    queue.add(val);
                }
            }
            lvl++;
        }

        ArrayList<Integer[]> query=new ArrayList<>();
        for(int i=0;i<q;i++)
            query.add(new Integer[]{ni(), ni(), i});

        long[] ans= new long[q];
        Comparator<Integer[]> comp= new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                final int i = level[a[0]] - level[b[0]];
                return i;
            }
        };

        Collections.sort(query, comp);
        HashMap<Integer, HashMap<Integer, Long>> memo= new HashMap<>();
        for(Integer[] i: query)
        {
            int u= i[0], v= i[1], curr= i[2];
            while(u!=-1)
            {
                if(u==v)
                {
                    ans[curr]= (ans[curr]+ pre[u])&0xFFFFFFFFL;
                    break;
                }
                else if(memo.containsKey(u) && memo.get(u).containsKey(v))
                { ans[curr]= (ans[curr]+ memo.get(u).get(v))& 0xFFFFFFFFL; break; }
                else if(memo.containsKey(v) && memo.get(v).containsKey(u))
                { ans[curr]= (ans[curr]+ memo.get(v).get(u))& 0xFFFFFFFFL; break; }

                ans[curr]= (ans[curr]+ (long)weight[u]*weight[v])& 0xFFFFFFFFL;
                u= parent[u];
                v= parent[v];
            }

            if(memo.containsKey(i[0]))
                memo.get(i[0]).put(i[1], ans[curr]);
            else
            {
                HashMap<Integer, Long> temp= new HashMap<>();
                temp.put(i[1], ans[curr]);
                memo.put(i[0], temp);
            }

            if(memo.containsKey(i[1]))
                memo.get(i[1]).put(i[0], ans[curr]);
            else
            {
                HashMap<Integer, Long> temp= new HashMap<>();
                temp.put(i[0], ans[curr]);
                memo.put(i[1], temp);
            }
        }

        for(Long a: ans)
            out.write((a+"\n").getBytes());
    }

    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        //out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new ScalarProductTree().run();
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

