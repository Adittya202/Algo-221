import java.io.*;
import java.util.*;

public class six {
    static class Task {
        int duration, deadline;
        Task(int a, int d) {
            this.duration = a;
            this.deadline = d;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int tCases = 1; 
        // tCases = sc.nextInt(); 

        while (tCases-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            int n = Integer.parseInt(nStr);

            Task[] tasks = new Task[n];
            for (int i = 0; i < n; i++) {
                tasks[i] = new Task(sc.nextInt(), sc.nextInt());
            }

            
            Arrays.sort(tasks, (t1, t2) -> Integer.compare(t1.duration, t2.duration));

            long currentFinishingTime = 0;
            long totalReward = 0;

            for (int i = 0; i < n; i++) {
                currentFinishingTime += tasks[i].duration;
                totalReward += (tasks[i].deadline - currentFinishingTime);
            }

            out.println(totalReward);
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