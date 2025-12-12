import java.io.*;
import java.util.*;

public class one {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        int T = 1;  
        // T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            String[] part = br.readLine().split(" ");
            int N = Integer.parseInt(part[0]);
            int M = Integer.parseInt(part[1]);

           
            ArrayList<Integer>[] col = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) col[i] = new ArrayList<>();

            int[] indegree = new int[N + 1];

            for (int i = 0; i < M; i++) {
                String[] p = br.readLine().split(" ");
                int A = Integer.parseInt(p[0]);
                int B = Integer.parseInt(p[1]);
                col[A].add(B);
                indegree[B]++;
            }

           
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) queue.add(i);
            }

            int[] result = new int[N];
            int idx = 0;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                result[idx++] = u;

                for (int v : col[u]) {
                    if (--indegree[v] == 0) {
                        queue.add(v);
                    }
                }
            }

            
            if (idx != N) {
                output.append("-1\n");
            } else {
                for (int i = 0; i < N; i++) {
                    output.append(result[i]).append(" ");
                }
                output.append("\n");
            }
        }

        System.out.print(output.toString());
    }
}
