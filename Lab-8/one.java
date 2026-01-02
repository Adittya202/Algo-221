import java.io.*;
import java.util.*;

public class one {
    static int[] parent;
    static int[] circleSize;

    static int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    static int union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            if (circleSize[rootI] < circleSize[rootJ]) {
                parent[rootI] = rootJ;
                circleSize[rootJ] += circleSize[rootI];
                return circleSize[rootJ];
            } else {
                parent[rootJ] = rootI;
                circleSize[rootI] += circleSize[rootJ];
                return circleSize[rootI];
            }
        }
        return circleSize[rootI];
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
            int k = sc.nextInt();

            parent = new int[n + 1];
            circleSize = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                circleSize[i] = 1;
            }

            for (int i = 0; i < k; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                out.println(union(a, b));
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
        int nextInt() { return Integer.parseInt(next()); }
    }
}