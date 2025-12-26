import java.io.*;
import java.util.*;

public class sevenD {
    static int[] head, to, next;
    static int[] nodeWeights;
    static int edgeCount;
    static final long INF = Long.MAX_VALUE / 2;

    static void addEdge(int u, int v) {
        edgeCount++;
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
    }

    static class Node implements Comparable<Node> {
        int id;
        long cost;
        Node(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tCases = 1; 
        /* loop segment for the test cases: UNCOMMENT BELOW FOR EXAM */
        // tCases = sc.nextInt(); 

        while (tCases-- > 0) {
            String firstVal = sc.next();
            if (firstVal == null) break;
            
            int n = Integer.parseInt(firstVal);
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            nodeWeights = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                nodeWeights[i] = sc.nextInt();
            }

            head = new int[n + 1];
            to = new int[m + 1];
            next = new int[m + 1];
            edgeCount = 0;

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                addEdge(u, v);
            }

            long[] dist = new long[n + 1];
            Arrays.fill(dist, INF);
            
            // The starting cost is the weight of the source node
            dist[s] = nodeWeights[s];

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(s, dist[s]));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int u = current.id;
                if (current.cost > dist[u]) continue;

                for (int i = head[u]; i != 0; i = next[i]) {
                    int v = to[i];
                    // Cost to reach node v is current cost + weight of node v
                    if (dist[u] + nodeWeights[v] < dist[v]) {
                        dist[v] = dist[u] + nodeWeights[v];
                        pq.add(new Node(v, dist[v]));
                    }
                }
            }

            if (dist[d] >= INF) {
                out.println("-1");
            } else {
                out.println(dist[d]);
            }
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
        int nextInt() {
            String s = next();
            if (s == null) return -1;
            return Integer.parseInt(s);
        }
    }
}