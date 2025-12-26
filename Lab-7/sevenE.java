import java.io.*;
import java.util.*;

public class sevenE {
    static int[] head, to, next, weight;
    static int edgeCount;
    static final long INF = Long.MAX_VALUE / 2;

    static void addEdge(int u, int v, int w) {
        edgeCount++;
        to[edgeCount] = v;
        weight[edgeCount] = w;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
    }

    static class State implements Comparable<State> {
        int u, lastParity;
        long d;

        State(int u, int lastParity, long d) {
            this.u = u;
            this.lastParity = lastParity;
            this.d = d;
        }

        public int compareTo(State other) {
            return Long.compare(this.d, other.d);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

       
        int tCases = 1;
        // String tStr = sc.next(); if(tStr != null) tCases = Integer.parseInt(tStr);

        while (tCases-- > 0) {
            String firstVal = sc.next();
            if (firstVal == null) break;
            int n = Integer.parseInt(firstVal);
            int m = sc.nextInt();

          
            int[] uArr = new int[m];
            for (int i = 0; i < m; i++) uArr[i] = sc.nextInt();
            int[] vArr = new int[m];
            for (int i = 0; i < m; i++) vArr[i] = sc.nextInt();
            int[] wArr = new int[m];
            for (int i = 0; i < m; i++) wArr[i] = sc.nextInt();

            head = new int[n + 1];
            to = new int[m + 1];
            weight = new int[m + 1];
            next = new int[m + 1];
            edgeCount = 0;

            for (int i = 0; i < m; i++) {
                addEdge(uArr[i], vArr[i], wArr[i]);
            }

            long[][] dist = new long[n + 1][2];
            for (int i = 0; i <= n; i++) Arrays.fill(dist[i], INF);

            PriorityQueue<State> pq = new PriorityQueue<>();
            
            
            for (int i = head[1]; i != 0; i = next[i]) {
                int v = to[i];
                int w = weight[i];
                int p = w % 2;
                if (w < dist[v][p]) {
                    dist[v][p] = w;
                    pq.add(new State(v, p, dist[v][p]));
                }
            }

            while (!pq.isEmpty()) {
                State curr = pq.poll();
                if (curr.d > dist[curr.u][curr.lastParity]) continue;

                int nextParity = 1 - curr.lastParity; // Must alternate

                for (int i = head[curr.u]; i != 0; i = next[i]) {
                    int v = to[i];
                    int w = weight[i];
                    if (w % 2 == nextParity) {
                        if (dist[curr.u][curr.lastParity] + w < dist[v][nextParity]) {
                            dist[v][nextParity] = dist[curr.u][curr.lastParity] + w;
                            pq.add(new State(v, nextParity, dist[v][nextParity]));
                        }
                    }
                }
            }

            long ans = Math.min(dist[n][0], dist[n][1]);
            out.println(ans >= INF ? -1 : ans);
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