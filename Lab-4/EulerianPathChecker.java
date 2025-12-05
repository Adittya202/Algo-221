import java.io.*;

public class EulerianPathChecker {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        String lineNM = br.readLine();
        if (lineNM == null || lineNM.trim().isEmpty()) return;
        String[] partsNM = lineNM.split(" ");
        int N = Integer.parseInt(partsNM[0]);
        int M = Integer.parseInt(partsNM[1]);

        
        String[] Us = br.readLine().split(" ");
        String[] Vs = br.readLine().split(" ");

        
        
        int[] degree = new int[N + 1];

        
        for (int i = 0; i < M; i++) {
            
            if (i >= Us.length || i >= Vs.length || Us[i].isEmpty() || Vs[i].isEmpty()) {
                break; 
            }
            
            
            int u = Integer.parseInt(Us[i]);
            int v = Integer.parseInt(Vs[i]);

            
            degree[u]++;
            degree[v]++;
        }

       
        int oddDegreeCount = 0;
        
      
        for (int i = 1; i <= N; i++) {
            if (degree[i] % 2 != 0) { 
                oddDegreeCount++;
            }
        }
        

        if (oddDegreeCount == 0 || oddDegreeCount == 2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}