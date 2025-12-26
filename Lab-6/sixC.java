import java.io.*;
import java.util.*;

public class sixC {
   
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

       
        // int T = sc.nextInt();
        
        int T = 1; 
        

        while (T-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            int N = Integer.parseInt(nStr);

            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();

            if (r1 == r2 && c1 == c2) {
                out.println(0);
                continue;
            }

            // dist array to store steps and mark visited
            int[][] dist = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) Arrays.fill(dist[i], -1);

            // Fast array-based queue (storing packed coordinates: r * (N+1) + c)
            int[] q = new int[N * N + 1];
            int head = 0, tail = 0;

            dist[r1][c1] = 0;
            q[tail++] = r1 * (N + 1) + c1;

            int ans = -1;
            while (head < tail) {
                int curr = q[head++];
                int r = curr / (N + 1);
                int c = curr % (N + 1);

                if (r == r2 && c == c2) {
                    ans = dist[r][c];
                    break;
                }

                for (int i = 0; i < 8; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];

                    if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        q[tail++] = nr * (N + 1) + nc;
                    }
                }
            }
            out.println(ans);
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