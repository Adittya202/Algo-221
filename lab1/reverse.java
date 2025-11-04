import java.io.*;
import java.util.StringTokenizer;


public class reverse {
   public static void main (String []args) throws IOException{
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter n = new PrintWriter(System.out);

        int N = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());

        int [] numbers = new int[N];

        for(int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int MaxOps = N*N;
        int [] first = new int [MaxOps];
        int [] sec = new int [MaxOps];
        int count = 0;

        for (int i = 0; i < N; i++){
            boolean changed = false;
            for(int j = 0; j+2 < N ;j++){
                if(numbers[j]> numbers[j+2]){
                    int temp = numbers[j];
                    numbers[j]= numbers[j+2];
                    numbers[j+2]= temp;

                

                if (count<MaxOps){
                    first[count]= j+1;
                    sec[count] = j+3;
                    count++;
                }

                changed = true;
            }
         }

            if(!changed)
            break;
        }


            boolean arrange = true;
            for(int i = 0 ; i < N-1;i++){
                if(numbers[i]>numbers[i+1]){
                    arrange = false;
                    break;
                }
            }

            if(arrange){
                n.println("YES");
                n.println(count);

                for(int i = 0;i< count;i++){
                    n.println(first[i] + " "+ sec[i]);
                }
            }



            n.close();

   } 
}