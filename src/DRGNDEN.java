import java.util.*;
import java.io.*;
class DRGNDEN
{
    static long[][] tree;
    static long[][] tree2;
    static int[] height;
    static int[] height2;
    static int[] taste;
    static int[] taste2;

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

    static long[] build(int start, int last, int index, long[][] tree, int[] height, int[] taste)
    {
        if(start== last)
        {
            tree[index]= new long[]{(long)taste[start], (long)start};
            return tree[index];
        }
        else
        {
            int mid= start+ (last- start)/2;
            long[] left= build(start, mid, 2*index+1, tree, height, taste);
            long[] right= build(mid+1, last, 2*index+2, tree, height, taste);
            tree[index]= merge(left, right, height, taste);
            return tree[index];
        }
    }

    static long[] range(int q_start, int q_last, int start, int last, int index, long[][] tree, int[] height, int[] taste)
    {
        if(q_start>last || q_last< start)
            return new long[]{0};
        else if(start>= q_start && last<= q_last)
            return  tree[index];
        else
        {
            int mid= start+ (last- start)/2;
            long[] left= range(q_start, q_last, start, mid, 2*index+1, tree, height, taste);
            long[] right= range(q_start, q_last, mid+1, last, 2*index+2, tree, height, taste);
            return merge(left, right, height, taste);
        }
    }

    static void update(int start, int last, int index, int s_index, int val, long[][]tree, int[] height, int[] taste)
    {

        if(index>= start && index<= last)
        {
            if(start== last)
                tree[s_index]=new long[]{taste[start], start};
            else
            {
                int mid= start+ (last- start)/2;
                update(start, mid, index, 2*s_index+1, val, tree, height, taste);
                update(mid+1, last, index, 2*s_index+2, val, tree, height, taste);
                tree[s_index]= merge(tree[2*s_index+1], tree[2*s_index+2], height, taste);
            }
        }
    }


    public static void main(String args[]) throws IOException {
        PrintWriter w= new PrintWriter(System.out);
        InputReader in=new InputReader(System.in);
        //FileInputStream fis = new FileInputStream("input.txt");
        //InputReader in = new InputReader(fis);
        //PrintWriter w = new PrintWriter("output.txt");

        int n= in.nextInt()+1, q=in.nextInt();
        height=new int[n]; height2= new int[n];
        taste=new int[n]; taste2=new int[n];

        int[] r_max=new int[n];
        int[] r_max2=new int[n];

        for(int i=1;i<n;i++)
            height[i]= in.nextInt();

        int c=1;
        for(int i=n-1;i>=1;i--)
            height2[c++]= height[i];

        for(int i=1;i<n;i++)
            taste[i]= in.nextInt();

        c=1;
        for(int i=n-1;i>=1;i--)
            taste2[c++]= taste[i];

        tree=new long[4*n][];
        tree2= new long[4*n][];
        //System.out.println(tree.size());

        build(1, n-1, 0, tree, height, taste);
        build(1, n-1, 0, tree2, height2, taste2);
        right(r_max, height);
        right(r_max2, height2);

        //for(int i=0;i<tree.length;i++)
        //{
        //    w.print(i+" ");
        //    for(int j=0;j<tree[i].length;j++)
        //        w.print(tree[i][j]+" ");
        //    w.println();
        //    w.flush();
        //}

        for(int i=0;i<q;i++)
        {
            int query= in.nextInt();
            int start= in.nextInt();
            int last= in.nextInt();

            if(query==1)
            {
                taste[start]= last;
                update(1, n-1, start, 0, last, tree, height, taste);
                start= n- start;
                taste2[start]= last;
                update(1, n-1, start, 0, last, tree2, height2, taste2);
                continue;
            }

            if(start<=last)
            {
                if (r_max[start]<=last)
                    w.println("-1");
                else
                    w.println(range(start, last, 1, n-1, 0, tree, height, taste)[0]);
            }
            else
            {
                start= n- start;
                last= n- last;
                //w.println(start+" "+last);
                if(r_max2[start]<=last)
                    w.println("-1");
                else
                    w.println(range(start, last, 1, n-1, 0, tree2, height2, taste2)[0]);
            }
        }
        w.close();
    }

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