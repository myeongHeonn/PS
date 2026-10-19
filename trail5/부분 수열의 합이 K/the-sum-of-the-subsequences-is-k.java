import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int value = prefixSum[j] - prefixSum[i - 1];
                if (value == k) count++;
            }
        }

        System.out.println(count);
    }
}