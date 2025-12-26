import java.io.*;
import java.util.*;

public class sixB {
    static int[] head, to, next;
    static int[] color; // -1: unvisited, 0: color1, 1: color2
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

        
        //  int T = sc.nextInt();
        // comment int T = 1;
        int T = 1; 
        // ------------------

        while (T-- > 0) {
            String firstToken = sc.next();
            if (firstToken == null) break;
            
            int N = Integer.parseInt(firstToken);
            int M = sc.nextInt();

            head = new int[N + 1];
            // Undirected graph: M tackles means 2 * M entries in 'to' and 'next'
            next = new int[2 * M + 1];
            to = new int[2 * M + 1];
            color = new int[N + 1];
            Arrays.fill(color, -1);
            edgeCount = 0;

            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                addEdge(u, v);
                addEdge(v, u);
            }

            long totalMax = 0;
            int[] q = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                if (color[i] == -1) {
                    int headQ = 0, tailQ = 0;
                    int count0 = 0, count1 = 0;

                    color[i] = 0;
                    q[tailQ++] = i;
                    count0++;

                    while (headQ < tailQ) {
                        int u = q[headQ++];
                        for (int e = head[u]; e != 0; e = next[e]) {
                            int v = to[e];
                            if (color[v] == -1) {
                                color[v] = 1 - color[u];
                                if (color[v] == 0) count0++; else count1++;
                                q[tailQ++] = v;
                            }
                        }
                    }
                    // For each component, pick the larger group
                    totalMax += Math.max(count0, count1);
                }
            }
            out.println(totalMax);
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