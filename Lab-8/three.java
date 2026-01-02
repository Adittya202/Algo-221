import java.io.*;
import java.util.*;

public class three {
    static int n, m;
    static int[] parent;
    static ArrayList<int[]>[] adj;
    static int[] depth;
    static int[][] up;
    static int[][] max1; 
    static int[][] max2; 
    static final int LOG = 12; 

    static int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    static void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }

   
    static int[] combine(int m1a, int m2a, int m1b, int m2b) {
        int[] vals = {m1a, m2a, m1b, m2b};
        int best = -1, second = -1;
        for (int v : vals) {
            if (v == -1) continue;
            if (v > best) {
                second = best;
                best = v;
            } else if (v > second && v != best) {
                second = v;
            }
        }
        return new int[]{best, second};
    }

    static void dfs(int u, int p, int w, int d) {
        depth[u] = d;
        up[u][0] = p;
        max1[u][0] = w;
        max2[u][0] = -1;

        for (int i = 1; i < LOG; i++) {
            int ancestor = up[u][i - 1];
            if (ancestor != -1) {
                up[u][i] = up[ancestor][i - 1];
                int[] merged = combine(max1[u][i - 1], max2[u][i - 1], max1[ancestor][i - 1], max2[ancestor][i - 1]);
                max1[u][i] = merged[0];
                max2[u][i] = merged[1];
            } else {
                up[u][i] = -1;
                max1[u][i] = -1;
                max2[u][i] = -1;
            }
        }

        for (int[] edge : adj[u]) {
            if (edge[0] != p) {
                dfs(edge[0], u, edge[1], d + 1);
            }
        }
    }

    static int[] queryPath(int u, int v) {
        int res1 = -1, res2 = -1;
        
        if (depth[u] < depth[v]) { int temp = u; u = v; v = temp; }

        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                int[] merged = combine(res1, res2, max1[u][i], max2[u][i]);
                res1 = merged[0];
                res2 = merged[1];
                u = up[u][i];
            }
        }

        if (u == v) return new int[]{res1, res2};

        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                int[] merged = combine(res1, res2, max1[u][i], max2[u][i]);
                res1 = merged[0];
                res2 = merged[1];
                
                merged = combine(res1, res2, max1[v][i], max2[v][i]);
                res1 = merged[0];
                res2 = merged[1];
                
                u = up[u][i];
                v = up[v][i];
            }
        }

        int[] merged = combine(res1, res2, max1[u][0], max2[u][0]);
        res1 = merged[0]; res2 = merged[1];
        merged = combine(res1, res2, max1[v][0], max2[v][0]);
        
        return merged;
    }

    static class Edge {
        int u, v, w, id;
        boolean inMST;
        Edge(int u, int v, int w, int id) {
            this.u = u; this.v = v; this.w = w; this.id = id; this.inMST = false;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tCases = 1; 
        // tCases = sc.nextInt(); 

        while (tCases-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            n = Integer.parseInt(nStr);
            m = sc.nextInt();

            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt(), i);
            }
            
            Arrays.sort(edges, (a, b) -> Integer.compare(a.w, b.w));

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;

            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            long mstCost = 0;
            int edgesCount = 0;

            for (Edge e : edges) {
                if (find(e.u) != find(e.v)) {
                    union(e.u, e.v);
                    mstCost += e.w;
                    edgesCount++;
                    e.inMST = true;
                    adj[e.u].add(new int[]{e.v, e.w});
                    adj[e.v].add(new int[]{e.u, e.w});
                }
            }

            if (edgesCount != n - 1) {
                out.println("-1");
                continue;
            }

            depth = new int[n + 1];
            up = new int[n + 1][LOG];
            max1 = new int[n + 1][LOG];
            max2 = new int[n + 1][LOG];

            
            dfs(1, -1, -1, 0);

            long secondBest = Long.MAX_VALUE;

            for (Edge e : edges) {
                if (!e.inMST) {
                    int[] pathMax = queryPath(e.u, e.v);
                    int m1 = pathMax[0];
                    int m2 = pathMax[1];

                    if (m1 != -1) {
                        if (e.w > m1) {
                            secondBest = Math.min(secondBest, mstCost - m1 + e.w);
                        } else if (e.w == m1 && m2 != -1) {
                            secondBest = Math.min(secondBest, mstCost - m2 + e.w);
                        }
                    }
                }
            }

            if (secondBest == Long.MAX_VALUE) out.println("-1");
            else out.println(secondBest);
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