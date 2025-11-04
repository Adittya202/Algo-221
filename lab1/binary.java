import java.io.*;

public class binary {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int q = Integer.parseInt(parts[1]);

        String[] parts2 = br.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts2[i]);
        }

        while (q-- > 0) {
            String[] pair = br.readLine().split(" ");
            int x = Integer.parseInt(pair[0]);
            int y = Integer.parseInt(pair[1]);

            int lower = lower_bound(a, x);
            int upper = upper_bound(a, y);

            System.out.println(upper - lower);
        }
    }

    // first index where a[i] >= target
    public static int lower_bound(int[] a, int target) {
        int low = 0, high = a.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (a[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // first index where a[i] > target
    public static int upper_bound(int[] a, int target) {
        int low = 0, high = a.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (a[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}