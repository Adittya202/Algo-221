import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EasyTreeQueries {

    private static int N;
    private static List<Integer>[] adj;
    private static int[] subtreeSize;

    private static void dfs(int u, int p) {
        subtreeSize[u] = 1;
        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u);
                subtreeSize[u] += subtreeSize[v];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String lineNR = br.readLine();
        if (lineNR == null) return;
        String[] partsNR = lineNR.split(" ");
        N = Integer.parseInt(partsNR[0]);
        int R = Integer.parseInt(partsNR[1]);

        @SuppressWarnings("unchecked")
        List<Integer>[] tempAdj = new ArrayList[N + 1];
        adj = tempAdj;
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String lineUV = br.readLine();
            if (lineUV == null) break;
            String[] partsUV = lineUV.split(" ");
            int u = Integer.parseInt(partsUV[0]);
            int v = Integer.parseInt(partsUV[1]);
            
            adj[u].add(v);
            adj[v].add(u);
        }

        subtreeSize = new int[N + 1];
        dfs(R, 0);

        String lineQ = br.readLine();
        if (lineQ == null) return;
        int Q = Integer.parseInt(lineQ.trim());
        
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            String lineX = br.readLine();
            if (lineX == null) break;
            int X = Integer.parseInt(lineX.trim());
            
            sb.append(subtreeSize[X]).append("\n");
        }

        System.out.print(sb.toString());
    }
}