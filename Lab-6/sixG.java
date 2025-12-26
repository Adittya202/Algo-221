import java.io.*;
import java.util.*;

public class sixG {
    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        
        // int T = sc.nextInt();
        
        int T = 1; 
        

        while (T-- > 0) {
            String nStr = sc.next();
            if (nStr == null) break;
            int N = Integer.parseInt(nStr);
            String[] words = new String[N];
            boolean[] present = new boolean[26];

            for (int i = 0; i < N; i++) {
                words[i] = sc.next();
                for (char c : words[i].toCharArray()) present[c - 'a'] = true;
            }

            List<Integer>[] adj = new ArrayList[26];
            for (int i = 0; i < 26; i++) adj[i] = new ArrayList<>();
            int[] indegree = new int[26];
            boolean possible = true;

            for (int i = 0; i < N - 1; i++) {
                String w1 = words[i];
                String w2 = words[i + 1];
                int len = Math.min(w1.length(), w2.length());
                boolean foundDiff = false;
                for (int k = 0; k < len; k++) {
                    if (w1.charAt(k) != w2.charAt(k)) {
                        int u = w1.charAt(k) - 'a';
                        int v = w2.charAt(k) - 'a';
                        adj[u].add(v);
                        indegree[v]++;
                        foundDiff = true;
                        break;
                    }
                }
               
                if (!foundDiff && w1.length() > w2.length()) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                out.println("-1");
                continue;
            }

           
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int totalPresent = 0;
            for (int i = 0; i < 26; i++) {
                if (present[i]) {
                    totalPresent++;
                    if (indegree[i] == 0) pq.add(i);
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty()) {
                int u = pq.poll();
                sb.append((char) (u + 'a'));
                for (int v : adj[u]) {
                    if (--indegree[v] == 0) pq.add(v);
                }
            }

            if (sb.length() != totalPresent) {
                out.println("-1"); 
            } else {
                out.println(sb.toString());
            }
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
    }
}