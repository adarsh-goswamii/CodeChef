import java.util.*;
import java.io.*;

class DOCCHEF {
    public static void main(String[] args) throws IOException {
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //FileWriter pw = new FileWriter("output.txt");

        int t= Integer.parseInt(br.readLine());
        for(int ii=0;ii<t;ii++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n= Integer.parseInt(st.nextToken());
            long x= Long.parseLong(st.nextToken());

            ArrayList<Integer> country=new ArrayList<>();
            StringTokenizer st2=new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++)
                country.add(Integer.parseInt(st2.nextToken()));

            Collections.sort(country);

            long day=0;
            while(true)
            {
                int small= BinarySearch(country, x);
                //System.out.println(small);
                // ALL THE CASES WHEN OUR DAY IS NOT WASTED
                if(small== country.size()-1) // CHECKED
                    break;
                else if(small!=-1 && (country.get(small)*2>= country.get(small+1) || country.get(small)==x))
                { // CHECKED
                    x= country.get(small)*2;
                    country.remove(small);
                }
                else
                {
                    if(small==-1)
                        x*=2;
                    else
                    {
                        int val= call(x, country.get(small+1));
                        int val2= call(country.get(small), country.get(small+1));
                        x= country.get(small+1)*2;
                        if(val2<=val)
                            country.remove(small);
                        day+= Math.min(val, val2)-1;
                    }
                }
                day++;
            }
            System.out.println(day+ country.size());
        }
    }
    static int BinarySearch(ArrayList<Integer> arr, long x)
    {
        int start=0, last= arr.size()-1;
        while(start<= last)
        {
            int mid= start+ (last- start)/2;
            if(arr.get(mid)== x)
                return mid;
            else if(arr.get(mid)>x)
                last= mid-1;
            else
                start= mid+1;
        }
        return last;
    }

    static int call(long x, int next)
    {
        int ret=0;
        while(x<next)
        {
            x*=2;
            ret++;
        }
        return ret;
    }
}