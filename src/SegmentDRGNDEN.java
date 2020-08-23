import java.util.*;
import java.io.*;
class SegmentDRGNDEN
{
    static long[][] tree;
    static long[][] tree2;
    static int[] height;
    static int[] height2;
    static int[] taste;
    static int[] taste2;
    InputStream is;
    PrintWriter w;
    String INPUT = "";



    static long[] merge(long[] left, long[] right, int[] height, int[] taste)
    {
        if(left.length==1)
            return right;
        else if(right.length==1)
            return left;

        int max;
        max= height[Math.toIntExact(right[1])];

        int size=0;
        for(int i=1;i<left.length;i++)
        {
            if(max< height[Math.toIntExact(left[i])])
                size++;
        }

        for(int i=1;i<right.length;i++)
        {
            if(right[i]==0)
                break;
            else
                size++;
        }

        long[] ret=new long[size+1];size=1;
        int i=1;
        while(i<left.length)
        {
            if(max< height[Math.toIntExact(left[i])])
            ret[size++]= left[i];
            else
                break;
            i++;
        }
        long val= left[0];
        for(;i<left.length;i++)
            val-= taste[Math.toIntExact(left[i])];

        for(i=1;i<right.length;i++)
        {
            if(right[i]==0)
                break;
            else
                ret[size++]= right[i];
        }
        ret[0]= val+ right[0];
        return ret;
    }

    //void update(int p, const S& value) {
    //    for (t[p += n] = value; p >>= 1; ) t[p] = combine(t[p<<1], t[p<<1|1]);
    //}

    void build(long[][] tree, int[] height, int[] taste)
    {
        int n= height.length-1;

        for(int i=0;i<n;i++)
            tree[n+i]= new long[]{taste[i+1], i+1};

        for (int i = n - 1; i > 0; --i)
            tree[i] = merge(tree[i<<1], tree[i<<1|1], height, taste);
    }

    static long[] query(int l, int r, long[][]tree, int[] height, int[] taste) {
        long[] resl=new long[]{0};
        long[] resr= new long[]{0};
        int n= height.length-1;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if((l&1)==1)
                resl = merge(resl, tree[l++], height, taste);
            if((r&1)==1)
                resr = merge(tree[--r], resr, height, taste);
        }
        return merge(resl, resr, height, taste);
    }


    void solve()
    {

    }

    void run() throws Exception
    {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        w = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        w.flush();
        if(!INPUT.isEmpty())tr(System.currentTimeMillis()-s+"ms");
    }


    public static void main(String[] args) throws Exception { new SegmentDRGNDEN().run(); }

    private byte[] inbuf = new byte[1024];
    public int lenbuf = 0, ptrbuf = 0;

    private int readByte()
    {
        if(lenbuf == -1)throw new InputMismatchException();
        if(ptrbuf >= lenbuf){
            ptrbuf = 0;
            try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
            if(lenbuf <= 0)return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
    private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }

    private double nd() { return Double.parseDouble(ns()); }
    private char nc() { return (char)skip(); }

    private String ns()
    {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n)
    {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while(p < n && !(isSpaceChar(b))){
            buf[p++] = (char)b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m)
    {
        char[][] map = new char[n][];
        for(int i = 0;i < n;i++)map[i] = ns(m);
        return map;
    }

    private int[] na(int n)
    {
        int[] a = new int[n];
        for(int i = 1;i < n;i++)a[i] = ni();
        return a;
    }

    private int ni()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private void tr(Object... o) { if(INPUT.length() > 0)System.out.println(Arrays.deepToString(o)); }

    static void right(int[] r_max, int[] height)
    {
        //int temp[]=new int[height.length];
        Stack<Integer> stack=new Stack<>();
        for(int i=height.length-1;i>=0;i--)
        {
            while(stack.size()!=0 && height[stack.peek()]<height[i])
                stack.pop();
            if(stack.size()==0)
                r_max[i]=height.length;
            else
                r_max[i]=stack.peek();
            stack.push(i);
        }
    }
}
