import java.util.*;
import java.io.*;

public class ChefandSubtreeMEXs {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve() throws IOException {
        int t = ni();
        for (int ii = 0; ii < t; ii++) {
            int[] parent= new int[ni()+1];
            parent[1]= -1;

            for(int i=2;i< parent.length;i++)
                parent[i]= ni();

            HashSet<Integer> leaf= new HashSet<>();
            for(int i = 1;i<parent.length;i++)
                leaf.add(i);

            for(int i=2;i<parent.length;i++)
                leaf.remove(parent[i]);

            ArrayList<ArrayList<Integer>> arrayList= new ArrayList<>();
            for(int i=0;i< parent.length;i++)
                arrayList.add(new ArrayList<>());

            for(int i=2;i<parent.length;i++)
                arrayList.get(parent[i]).add(i);

            Queue<Integer> queue =  new LinkedList<>();
            queue.add(1);

            ArrayList<ArrayList<Integer>> level= new ArrayList<>();
            while(!queue.isEmpty())
            {
                int size= queue.size();
                ArrayList<Integer> temp= new ArrayList<>();
                for (int i = 0; i < size; i++)
                {
                    int curr= queue.poll();
                    temp.add(curr);

                    for(int j: arrayList.get(curr))
                        queue.add(j);
                }
                level.add(temp);
            }

            Collections.reverse(level);
            int lvl=0;
            int ans=0, val=1;
            for(ArrayList<Integer> integers: level)
            {
                if(lvl==0)
                {
                    ans+=1;
                    val= integers.size();
                }
                else
                {
                    for(int i=0;i<integers.size();i++)
                    {
                        if(leaf.contains(integers.get(i)))
                            val+=1;
                        else
                            ans+= ++val;
                    }
                }
                lvl++;
            }

            out.println(ans);
        }
    }

    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new ChefandSubtreeMEXs().run();
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

