import java.util.*;
import java.io.*;

class ADAKING {
    public static void main(String args[]) throws IOException {
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter pw = new FileWriter("output.txt");

        int t= Integer.parseInt(br.readLine());
        for(int ii=0;ii<t;ii++) {
            int k = Integer.parseInt(br.readLine());
            int row = k / 8;
            int col = k % 8;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < 8; j++)
                    System.out.print(". ");
                System.out.println();
            }

            for (int i = 0; i < 8; i++) {
                if (i < col)
                    System.out.print(". ");
                else
                    System.out.print("X ");
            }
            System.out.println();

            for(int i=0;i<8 && row+1<8;i++)
            {
                if (i <= col)
                    System.out.print("X ");
                else
                    System.out.print(". ");
            }
            System.out.println();

            for (int i = row + 2; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                    System.out.print(". ");
                System.out.println();
            }
        }

    }
}
// k/3
// k%3