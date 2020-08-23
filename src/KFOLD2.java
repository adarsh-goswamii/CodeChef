import java.util.*;
import java.io.*;
class KFOLD2
{
    public static void main(String[] args)throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);

        int t= Integer.parseInt(br.readLine());

        for(int ii=0;ii<t;ii++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n= Integer.parseInt(st.nextToken());
            int k= Integer.parseInt(st.nextToken());
            int fold= n/k;

            char[] arr= br.readLine().toCharArray();
            int one=0, zero=0;

            for(char c: arr)
                if(c=='1')
                    one++;
                else
                    zero++;

            int val= one/fold + zero/fold;
            if(val!=k)
                out.println("IMPOSSIBLE");
            else
            {
                StringBuilder front= new StringBuilder("");
                for(int i=0;i<zero/fold;i++)
                    front.append('0');

                for(int i=0;i<one/fold;i++)
                    front.append('1');

                String[] str={front.toString(), front.reverse().toString()};
                for(int i=0;i<fold;i++)
                    out.print(str[i%2]);

                out.println();
            }
        }
        out.close();
    }
}