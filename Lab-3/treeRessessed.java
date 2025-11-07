import java.io.*;
import java.util.*;

public class treeRessessed{
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] inorder = new int[N];
        int[] postorder = new int[N];
        HashMap<Integer, Integer> inIndex = new HashMap<>(); 

        String[] inParts = br.readLine().split(" ");
        String[] postParts = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(inParts[i]);
            postorder[i] = Integer.parseInt(postParts[i]);
            inIndex.put(inorder[i], i);
        }

        buildPreorder(postorder, 0, N - 1, inorder, 0, N - 1, inIndex);
        System.out.println(sb.toString().trim());
    }

    public static void buildPreorder(int[] post, int postStart, int postEnd,
                                     int[] in, int inStart, int inEnd,
                                     HashMap<Integer, Integer> inIndex) {
       
        if (postStart > postEnd || inStart > inEnd) return;

        
        int root = post[postEnd];
        sb.append(root).append(" "); 

        int mid= inIndex.get(root);  
        int leftSize = mid- inStart; 

        
        buildPreorder(post, postStart, postStart + leftSize - 1,
                      in, inStart, mid- 1, inIndex);

        
        buildPreorder(post, postStart + leftSize, postEnd - 1,
                      in, mid+ 1, inEnd, inIndex);
    }
}
