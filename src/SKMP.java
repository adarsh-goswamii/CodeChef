import java.util.*;
import java.io.*;
class SKMP
{
    public static void main(String[] args)throws IOException
    {
        //PrintWriter w= new PrintWriter(System.out);
        //InputReader in=new DRGNDEN.InputReader(System.in);
        FileInputStream fis = new FileInputStream("input.txt");
        InputReader in = new InputReader(fis);
        PrintWriter w = new PrintWriter("output.txt");

        int t= Integer.parseInt(in.nextLine());
        for(int ii=0;ii<t;ii++)
        {
            char[] arr= in.nextLine().toCharArray();
            char[] pattern= in.nextLine().toCharArray();
            char[] ans=new char[arr.length];

            int[] map=new int[26];
            for(char c: arr)
                map[c-'a']++;

            for(char c: pattern)
                map[c-'a']--;

            int count=0;
            boolean bool= true;
            for(int i=0;i<26;i++)
            {
                if(map[i]==0)
                    continue;

                char c= (char)(i+'a');
                int freq= map[i];

                if(pattern[0]<=c && bool)
                {
                    int j=0;
                    while(j<pattern.length && pattern[j]==c)
                        ans[count++]= pattern[j++];

                    if(j<pattern.length && pattern[j]<c)
                    {
                        for(;j<pattern.length;j++)
                            ans[count++]= pattern[j];
                        for(;freq>0;freq--)
                            ans[count++]= c;
                    }
                    else
                    {
                        for(;j<freq;j++)
                            ans[count++]= c;
                        for(j=0;j<pattern.length;j++)
                            ans[count++]= pattern[j];
                    }
                    bool= false;
                }
                else
                {
                    for(int j=0;j<freq;j++)
                        ans[count++]= c;
                }
            }
            w.println(new String(ans));
        }
        w.close();
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