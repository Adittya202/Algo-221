import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class choy {

    private static List<Integer>[] adj;
    private static int N;
    private static int[] status; // 0: White, 1: Gray, 2: Black
    private static boolean cycleFound;

    private static void dfs(int u) {
        status[u] = 1;

        for (int v : adj[u]) {
            if (status[v] == 1) {
                cycleFound = true;
                return;
            }
            if (status[v] == 0) {
                dfs(v);
                if (cycleFound) return;
            }
        }
        status[u] = 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lineNM = br.readLine();
        if (lineNM == null) return;
        String[] partsNM = lineNM.split(" ");
        N = Integer.parseInt(partsNM[0]);
        int M = Integer.parseInt(partsNM[1]);

        @SuppressWarnings("unchecked")
        List<Integer>[] tempAdj = new ArrayList[N + 1];
        adj = tempAdj;
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String lineUV = br.readLine();
            if (lineUV == null) break;
            String[] partsUV = lineUV.split(" ");
            
            if (partsUV.length < 2) continue;
            
            int u = Integer.parseInt(partsUV[0]);
            int v = Integer.parseInt(partsUV[1]);

            adj[u].add(v);
        }

        status = new int[N + 1];
        cycleFound = false;

        for (int i = 1; i <= N; i++) {
            if (status[i] == 0) {
                dfs(i);
            }
            if (cycleFound) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}