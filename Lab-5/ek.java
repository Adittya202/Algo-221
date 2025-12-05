import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Collections; 

public class ek{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        String lineNM = br.readLine();
        if (lineNM == null || lineNM.trim().isEmpty()) return;
        String[] partsNM = lineNM.split(" ");
        int N = Integer.parseInt(partsNM[0]);
        int M = Integer.parseInt(partsNM[1]);

        
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

       
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            if (line == null) break;
            String[] parts = line.split(" ");
            
          
            if (parts.length < 2) continue; 
            
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

         
            adj[u].add(v);
            adj[v].add(u);
        }

       
        Queue<Integer> queue = new LinkedList<>();
        
        
        boolean[] visited = new boolean[N + 1];
        
        StringBuilder output = new StringBuilder();
        
        
        int startNode = 1;

    
        visited[startNode] = true;
        queue.add(startNode);
        
        
        while (!queue.isEmpty()) {
            int u = queue.poll(); 
            output.append(u).append(" "); 

        
            ArrayList<Integer> neighbors = adj[u];
            Collections.sort(neighbors); 

            
            for (int v : neighbors) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);      
                }
            }
        }

       
        System.out.println(output.toString().trim());
    }
}