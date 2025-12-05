import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class caar {

    private static void runBFS(int start, int N, List<Integer>[] adj, int[] parent, int[] dist) {
        for (int i = 1; i <= N; i++) {
            parent[i] = 0;
            dist[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();

        dist[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                    queue.add(v);
                }
            }
        }
    }

    private static List<Integer> reconstructPath(int start, int target, int[] parent, int[] dist) {
        if (dist[target] == -1) {
            return null;
        }

        LinkedList<Integer> path = new LinkedList<>();
        int curr = target;

        while (curr != 0) {
            path.addFirst(curr);
            if (curr == start)
                break;
            curr = parent[curr];
        }

        if (path.isEmpty() || path.getFirst() != start) {
            return null;
        }
        return path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lineNMSDK = br.readLine();
        if (lineNMSDK == null)
            return;
        String[] partsNMSDK = lineNMSDK.split(" ");
        int N = Integer.parseInt(partsNMSDK[0]);
        int M = Integer.parseInt(partsNMSDK[1]);
        int S = Integer.parseInt(partsNMSDK[2]);
        int D = Integer.parseInt(partsNMSDK[3]);
        int K = Integer.parseInt(partsNMSDK[4]);

        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            if (line == null)
                break;
            String[] parts = line.split(" ");

            if (parts.length < 2)
                continue;

            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            adj[u].add(v);
        }

        int[] parentSK = new int[N + 1];
        int[] distSK = new int[N + 1];
        runBFS(S, N, adj, parentSK, distSK);

        int[] parentKD = new int[N + 1];
        int[] distKD = new int[N + 1];
        runBFS(K, N, adj, parentKD, distKD);

        if (distSK[K] == -1 || distKD[D] == -1) {
            System.out.println("-1");
            return;
        }

        List<Integer> pathSK = reconstructPath(S, K, parentSK, distSK);
        List<Integer> pathKD = reconstructPath(K, D, parentKD, distKD);

        if (pathSK == null || pathKD == null) {
            System.out.println("-1");
            return;
        }

        int totalLength = distSK[K] + distKD[D];
        System.out.println(totalLength);

        StringBuilder sb = new StringBuilder();

        for (int node : pathSK) {
            sb.append(node).append(" ");
        }

        for (int i = 1; i < pathKD.size(); i++) {
            sb.append(pathKD.get(i)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}