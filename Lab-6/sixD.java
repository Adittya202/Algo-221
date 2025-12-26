import java.io.*;
import java.util.*;

public class sixD {
    static int[] head, to, next;
    static int edgeCount;

    static void addEdge(int u, int v) {
        edgeCount++;
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
    }

    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        
        // For Lab: int T = sc.nextInt();
        
        int T = 1; 
        

        while (T-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            int N = Integer.parseInt(nStr);

            if (N == 1) {
                out.println(0);
                out.println("1 1");
                continue;
            }

            head = new int[N + 1];
            to = new int[2 * N + 1];
            next = new int[2 * N + 1];
            edgeCount = 0;

            for (int i = 0; i < N - 1; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                addEdge(u, v);
                addEdge(v, u);
            }

            
            int[] resA = bfs(1, N);
            int nodeA = resA[0];

            
            int[] resB = bfs(nodeA, N);
            int nodeB = resB[0];
            int diameter = resB[1];

            out.println(diameter);
            out.println(nodeA + " " + nodeB);
        }
        out.flush();
        out.close();
    }

   
    static int[] bfs(int start, int N) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        int[] q = new int[N + 1];
        int h = 0, t = 0;

        dist[start] = 0;
        q[t++] = start;

        int farthestNode = start;
        int maxDist = 0;

        while (h < t) {
            int u = q[h++];
            if (dist[u] > maxDist) {
                maxDist = dist[u];
                farthestNode = u;
            }
            for (int i = head[u]; i != 0; i = next[i]) {
                int v = to[i];
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q[t++] = v;
                }
            }
        }
        return new int[]{farthestNode, maxDist};
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream in) { br = new BufferedReader(new InputStreamReader(in)); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (Exception e) { return null; }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }
}