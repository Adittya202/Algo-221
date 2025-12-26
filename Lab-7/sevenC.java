import java.io.*;
import java.util.*;

public class sevenC {
    // Adjacency list arrays for bi-directional edges
    static int[] head, to, next, weight;
    static int edgeCount;
    static final int INF = Integer.MAX_VALUE;

    static void addEdge(int u, int v, int w) {
        edgeCount++;
        to[edgeCount] = v;
        weight[edgeCount] = w;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
    }

    // Node class for PriorityQueue
    static class Node implements Comparable<Node> {
        int id, danger;
        Node(int id, int danger) {
            this.id = id;
            this.danger = danger;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.danger, other.danger);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        /* loop segment for the test cases */
        // Change to 'int t = sc.nextInt();' if the exam provides T on the first line
        int t = 1; 
        while (t-- > 0) {
            String firstVal = sc.next();
            if (firstVal == null) break;
            
            int n = Integer.parseInt(firstVal);
            int m = sc.nextInt();

            // Initialize arrays for n cities and 2*m bi-directional edges
            head = new int[n + 1];
            to = new int[2 * m + 1];
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

            int[] minMaxDanger = new int[n + 1];
            Arrays.fill(minMaxDanger, INF);
            minMaxDanger[1] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(1, 0));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int u = current.id;
                int d = current.danger;

                if (d > minMaxDanger[u]) continue;

                for (int i = head[u]; i != 0; i = next[i]) {
                    int v = to[i];
                    // Minimax logic: danger to reach v is max(danger to reach u, edge danger)
                    int currentPathDanger = Math.max(minMaxDanger[u], weight[i]);
                    if (currentPathDanger < minMaxDanger[v]) {
                        minMaxDanger[v] = currentPathDanger;
                        pq.add(new Node(v, minMaxDanger[v]));
                    }
                }
            }

            // Print output for all cities from 1 to n
            for (int i = 1; i <= n; i++) {
                out.print((minMaxDanger[i] == INF ? -1 : minMaxDanger[i]) + (i == n ? "" : " "));
            }
            out.println();
        }
        out.flush();
        out.close();
    }

    // Verified FastScanner to avoid TLE and Compilation errors
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}