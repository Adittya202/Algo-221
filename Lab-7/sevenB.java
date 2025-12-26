import java.io.*;
import java.util.*;

public class sevenB {
    static int[] head, to, next, weight;
    static int edgeCount;
    static final long INF = 1_000_000_000_000_000L;

    static void addEdge(int u, int v, int w) {
        edgeCount++;
        to[edgeCount] = v;
        weight[edgeCount] = w;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
    }

    static class Node implements Comparable<Node> {
        int id;
        long dist;
        Node(int id, long dist) {
            this.id = id;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        if (sc.hasNext()) {
            int tCases = 1; 
            //  tCases = sc.nextInt();
            
           
            while (tCases-- > 0) {
                int n = sc.nextInt();
                int m = sc.nextInt();
                int s = sc.nextInt();
                int t = sc.nextInt();

                head = new int[n + 1];
                to = new int[m + 1];
                weight = new int[m + 1];
                next = new int[m + 1];
                edgeCount = 0;

                for (int i = 0; i < m; i++) {
                    addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
                }

                long[] distA = dijkstra(s, n);
                long[] distB = dijkstra(t, n);

                long minTime = INF;
                int meetingNode = -1;

                for (int i = 1; i <= n; i++) {
                    if (distA[i] != INF && distB[i] != INF) {
                        long curTime = Math.max(distA[i], distB[i]);
                        if (curTime < minTime) {
                            minTime = curTime;
                            meetingNode = i;
                        } else if (curTime == minTime) {
                            if (meetingNode == -1 || i < meetingNode) meetingNode = i;
                        }
                    }
                }

                if (meetingNode == -1) out.println("-1");
                else out.println(minTime + " " + meetingNode);
            }
        }
        out.flush();
        out.close();
    }

    static long[] dijkstra(int start, int n) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;
            if (current.dist > dist[u]) continue;

            for (int i = head[u]; i != 0; i = next[i]) {
                int v = to[i];
                if (dist[u] + weight[i] < dist[v]) {
                    dist[v] = dist[u] + weight[i];
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        return dist;
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        public FastScanner(InputStream in) { br = new BufferedReader(new InputStreamReader(in)); }
        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try { String line = br.readLine(); if (line == null) return null;
                    st = new StringTokenizer(line); } catch (Exception e) { return null; }
            }
            return st.nextToken();
        }
        public boolean hasNext() {
            while (st == null || !st.hasMoreElements()) {
                try { String line = br.readLine(); if (line == null) return false;
                    st = new StringTokenizer(line); } catch (Exception e) { return false; }
            }
            return true;
        }
        public int nextInt() { return Integer.parseInt(next()); }
    }
}