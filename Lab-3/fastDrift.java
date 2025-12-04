import java.io.*;

public class fastDrift {

    static long m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // number of test cases

        while (T-- > 0) {
            String[] x = br.readLine().split(" ");
            long a = Long.parseLong(x[0]);
            long n = Long.parseLong(x[1]);
            m = Long.parseLong(x[2]);
            System.out.println(sum(a, n));
        }
    }

    // a^b % m (fast exponentiation)
    static long modPow(long a, long b) {
        long ans = 1;
        a %= m;
        while (b > 0) {
            if ((b & 1) == 1) ans = (ans * a) % m;
            a = (a * a) % m;
            b >>= 1;
        }
        return ans;
    }

    // a^1 + a^2 + ... + a^n % m   (O(log n))
    static long sum(long a, long n) {
        if (n == 1) return a % m;

        long half = n / 2;
        long s = sum(a, half);        // sum of first half
        long p = modPow(a, half);     // shift factor

        long ans = s + (s * p) % m;   // firstHalf + shifted firstHalf

        if ((n & 1) == 1) ans += modPow(a, n); // if odd, add last term

        return ans % m;
    }
}
