import java.util.*;
import java.io.*;
class POLYREL
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

            for(int i=0;i<n;i++)
                br.readLine();

            int ans=0;
            while(n>=3)
            {
                ans+= n;
                n= n/2;
            }
            out.println(ans);
        }
        out.close();
    }
}