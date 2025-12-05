import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class tin {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        String lineNMSD = br.readLine();
        if (lineNMSD == null) return;
        String[] partsNMSD = lineNMSD.split(" ");
        int N = Integer.parseInt(partsNMSD[0]);
        int S = Integer.parseInt(partsNMSD[2]); 
        int D = Integer.parseInt(partsNMSD[3]); 
        
      
        String[] Us = br.readLine().split(" ");
        String[] Vs = br.readLine().split(" ");
        int M = Us.length; 

       
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            
            if (i >= Us.length || i >= Vs.length || Us[i].isEmpty() || Vs[i].isEmpty()) break; 
            
            int u = Integer.parseInt(Us[i]);
            int v = Integer.parseInt(Vs[i]);

          
            adj[u].add(v);
            adj[v].add(u);
        }
        
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }

     
        
        Queue<Integer> queue = new LinkedList<>();
      
        int[] parent = new int[N + 1]; 
       
        int[] dist = new int[N + 1]; 
        
        for (int i = 1; i <= N; i++) {
            dist[i] = -1;
        }

        dist[S] = 0;
        queue.add(S);

        while (!queue.isEmpty()) {
            int u = queue.poll(); 
            
            if (u == D) {
                
                break;
            }

            
            for (int v : adj[u]) {
                if (dist[v] == -1) { 
                    dist[v] = dist[u] + 1;
                    parent[v] = u;         
                    queue.add(v);
                }
            }
        }

     
        
        if (dist[D] == -1) {
          
            System.out.println("-1");
        } else {
        
            System.out.println(dist[D]);
            
           
            List<Integer> path = new ArrayList<>();
            int curr = D;
            
       
            while (curr != 0) {
                path.add(curr);
                if (curr == S) break;
                curr = parent[curr];
            }
            
        
            Collections.reverse(path); 
            
            StringBuilder pathOutput = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                pathOutput.append(path.get(i));
                if (i < path.size() - 1) {
                    pathOutput.append(" ");
                }
            }
            
            System.out.println(pathOutput.toString());
        }
    }
}