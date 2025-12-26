import java.io.*;
import java.util.*;

public class sixA {
    static int[] head, to, next, indegree;
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

        
        // int T = sc.nextInt();
        //remove T=1;
        int T = 1; 
        // ------------------

        while (T-- > 0) {
            String firstToken = sc.next();
            if (firstToken == null) break;
            
            int N = Integer.parseInt(firstToken);
            int M = sc.nextInt();

            
            head = new int[N + 1];
            next = new int[M + 1];
            to = new int[M + 1];
            indegree = new int[N + 1];
            edgeCount = 0;

            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                addEdge(u, v);
                indegree[v]++;
            }

           
            int[] q = new int[N];
            int headQ = 0, tailQ = 0;

            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) q[tailQ++] = i;
            }

            int count = 0;
            StringBuilder sb = new StringBuilder();
            
            while (headQ < tailQ) {
                int u = q[headQ++];
                count++;
                sb.append(u).append(" ");

                for (int i = head[u]; i != 0; i = next[i]) {
                    int v = to[i];
                    if (--indegree[v] == 0) {
                        q[tailQ++] = v;
                    }
                }
            }

            if (count != N) {
                out.println("-1");
            } else {
                out.println(sb.toString().trim());
            }
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