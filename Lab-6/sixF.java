import java.io.*;
import java.util.*;

public class sixF {

    static final int MAX = 5000;
    static int[] spf = new int[MAX + 1];

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        buildSPF();

        int T = fs.nextInt();

        while (T-- > 0) {
            int s = fs.nextInt();
            int t = fs.nextInt();
            out.append(bfs(s, t)).append('\n');
        }

        System.out.print(out.toString());
    }

    static int bfs(int s, int t) {
        if (s > t) return -1;
        if (s == t) return 0;

        int[] dist = new int[t + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        dist[s] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            int x = cur;
            Set<Integer> used = new HashSet<>();

            while (x > 1) {
                int p = spf[x];
                used.add(p);
                while (x % p == 0) x /= p;
            }

            for (int p : used) {
                if (p == cur) continue;

                int next = cur + p;
                if (next <= t && dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    if (next == t) return dist[next];
                    q.add(next);
                }
            }
        }

        return -1;
    }

    static void buildSPF() {
        for (int i = 1; i <= MAX; i++) spf[i] = i;

        for (int i = 2; i * i <= MAX; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = read();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = read();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
