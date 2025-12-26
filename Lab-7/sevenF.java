import java.io.*;
import java.util.*;

public class sevenF {
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

    static class Node implements Comparable<Node> {
        int u;
        long d;
        Node(int u, long d) { this.u = u; this.d = d; }
        public int compareTo(Node other) { return Long.compare(this.d, other.d); }
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
            int s = sc.nextInt();
            int dNode = sc.nextInt();

            head = new int[n + 1];
            to = new int[2 * m + 1]; // Bidirectional
            weight = new int[2 * m + 1];
            next = new int[2 * m + 1];
            edgeCount = 0;

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                addEdge(u, v, w);
                addEdge(v, u, w);
            }

            long[] dist1 = new long[n + 1];
            long[] dist2 = new long[n + 1];
            Arrays.fill(dist1, INF);
            Arrays.fill(dist2, INF);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            dist1[s] = 0;
            pq.add(new Node(s, 0));

            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                int u = curr.u;
                long d = curr.d;

                if (d > dist2[u]) continue;

                for (int i = head[u]; i != 0; i = next[i]) {
                    int v = to[i];
                    int w = weight[i];
                    long newDist = d + w;

                    if (newDist < dist1[v]) {
                        dist2[v] = dist1[v];
                        dist1[v] = newDist;
                        pq.add(new Node(v, dist1[v]));
                        pq.add(new Node(v, dist2[v]));
                    } else if (newDist > dist1[v] && newDist < dist2[v]) {
                        dist2[v] = newDist;
                        pq.add(new Node(v, dist2[v]));
                    }
                }
            }

            out.println(dist2[dNode] >= INF ? -1 : dist2[dNode]);
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