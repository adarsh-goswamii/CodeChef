import java.util.*;
import java.io.*;
class PTMSSNG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t= Integer.parseInt(br.readLine());

        for(int ii=0;ii<t;ii++)
        {
            int n= Integer.parseInt(br.readLine());

            int points[][]=new int[4*n-1][2];
            for(int i=0;i<points.length;i++)
            {
                StringTokenizer st=new StringTokenizer(br.readLine());
                points[i][0]= Integer.parseInt(st.nextToken());
                points[i][1]= Integer.parseInt(st.nextToken());
            }

            int ans_x= call(points, 0), ans_y= call(points, 1);
            System.out.println(ans_x+" "+ans_y);

        }
    }

    static int call(int points[][], int cordinate)
    {
        HashMap<Integer, Integer> map=new HashMap<>();
        for(int[] i: points)
        {
            if(map.containsKey(i[cordinate]))
                map.put(i[cordinate], map.get(i[cordinate])+1);
            else
                map.put(i[cordinate], 1);
        }

        for(int i: map.keySet())
            if(map.get(i)%2!=0)
                return i;

        return -1;
    }
}