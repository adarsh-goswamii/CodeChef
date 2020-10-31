import java.util.*;
class POSAND
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);

        int t= sc.nextInt();
        for(int ii=0;ii<t;ii++)
        {
            int n= sc.nextInt();
            StringBuilder br= new StringBuilder("");

            if(n==1)
                System.out.println("1");
            else if((n&(n-1))==0)
                System.out.println("-1");
            else if(n==3)
                System.out.println("1 3 2");
            else if(n==5)
                System.out.println("2 3 1 5 4");
            else
            {
                br.append("2 3 1 5 4");
                for(int i=6;i<=n;i++)
                {
                    if((i&(i-1))==0)
                    {
                        br.append(" "+(i+1));
                        br.append(" "+i);
                        i++;
                    }
                    else
                        br.append(" "+i);
                }

                System.out.println(br.toString());
            }
        }
    }
}