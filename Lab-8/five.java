import java.io.*;
import java.util.*;

public class five {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tCases = sc.nextInt(); 

        while (tCases-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            int n = Integer.parseInt(nStr);
            int m = sc.nextInt();

            long[][] tasks = new long[n][2];
            for (int i = 0; i < n; i++) {
                tasks[i][0] = sc.nextLong(); 
                tasks[i][1] = sc.nextLong(); 
            }

            Arrays.sort(tasks, (a, b) -> {
                if (a[1] != b[1]) return Long.compare(a[1], b[1]);
                return Long.compare(a[0], b[0]);
            });

            TreeMap<Long, Integer> people = new TreeMap<>();
            people.put(-1L, m); 

            int count = 0;
            for (int i = 0; i < n; i++) {
                long start = tasks[i][0];
                long end = tasks[i][1];

                Long bestEnd = people.lowerKey(start);

                if (bestEnd != null) {
                    count++;
                    int num = people.get(bestEnd);
                    if (num == 1) people.remove(bestEnd);
                    else people.put(bestEnd, num - 1);

                    people.put(end, people.getOrDefault(end, 0) + 1);
                }
            }
            out.println(count);
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