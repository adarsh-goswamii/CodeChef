import java.util.*;
import java.io.*;

class EQDIV {
    InputReader in;
    PrintWriter out;

    void kEqualsOne(long n)
    {
        int i=0;
        if(n%2!=0)
        {
            if((n-1)%4==0)
                out.print("1\n"+(i++));
            else
                out.print("0\n"+(i++));
        }
        else
        {
            if(n%4==0)
                out.println("0");
            else
                out.println("1");
            n--;
        }

        boolean bool= false;
        for(;i<=n/2;i++, bool= !bool)
        {
            if(bool)
                out.print("10");
            else
                out.print("01");
        }
        out.println();
    }

    void kEqualsTwo(long n)
    {
        if(n==1)
        {
            out.println("1\n0");
            return;
        }

        if(n==2)
        {
            out.println("3\n01");
            return;
        }

        if(n==3)
        {
            out.println("4\n001");
            return;
        }

        if(n==4)
        {
            out.println("2\n0001");
            return;
        }

        if(n==5)
        {
            out.println("3\n01001");
            return;
        }
        int[] zero={0,1,1,0,1,0,0,1};
        int[] twoeven={0,1,0,0,1,0,0,1,0,1};
        int[] threeeven={1,0,1,1,1,0,0,0,1,0,1};

        int i=0;

        if(n%4==1)
        {   out.println("1");
            if ((n / 4) % 2 == 1) {
                out.print("0001000010011");
                i = 13;
            }
            else
            {
                out.print("0");
                i++;
            }
        }
        else if(n%4==2)
        {
            out.println("1");
            if((n/4)%2==1)
            {
                out.print("01");
                i=2;
            }
            else
            {
                for(int c=0;c<twoeven.length;i++, c++)
                    out.print(twoeven[c]);
            }
        }
        else if(n%4==3)
        {
            out.println("0");
            if((n/4)%2==1)
            {
                out.print("001");
                i=3;
            }
            else
            {
                for(int c=0;c<threeeven.length;i++, c++)
                    out.print(threeeven[c]);
            }
        }
        else
        {
            out.println("0");
            if((n/4)%2==1)
            {
                out.print("000000001101");
                i=12;
            }
        }

        int c=0;
        for(;i<n;i++, c++)
            out.print(zero[c%8]);
        out.println();
    }

    void kEqualsThree(long n)
    {
        long[] ans={0, 1, 7, 18, 28, 25, 7, 26, 4, 5, 1, 12, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0};
        String[] pattern={"", "1" , "01" , "001" , "0001" , "00001" , "010001" , "0001110" , "00110110" , "011010110" , "1000000101" , "00001101001" , "110100011001" , "0000010000011" , "01011001101001" , "110000101000011" , "1001011001101001" , "11101001000001101" , "010011010101011010" , "0011001001011000011" , "10100000001000000111" , "011000000001010010011" , "0000000000000001000111" , "01110000010000001000111" , "010100001001000001000111" , "0000000001001000000100111" , "01001100101000000000001111" , "100010010000000100000001111" , "1101010001001001000000001111" , "11111101101000000001000001111" , "110011011010000000000010001111", "1100001010000111001011001101001", "10010110011010011001011001101001"};

        if(n<=32)
        {
            out.println(ans[(int)n]);
            out.println(pattern[(int)n]);
        }
        else
        {
            long val= 16+ n%16;
            out.println(ans[(int) val]);
            out.print(pattern[(int )val]);

            val= n-val;
            for(int i=0;i<val/16;i++)
                out.print("1001011001101001");
            out.println();
        }
    }

    void kEqualsFour(long n)
    {
        long[] ans={0, 1 ,15 ,64 ,158 ,271 ,317 ,126 ,34 ,253 ,13 ,92 ,30 ,47 ,31 ,2 ,0 ,1 ,13 ,0 ,0 ,9 ,1 ,0 ,0 ,1 ,5 ,0 ,0 ,5 ,1 ,0 ,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0};
        String[] pattern={"","1" , "01" , "001" , "0001" , "00001" , "000001" , "0000001" , "00101110" , "000001110" , "0101001001" , "00000101001" , "001101010110" , "0010110101001" , "00000001111010" , "011110001010110" , "1111000111110001" , "01111000000010101" , "000000110100001110" , "0111101101001000011" , "11110101001000100011" , "000000000100100100011" , "1010110000100000001011" , "11110111011001000010011" , "111101100001001000001011" , "0111001010100010000000111" , "00010100000100010000110101" , "110000111111100001000000111" , "1110100000111010000100000111" , "00010000000100110001000101110" , "011101101110101000000000100111" , "0010011110001000100000000010111", "01101001100101100110100110010110","001101001100101100110100110010110", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50","51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "001101001100101100110100110010110001101001100101100110100110010110" };
        pattern[33]= "001010100011101000100000000001111";
        pattern[34]= "0101010001000000000100010000010111";
        pattern[35]= "10111000110100101000000010000001111";
        pattern[36]= "011010101011100010000000000100001111";
        pattern[38]= "11010101111010010111011111111101110000";
        pattern[39]= "100001010000000001111111111111111010000";
        pattern[40]= "1011010001000001111111110111111111010000";
        pattern[41]= "11000101010110011111111111011111111100000";
        pattern[42]= "111111101111111010111110101111111111100000";
        pattern[43]= "1101010001000011101101111111110111111100000";
        pattern[44]= "10010101010101100111111101111111011111100000";
        pattern[45]= "101111101111101111111010111111111110111100000";
        pattern[46]= "1110010100010101010110111111111111111101100000";
        pattern[47]= "10000110000001011111011101111111111111110100000";
        pattern[48]= "100001010000000100111111110111111111111111000000";
        pattern[49]= "1100010001010110001101111111110111111111111000000";
        pattern[50]= "10111010111010111111011110111111101111111111000000";
        pattern[52]= "1100001101000001010111110111111111111111011111000000";
        pattern[51]= "100000000110010011010111011111111111011111111000000";
        pattern[53]= "11111000111011101111111011111111111111111111011000000";
        pattern[54]= "110101011101000101000111111111111111111111111101000000";
        pattern[55]= "1001000000010111010101111101111111111111111111110000000";
        pattern[56]= "11010011000001010101011101111101111111111111111110000000";
        pattern[57]= "110001011101010111010111110111111111011111111111110000000";
        pattern[58]= "1010111101100111110111111111111111111111111011111110000000";
        pattern[59]= "10010100000100000101011110111111111111111111011111110000000";
        pattern[60]= "100001010001000101010011111111011111111111111101111110000000";
        pattern[61]= "1111111011111001111101111111111111111111111111111111010000000";
        pattern[62]= "10000001000001000011010111111111011111111111111111110110000000";
        pattern[63]= "100000001100010000000101010111111111111111111111111111100000000";


        for(int i=1;i<=64;i++)
            out.println(i+" = "+ans[i]);

        if(n<=64)
            out.println(ans[(int )n]+"\n"+pattern[(int ) n]);
        else
        {
            long val= 32+ n%32;
            out.println(ans[(int)val]);
            out.print(pattern[(int ) val]);
            val= n- val;

            for(int i=0;i<val/32;i++)
                out.print(pattern[32]);
            out.println();
        }
    }

    void solve()
    {
        int k= in.nextInt();
        int t= in.nextInt();
        for(int ii=0;ii<t;ii++)
        {
            long n= in.nextLong();

            if(k==1)
                kEqualsOne(n);

            if(k==2)
                kEqualsTwo(n);

            if(k==3)
                kEqualsThree(n);

            if(k==4)
                kEqualsFour(n);
        }
    }

    void run() throws Exception {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new EQDIV().run();
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
