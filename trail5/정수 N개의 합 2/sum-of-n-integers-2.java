import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int answer = -1_000_000_000;

        for (int i = 0; i <= (n - k); i++) {
            answer = Math.max(answer, prefixSum[i + k - 1] - prefixSum[i] + arr[i]);
        }

        System.out.println(answer);
    }
}