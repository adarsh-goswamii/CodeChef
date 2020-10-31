import java.util.*;
import java.io.*;

public class ChefLikesGoodSequences {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    int[] arr;
    int[][] segment_tree;

    int[] build(int start, int last, int index)
    {
        if(start== last)
        {
            //segment_tree.set(index, arr.get(start));
            segment_tree[index]= new int[]{1, arr[start], arr[start]};
            return segment_tree[index];
            //return arr.get(start);
        }
        else
        {
            int mid= start + (last- start)/2;
            int[] left= build(start, mid, 2*index+1);
            int[] right= build(mid+1, last, 2*index+2);

            if(left[2]== right[1])
            {
                if(right[0]==1)
                    segment_tree[index]= new int[]{left[0], left[1], left[2]};
                else
                    segment_tree[index]= new int[]{left[0]+ right[0]-1, left[1], right[2]};
            }
            else
                segment_tree[index]= new int[]{left[0]+ right[0], left[1], right[2]};

            return segment_tree[index];

            //segment_tree.set(index, right+ left);
            //return segment_tree.get(index);
        }
    }

    void update(int start, int last, int index, int s_index, int val)
    {
        if(index>= start && index<=last) {
            if (start == last) {
                //out.println(segment_tree[s_index][0]+" "+segment_tree[s_index][1]+" "+segment_tree[s_index][2]);
                segment_tree[s_index]= new int[]{1, val, val};
                //out.println(segment_tree[s_index][0]+" "+segment_tree[s_index][1]+" "+segment_tree[s_index][2]);
            } else {
                int mid = start + (last - start) / 2;
                update(start, mid, index, 2 * s_index + 1, val);
                update(mid + 1, last, index, 2 * s_index + 2, val);
                //segment_tree[s_index][0] = Math.min(segment_tree[2 * s_index + 1][0], segment_tree[2 * s_index + 2][0]);
                int[] left= segment_tree[2 * s_index + 1];
                int[] right= segment_tree[2 * s_index + 2];

//                out.println();
//                out.println(segment_tree[2*s_index+1][0]+" "+segment_tree[2*s_index+1][1]+" "+segment_tree[2*s_index+1][2]);
//                out.println(segment_tree[2*s_index+2][0]+" "+segment_tree[2*s_index+2][1]+" "+segment_tree[2*s_index+2][2]);

                if(left[2]== right[1])
                {
                    if(right[0]==1)
                        segment_tree[s_index]= new int[]{left[0], left[1], left[2]};
                    else
                        segment_tree[s_index]= new int[]{left[0]+ right[0]-1, left[1], right[2]};
                }
                else
                    segment_tree[s_index]= new int[]{left[0]+ right[0], left[1], right[2]};


            }
        }
    }


    void solve() throws IOException {
        int t = ni();
        for (int ii = 0; ii < t; ii++)
        {
            int n=ni(), q= ni();
            arr= new int[n];
            segment_tree= new int[4*n][3];

            for(int i=0;i<n;i++)
                arr[i]= ni();

            build(0, n-1, 0);

            for(int i=0;i<q;i++)
            {
                int index= ni()-1, value= ni();
                update(0, n-1, index, 0, value);

                out.println(segment_tree[0][0]);
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
        new ChefLikesGoodSequences().run();
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

