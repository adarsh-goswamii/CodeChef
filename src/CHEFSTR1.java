import java.util.*;
import java.io.*;

class CHEFSTR1 {
    public static void main(String args[]) throws IOException {
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter pw = new FileWriter("output.txt");

        int t= Integer.parseInt(br.readLine());
        for(int ii=0;ii<t;ii++)
        {
            int n= Integer.parseInt(br.readLine());
            long ans=0;
            StringTokenizer st=new StringTokenizer(br.readLine());
            int prev= Integer.parseInt(st.nextToken());

            for(int i=1;i<n;i++)
            {
                int val= Integer.parseInt(st.nextToken());
                ans+= Math.abs(prev-val)-1;
                prev= val;
                pw.write(ans+" ");
            }
            pw.write("\n"+ans+"\n");

        }
        pw.close();
    }
}