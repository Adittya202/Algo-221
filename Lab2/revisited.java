import java.io.*;
public class revisited {
    public static void main(String[]args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String[] parts=br.readLine().split(" ");
        int N=Integer.parseInt(parts[0]);
        int M=Integer.parseInt(parts[1]);
        int K=Integer.parseInt(parts[2]);

        String[] input1=br.readLine().split(" ");
         String[] input2=br.readLine().split(" ");
         int[]A=new int [N+1];
         int[]B =new int[M+1];
         for(int i=1; i<=N;i++){
            A[i]=Integer.parseInt(input1[i-1]);
         }
         for(int i=1;i<=M;i++){
            B[i]=Integer.parseInt(input2[i-1]);
         }
         int temp1=1;
         int temp2=M;
         int best_i=temp1;
         int best_j=temp2;
         
         int sum=A[temp1]+B[temp2];
         int min1=Math.abs(sum-K);
         if(sum>K){
            temp2--;
         }else{
            temp1++;
         }
         
         
         while(temp1<=N && temp2>0){
         sum=A[temp1]+B[temp2];
         int min2=Math.abs(sum-K);
         if(min2<min1){
            min1=min2;
            best_i=temp1;
            best_j=temp2;
         }
         if(sum>K){
            temp2--;
         }else{
            temp1++;
         }
        }
        System.out.print(best_i+" "+best_j);
    }
}