import java.util.*;
class CHEFEZQ
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);

        int t= sc.nextInt();
        for(int ii=0;ii<t;ii++)
        {
            int n= sc.nextInt(), k= sc.nextInt();

            long query=0, ans=-1;
            for(int i=0;i<n;i++)
            {
                query+= sc.nextInt();

                if(query<k && ans==-1)
                    ans= i;

                query-= k;
            }

            if(ans== -1)
                ans=(query/k +n);

            System.out.println(ans+1);
        }
    }
}