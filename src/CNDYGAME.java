import java.util.*;
import java.io.*;

class CNDYGAME {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    int MOD= 1000000007;

    void solve() throws IOException {
        int t = ni();
        for (int ii = 0; ii < t; ii++) {
            int n= ni();
            int[] candies= new int[n];
            for (int i = 0; i < n; i++)
                candies[i]= ni();

            int q= ni();
            if(candies[0]==1)
            {
                for (int i = 0; i < q; i++)
                {
                    long r= nl();
                    if(r<=n)
                    {
                        out.println("1");
                        continue;
                    }
                    long ans = r / n ;
                    r = r- ((r/n) *n);
                    if (r != 0 && r!=1)
                        ans++;
                    out.println(ans% MOD);
                }
                continue;
            }

            long[] presum= new long[n];
            long total=0;
            for (int i = 0; i < n; i++)
            {
                if(i== n-1)
                {
//                    out.println(total);
                    if(candies[i]%2!=0)
                        total= (total+ candies[i])% MOD;
                    else
                    {
                        total= (total+ candies[i]- 1)% MOD;
                        presum[i]++;
                    }
                    presum[i]+= total;
                    presum[i]= presum[i]% MOD;
                }
                else if(candies[i]== 1)
                    presum[i]= (presum[i]+ total)% MOD;
                else if(candies[i+1]== 1 && i!= n-2)
                {
                    if(candies[i]%2!=0)
                        total= (total+ candies[i])% MOD;
                    else
                    {
                        total= (total+ candies[i]- 1)% MOD;
                        presum[i]++;
                        presum[i+1]= 2;
                    }
                    presum[i]+= total;
                    presum[i]= presum[i]% MOD;
                }
                else
                {
                    if(candies[i]%2==0)
                        total= (total+ candies[i])% MOD;
                    else
                    {
                        total= (total+ candies[i]- 1)% MOD;
                        presum[i]++;
                    }
                    presum[i]+= total;
                    presum[i]= presum[i]% MOD;
                }
            }

//            for (int i = 0; i < n; i++)
//                System.out.print(presum[i]+" ");
//            System.out.println();

            for (int i = 0; i < q; i++)
            {
                long r= nl();
                r= r-1;
                long ans= (((r/n)%MOD) * total)% MOD;
                r= r%n;
                ans= (ans+ presum[(int)r])% MOD;
                out.println(ans);
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
        new CNDYGAME().run();
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

