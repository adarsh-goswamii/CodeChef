import java.util.*;
import java.io.*;

class UNSQUERS {
    InputStream is;
    OutputStream out = new BufferedOutputStream ( System.out );
    String INPUT = "";
    HashMap<Integer, Integer> entryMap;
    int totalSum=0, maxSuffixSum= 1, maxPrefixSum= 2, maxSubarraySum=3;

    void solve() throws IOException
    {
        int n=ni(), q= ni(), s= ni();
        int[] height= new int[n+1];
        for (int i = 1; i <=n; i++)
            height[i]= ni();

        ArrayList<ArrayList<Integer>> arrayLists= new ArrayList<>();
        for (int i = 0; i <=n; i++)
            arrayLists.add(new ArrayList<>());

        graph(arrayLists, height);
//        out.write((arrayLists+"\n").getBytes());

        ArrayList<Integer> flat= new ArrayList<>();
        boolean[] visited= new boolean[n+1];
        for(int i=n;i>=1;i--)
        {
            if(visited[i]) continue;
            DFSREC(arrayLists, visited, flat, i);
        }

//        out.write((flat+"\n"+entryMap+"\n").getBytes());

        int[][] tree= constructTree(flat, flat.size());

        int ans=0;
        for(int i=0;i<q;i++)
        {
            int x= ni(), y= ni();
            int l= (x+ s* ans- 1)% n+ 1;
            int r= (y+ s* ans- 1)% n+ 1;

            if(l> r)
            {
                l= l^r;
                r= l^r;
                l= l^r;
            }

//            out.write((l+" "+r+"\n").getBytes());
            ans= query(tree, entryMap.get(r), entryMap.get(l), flat.size());

            out.write((ans+"\n").getBytes());
        }
    }

    void build(int[][] tree, int start, int end, int index, ArrayList<Integer> arr)
    {
        if (start == end) {
            tree[index][totalSum] = arr.get(start);
            tree[index][maxSuffixSum] = arr.get(start);
            tree[index][maxPrefixSum] = arr.get(start);
            tree[index][maxSubarraySum] = arr.get(start);
            return;
        }

        int mid = (start + end) / 2;
        build(tree, start, mid, 2 * index, arr);
        build(tree, mid + 1, end, 2 * index + 1, arr);

        tree[index] = merge(tree[2 * index], tree[2 * index + 1]);
    }

    int[][] constructTree(ArrayList<Integer> arr, int n)
    {
        // Allocate memory for segment tree
        int x = (int)(Math.ceil(Math.log(n)/Math.log(2))); // Height of the tree

        int max_size = 2 * (int)Math.pow(2, x) - 1;
        int[][] tree = new int[max_size][4];

        build(tree, 0, n - 1, 1, arr);
        return tree;
    }

    int[] queryUtil(int[][] tree, int ss, int se, int qs, int qe, int index)
    {
        if (ss > qe || se < qs)
            return new int[]{0,0,0,0};

        if (ss >= qs && se <= qe) {
            return tree[index];
        }

        int mid = (ss + se) / 2;
        int[] left = queryUtil(tree, ss, mid, qs, qe, 2 * index);
        int[] right = queryUtil(tree, mid + 1, se, qs, qe, 2 * index + 1);

        int[] res = merge(left, right);
        return res;
    }

    int query(int[][] tree, int start, int end, int n)
    {
        int[] res = queryUtil(tree, 0, n - 1, start, end, 1);
        return res[maxSubarraySum];
    }

    int[] merge(int[] leftChild, int[] rightChild)
    {
        int[] parentNode= new int[4];
        parentNode[maxPrefixSum] = Math.max(leftChild[maxPrefixSum], leftChild[totalSum] + rightChild[maxPrefixSum]);

        parentNode[maxSuffixSum] = Math.max(rightChild[maxSuffixSum], rightChild[totalSum] + leftChild[maxSuffixSum]);

        parentNode[totalSum] = leftChild[totalSum] + rightChild[totalSum];

        parentNode[maxSubarraySum] = Math.max(leftChild[maxSubarraySum], Math.max(rightChild[maxSubarraySum], leftChild[maxSuffixSum] + rightChild[maxPrefixSum]));

        return parentNode;
    }

    private void DFSREC(ArrayList<ArrayList<Integer>> arrayLists, boolean[] visited, ArrayList<Integer> flat, int curr)
    {
        entryMap.put(curr, flat.size());
        flat.add(1);
        visited[curr]= true;

        for(int i: arrayLists.get(curr))
            DFSREC(arrayLists, visited, flat, i);

        flat.add(-1);
    }

    private void graph(ArrayList<ArrayList<Integer>> arrayLists, int[] height)
    {
        Stack<Integer> stack= new Stack<>();
        for(int i=1;i< height.length;i++)
        {
            while(!stack.isEmpty() && height[stack.peek()]< height[i])
            {
                int curr= stack.pop();
                arrayLists.get(i).add(curr);
            }

            stack.add(i);
        }
    }

    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
//        out = new PrintWriter(System.out);
        entryMap= new HashMap<>();

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

