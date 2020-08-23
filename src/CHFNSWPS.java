import java.util.*;
import java.io.*;

class CHFNSWPS {
    public static void main(String args[]) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t= Integer.parseInt(br.readLine());
        test: for(int ii=0;ii<t;ii++)
        {
            int n= Integer.parseInt(br.readLine());

            HashMap<Integer, Integer> map_A=new HashMap<>();
            HashMap<Integer, Integer> map_B=new HashMap<>();
            int secret_agent= Integer.MAX_VALUE;
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++)
            {
                int val= Integer.parseInt(st.nextToken());
                secret_agent= Math.min(secret_agent, val);
                if(map_A.containsKey(val))
                    map_A.put(val, map_A.get(val)+1);
                else
                    map_A.put(val, 1);
            }

            StringTokenizer st2=new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++)
            {
                int val= Integer.parseInt(st2.nextToken());
                secret_agent= Math.min(secret_agent, val);
                if(map_B.containsKey(val))
                    map_B.put(val, map_B.get(val)+1);
                else
                    map_B.put(val, 1);
            }

            ArrayList<Integer> seq_A=new ArrayList<>();
            ArrayList<Integer> seq_B=new ArrayList<>();
            //long cost=0;
            for(int i: map_A.keySet())
            {
                int diff=0;

                if(map_B.containsKey(i))
                    diff= map_A.get(i)>map_B.get(i)?(map_A.get(i)-map_B.get(i)): 0;
                else
                    diff= map_A.get(i);

                if(diff%2==1)
                {
                    System.out.println("-1");
                    continue test;
                }
                else
                {
                    for(int j=0;j<diff/2;j++)
                        seq_A.add(i);
                }
            }

            for(int i: map_B.keySet())
            {
                int diff=0;

                if(map_A.containsKey(i))
                    diff= map_A.get(i)<map_B.get(i)? (map_B.get(i)-map_A.get(i)): 0;
                else
                    diff= map_B.get(i);

                if(diff%2==1)
                {
                    System.out.println("-1");
                    continue test;
                }
                else
                {
                    for(int j=0;j<diff/2;j++)
                        seq_B.add(i);
                }
            }

            if(seq_A.size()==0 )
            {
                System.out.println("0");
                continue test;
            }

            Collections.sort(seq_A);
            Collections.sort(seq_B);
            secret_agent*=2;
            long cost=0;

            int len= seq_A.size();
            // Naive implementation starts  -----------------------------------------------------------//
            for(int i=0;i<len;i++)
            {
                int min= Math.min(seq_A.get(0), seq_B.get(0));
                if(min== seq_A.get(0)) {
                    if (min <= secret_agent) {
                        cost += min;
                        seq_A.remove(0);
                        seq_B.remove(seq_B.size() - 1);
                    } else {
                        cost += secret_agent * seq_A.size();
                        break;
                    }
                }
                else
                {
                    if (min <= secret_agent) {
                        cost += min;
                        seq_B.remove(0);
                        seq_A.remove(seq_A.size() - 1);
                    }
                    else {
                        cost += secret_agent * seq_A.size();
                        break;
                    }
                }
                //System.out.println(seq_A+"\n"+seq_B);
            }
            System.out.println(cost);
        }
    }
}