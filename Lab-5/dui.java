import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class dui {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      
        String lineNM = br.readLine();
        if (lineNM == null) return;
        String[] partsNM = lineNM.split(" ");
        int N = Integer.parseInt(partsNM[0]);
        int M = Integer.parseInt(partsNM[1]);

     
        String[] Us = br.readLine().split(" "); 
        String[] Vs = br.readLine().split(" "); 

     
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        
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


        
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N + 1];
        StringBuilder output = new StringBuilder();
        
        int startNode = 1;


        stack.push(startNode);
        
        while (!stack.isEmpty()) {
            int u = stack.pop(); 

            if (!visited[u]) {
                visited[u] = true; 
                output.append(u).append(" "); 

               
                
                ArrayList<Integer> neighbors = adj[u];
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int v = neighbors.get(i);
                    if (!visited[v]) {
                        stack.push(v); 
                    }
                }
            }
        }

        
        System.out.println(output.toString().trim());
    }
}