import java.io.*;

public class fifth {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lineNM = br.readLine();
        if (lineNM == null) return;
        String[] partsNM = lineNM.split(" ");
        int N = Integer.parseInt(partsNM[0]);
        int M = Integer.parseInt(partsNM[1]);

       
        String[] Us = br.readLine().split(" ");
        String[] Vs = br.readLine().split(" "); 

        
        int[] inDegree = new int[N + 1];
        int[] outDegree = new int[N + 1];

       
        for (int i = 0; i < M; i++) {
            
            if (i >= Us.length || i >= Vs.length || Us[i].isEmpty() || Vs[i].isEmpty()) {
                break; 
            }
            
          
            int u = Integer.parseInt(Us[i]); 
            int v = Integer.parseInt(Vs[i]); 

           
            outDegree[u]++; 
            inDegree[v]++;  
        }

      
        StringBuilder sb = new StringBuilder();
        
       
        for (int i = 1; i <= N; i++) {
            int difference = inDegree[i] - outDegree[i];
            
            sb.append(difference);
            
            
            if (i < N) {
                sb.append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}