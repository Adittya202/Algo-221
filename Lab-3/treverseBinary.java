import java.io.*;
import java.util.HashMap;

public class treverseBinary {
    public static int preidx=0;
    public static StringBuilder sb= new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] in = new int[N];
        int[] pre = new int[N];
        HashMap <Integer, Integer> inoIdx= new HashMap<>();

        

        String[] parts1 = br.readLine().split(" ");
        String[] parts2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            in[i] = Integer.parseInt(parts1[i]);
            pre[i] = Integer.parseInt(parts2[i]);
        }
        for(int i=0; i<N; i++){
            inoIdx.put(in[i], i);
        }
        po(in, pre, 0, in.length-1, inoIdx);
        System.out.println(sb.toString().trim());
       
    }
    public static void po(int in[], int pre[],int low, int high, HashMap<Integer,Integer> inoIdx){
        if(low>high){
            return;
        }
        int root=pre[preidx++];
        int mid=inoIdx.get(root);
        po(in, pre,low, mid-1,inoIdx);
        po(in, pre, mid+1,high,inoIdx);
        sb.append(root).append(" ");
    }

}
