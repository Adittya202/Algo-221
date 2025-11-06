import java.io.*;
import java.util.*;

public class revisitedCount{
    static int[] A;
    static int[] temp;
    static int N;
    static long count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        temp = new int[N];
        String[] elems = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(elems[i]);
        }

        mergeSort(0, N - 1);

        System.out.println(count);
    }

    static void mergeSort(int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        countPairs(left, mid, right);
        merge(left, mid, right);
    }

    static void countPairs(int left, int mid, int right) {
        int size = right - mid;
        long[] squares = new long[size];
        for (int i = 0; i < size; i++) {
            squares[i] = 1L * A[mid + 1 + i] * A[mid + 1 + i];
        }
        Arrays.sort(squares);

        for (int i = left; i <= mid; i++) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int m = (lo + hi) / 2;
                if (A[i] > squares[m]) {
                    lo = m + 1;
                } else {
                    hi = m;
                }
            }
            count += lo;
        }
    }

    static void merge(int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (A[i] <= A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }
        while (i <= mid) temp[k++] = A[i++];
        while (j <= right) temp[k++] = A[j++];
        for (i = left; i <= right; i++) {
            A[i] = temp[i];
        }
    }
}