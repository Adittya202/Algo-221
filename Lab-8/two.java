import java.io.*;
import java.util.*;

public class two {
    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.w, other.w);
        }
    }

    static int[] parent;

    static int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tCases = 1; 
        // tCases = sc.nextInt();

        while (tCases-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            
            int n = Integer.parseInt(nStr);
            int m = sc.nextInt();

            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
            }

            Arrays.sort(edges);

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;

            long totalCost = 0;
            int edgesCount = 0;

            for (Edge edge : edges) {
                int rootU = find(edge.u);
                int rootV = find(edge.v);

                if (rootU != rootV) {
                    parent[rootU] = rootV;
                    totalCost += edge.w;
                    edgesCount++;
                    if (edgesCount == n - 1) break;
                }
            }
            out.println(totalCost);
        }
        out.flush();
        out.close();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) { return null; }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }
}