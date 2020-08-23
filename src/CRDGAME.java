import java.util.*;
import java.io.*;

class CRDGAME {
    public static void main(String args[]) throws IOException {
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter pw = new FileWriter("output.txt");

        int t= Integer.parseInt(br.readLine());

        for(int ii=0;ii<t;ii++)
        {
            int n= Integer.parseInt(br.readLine());
            int chef=0, monty=0;

            for(int i=0;i<n;i++)
            {
                StringTokenizer st=new StringTokenizer(br.readLine());
                int val_chef= sum(Integer.parseInt(st.nextToken()));
                int val_monty= sum(Integer.parseInt(st.nextToken()));

                if(val_monty== val_chef)
                {
                    chef++;
                    monty++;
                }
                else if(val_monty> val_chef)
                    monty++;
                else
                    chef++;
            }

            if(monty== chef)
                System.out.println(2+" "+monty);
            else if(monty>chef)
                System.out.println(1+" "+monty);
            else
                System.out.println(0+" "+chef);
        }

    }

    static int sum(int n)
    {
        int ret=0;
        while(n!=0)
        {
            ret+= n%10;
            n/=10;
        }
        return ret;
    }
}