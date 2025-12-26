import java.io.*;
import java.util.*;

public class sixE {
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
            int M = sc.nextInt();
            int S_count = sc.nextInt();
            int Q_count = sc.nextInt();

            head = new int[N + 1];
            to = new int[2 * M + 1];
            next = new int[2 * M + 1];
            edgeCount = 0;

            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                addEdge(u, v);
                addEdge(v, u);
            }

            int[] dist = new int[N + 1];
            Arrays.fill(dist, -1);
            int[] q = new int[N + 1];
            int headQ = 0, tailQ = 0;

            
            for (int i = 0; i < S_count; i++) {
                int s = sc.nextInt();
                if (dist[s] == -1) {
                    dist[s] = 0;
                    q[tailQ++] = s;
                }
            }

            
            while (headQ < tailQ) {
                int u = q[headQ++];
                for (int i = head[u]; i != 0; i = next[i]) {
                    int v = to[i];
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        q[tailQ++] = v;
                    }
                }
            }

            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Q_count; i++) {
                int dest = sc.nextInt();
                sb.append(dist[dest]).append(i == Q_count - 1 ? "" : " ");
            }
            out.println(sb.toString());
        }
        out.flush();
        out.close();
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