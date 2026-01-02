import java.io.*;
import java.util.*;

public class four {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tCases = 1; 
        // tCases = sc.nextInt(); 

        while (tCases-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            int n = Integer.parseInt(nStr);

            long[][] tasks = new long[n][2];
            for (int i = 0; i < n; i++) {
                tasks[i][0] = sc.nextLong(); 
                tasks[i][1] = sc.nextLong(); 
            }

            
            Arrays.sort(tasks, (a, b) -> {
                if (a[1] != b[1]) return Long.compare(a[1], b[1]);
                return Long.compare(a[0], b[0]);
            });

            StringBuilder sb = new StringBuilder();
            int count = 0;
            long lastEnd = -1; 

            for (int i = 0; i < n; i++) {
                
                if (tasks[i][0] > lastEnd) {
                    count++;
                    lastEnd = tasks[i][1];
                    sb.append(tasks[i][0]).append(" ").append(tasks[i][1]).append("\n");
                }
            }

            out.println(count);
            out.print(sb.toString());
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
        long nextLong() { return Long.parseLong(next()); }
    }
}