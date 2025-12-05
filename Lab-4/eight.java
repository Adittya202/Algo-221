import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class eight {

    
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        String lineNQ = br.readLine();
        if (lineNQ == null) return;
        String[] partsNQ = lineNQ.split(" ");
        int N = Integer.parseInt(partsNQ[0]);
        int Q = Integer.parseInt(partsNQ[1]);

       
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new ArrayList[N + 1];

        
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        
        for (int i = 1; i <= N; i++) {
         
            for (int j = i + 1; j <= N; j++) {
                
                if (gcd(i, j) == 1) {
                 
                    adjList[i].add(j);
                    
                    adjList[j].add(i); 
                }
            }
        }

     
        StringBuilder sb = new StringBuilder();
        
        for (int q = 0; q < Q; q++) {
            String lineQK = br.readLine();
            if (lineQK == null) break;
            String[] partsQK = lineQK.split(" ");
            
      
            if (partsQK.length < 2) continue; 
            
            int X = Integer.parseInt(partsQK[0]); 
            int K = Integer.parseInt(partsQK[1]); 

            List<Integer> neighbors = adjList[X];
            
     
            if (neighbors.size() < K) {
                sb.append("-1").append("\n");
            } else {
              
                sb.append(neighbors.get(K - 1)).append("\n");
            }
        }
        
        System.out.print(sb.toString());
    }
}