import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        Arrays.sort(arr, 1, n + 1);

        int i = 1;
        int j = n;
        int ans = Integer.MAX_VALUE;

        while (i < j) {
            int sumVal = arr[i] + arr[j];

            if (sumVal >= 0) j--;
            else i++;

            ans = Math.min(ans, Math.abs(sumVal));
        }

        System.out.println(ans);
    }
}