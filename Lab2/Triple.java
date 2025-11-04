import java.util.*;
import java.io.*;

public class Triple {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts1 = br.readLine().split(" ");
        String[] parts2 = br.readLine().split(" ");
        int n = Integer.parseInt(parts1[0]);
        int x = Integer.parseInt(parts1[1]);
        int[] arr = new int[n + 1];
        int[] idx = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(parts2[i - 1]);
            idx[i] = i;
        }
        // have to sort the original value and index of the array
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    int temp2 = idx[j];
                    idx[j] = idx[i];
                    idx[i] = temp2;
                }
            }
        }

        boolean found = false;
        int final_i = 0;
        int left = 0;
        int right = 0;

        for (int i = 1; i <= n; i++) {
            left = i + 1;
            right = n;
            int sum = 0;

            while (right > left) {
                sum = arr[i] + arr[left] + arr[right];
                if (sum < x) {
                    left++;
                } else if (sum > x) {
                    right--;
                } else {
                    final_i = i;
                    found = true;
                    break;
                }

            }
            if (sum == x) {
                break;
            }
        }
        if (found == true) {
            System.out.print(idx[final_i] + " " + idx[left] + " " + idx[right]);
        } else {
            System.out.print(-1);
        }

    }
}
