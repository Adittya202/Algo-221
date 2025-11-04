
// import java. util.*;
import java.io.*;

public class merge {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] parts1 = br.readLine().split(" ");
        int M = Integer.parseInt(br.readLine());
        String[] parts2 = br.readLine().split(" ");
        int[] arr1= new int[N+1];
        int[] arr2= new int[M+1];
        int[] f_array= new int[N+M+1];

        for(int i=1; i<=N; i++){
            arr1[i]= Integer.parseInt(parts1[i-1]);
        }
        for(int i=1;i<=M;i++){
            arr2[i]= Integer.parseInt(parts2[i-1]);
        }

        int left=1;
        int right=1;
        for(int i=1;i<=M+N;i++){
             if(right==(M+1)){
            f_array[i]=arr1[left];
            left++;
        }else if(left==(N+1)){
             f_array[i]=arr2[right];
            right++;
        }
        else if(  left<=N && arr1[left]<arr2[right]){
            f_array[i]=arr1[left];
            left++;
        }else if(right<=M && arr2[right]<=arr1[left]){
            f_array[i]=arr2[right];
            right++;
        }
        }
        StringBuilder sb = new StringBuilder();
       for(int i=1;i<=M+N;i++){
        sb.append(f_array[i]).append(' ');
       }
       System.out.print(sb.toString());
    }
}//resubmit