import java.io.*;

public class powerDrift {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ;
        String[] E = br.readLine().split(" ");
        long a=Long.parseLong(E[0]);
        long b=Long.parseLong(E[1]);
        pow(a, b);
    }

    public static void pow(long a, long b) {
        long ans = 1;
        while (b > 0) {
            if (b % 2 != 0) {
                ans=(ans*a)%107;
                b = b - 1;
            } else if (b % 2 == 0) {
                a = (a *a)% 107;
                b = b / 2;
            }
        }
        System.out.print(ans);
    }
}