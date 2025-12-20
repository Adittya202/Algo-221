import java.io.*;
import java.util.*;

public class sevenA {
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
            // Exam hint: if T is given, do tCases = sc.nextInt();

            /* loop segment for the test cases */
            while (tCases-- > 0) {
                int n = sc.nextInt();
                int m = sc.nextInt();
                int s = sc.nextInt();
                int dNode = sc.nextInt();

                // Read input based on the specific format in image B
                int[] uArray = new int[m];
                for (int i = 0; i < m; i++) uArray[i] = sc.nextInt();
                int[] vArray = new int[m];
                for (int i = 0; i < m; i++) vArray[i] = sc.nextInt();
                int[] wArray = new int[m];
                for (int i = 0; i < m; i++) wArray[i] = sc.nextInt();

                head = new int[n + 1];
                to = new int[m + 1];
                weight = new int[m + 1];
                next = new int[m + 1];
                edgeCount = 0;

                for (int i = 0; i < m; i++) {
                    addEdge(uArray[i], vArray[i], wArray[i]);
                }

                long[] dist = new long[n + 1];
                int[] parent = new int[n + 1];
                Arrays.fill(dist, INF);
                dist[s] = 0;

                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.add(new Node(s, 0));

                while (!pq.isEmpty()) {
                    Node current = pq.poll();
                    int u = current.id;
                    if (current.dist > dist[u]) continue;

                    for (int i = head[u]; i != 0; i = next[i]) {
                        int v = to[i];
                        if (dist[u] + weight[i] < dist[v]) {
                            dist[v] = dist[u] + weight[i];
                            parent[v] = u;
                            pq.add(new Node(v, dist[v]));
                        }
                    }
                }

                if (dist[dNode] == INF) {
                    out.println("-1");
                } else {
                    out.println(dist[dNode]);
                    
                    // Reconstructing the path
                    List<Integer> path = new ArrayList<>();
                    for (int curr = dNode; curr != 0; curr = parent[curr]) {
                        path.add(curr);
                    }
                    Collections.reverse(path);
                    
                    for (int i = 0; i < path.size(); i++) {
                        out.print(path.get(i) + (i == path.size() - 1 ? "" : " "));
                    }
                    out.println();
                }
            }
        }
        out.flush();
        out.close();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        public FastScanner(InputStream in) { br = new BufferedReader(new InputStreamReader(in)); }
        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (Exception e) { return null; }
            }
            return st.nextToken();
        }
        public boolean hasNext() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return false;
                    st = new StringTokenizer(line);
                } catch (Exception e) { return false; }
            }
            return true;
        }
        public int nextInt() { return Integer.parseInt(next()); }
    }
}