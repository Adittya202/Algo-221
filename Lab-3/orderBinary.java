import java.io.*;

public class orderBinary {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int N = Integer.parseInt(br.readLine());
       long[] A = new long[N];
        
        String[] elems = br.readLine().split(" ");
        for(int i=0; i<N;i++){
            A[i]=Long.parseLong(elems[i]);
        }
        divide(A,0,A.length-1);

    }
    public static void divide(long A[], int low, int high){
        
        if(low>high){
            return;
        }
        int mid=(low+high)/2;
          System.out.print(A[mid]+" ");
        
        divide(A, low, mid-1);
        divide(A, mid+1, high);
        
    }
}