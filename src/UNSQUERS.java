import java.util.*;
import java.io.*;

public class UNSQUERS {
    InputStream is;
    OutputStream out = new BufferedOutputStream ( System.out );
    String INPUT = "";

    void solve() throws IOException
    {
        int n= ni(), q= ni(), s=ni();
        int[] height= new int[n+1];
        for (int i = 1; i <=n; i++)
            height[i]= ni();

        int[] higher= new int[n+1];
        nextHigher(height, higher);

//        for(int i=0;i<higher.length;i++)
//            out.write((higher[i]+" ").getBytes());
//        out.flush();

        int[][] tree= new int[4*n][];
        build(1, higher.length-1, 0, tree, higher);

        for (int i = 0; i <=12; i++)
        {
            if(i==9|| i==10) continue;
            for (int j = 0; j < tree[i].length; j++)
                out.write((tree[i][j]+" ").getBytes());
            out.write("\n".getBytes());
            out.flush();
        }

//        int ans=0;
//        for (int i = 0; i < q; i++)
//        {
//            int x= ni(), y= ni();
//            int l= (x+ s*ans-1)%n+ 1;
//            int r= (y+ s*ans-1)%n+ 1;
//            if(l> r)
//            {
//                l= l^r;
//                r= l^r;
//                l= l^r;
//            }
//            ans= call(higher, l, r);
//
//            out.write((ans+"\n").getBytes());
//        }

    }

    private void nextHigher(int[] height, int[] higher) {
        Stack<Integer> stack= new Stack<>();

        for (int i =height.length-1; i>=1; i--)
        {
            while(!stack.isEmpty() && height[stack.peek()]<=height[i])
                stack.pop();

            if(stack.isEmpty())
                higher[i]= height.length;
            else
                higher[i]= stack.peek();

            stack.add(i);
        }
    }

    private int call(int[] higher, int l, int r)
    {
        int[] memo= new int[higher.length];
        int max=0;
        for(int i=r;i>=l;i--)
        {
            if(higher[i]<=r)
                memo[i]= memo[higher[i]];
            memo[i]++;
            max= Math.max(max, memo[i]);
        }

        return max;
    }

    int[] build(int start, int last, int index, int[][] tree, int[] higher)
    {
        if(start== last)
        {
            tree[index]= new int[]{1, 1};
            return tree[index];
        }
        else
        {
            int mid= start+ (last- start)/2;
            int[] left= build(start, mid, 2*index+1, tree, higher);
            int[] right= build(mid+1, last, 2*index+2, tree, higher);

            tree[index]= new int[left.length+ right.length-1];
            int max= Math.max(left[left.length-1], right[right.length-1]);

            int i;
            for(i=0;i<left.length-1;i++)
            {
                if(higher[start+i]<= last && higher[start+i]>mid)
                {
                    tree[index][i]= left[i]+ right[higher[start+i]- mid];
                }
                else
                    tree[index][i]= left[i];
                max= Math.max(tree[index][i], max);
            }

            for(int j=0;j<right.length-1;j++)
            {
                tree[index][i+j]= right[j];
            }

            tree[index][tree[index].length-1]= max;
            return tree[index];
        }
    }


    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
//        out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new UNSQUERS().run();
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

