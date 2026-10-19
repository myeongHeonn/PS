import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        int[][] prefixSum = new int[n + 1][n + 1];
        for (int i  = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j]
                                + prefixSum[i][j - 1]
                                - prefixSum[i - 1][j - 1]
                                + grid[i][j];
            }
        }

        int answer = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int[] dp = new int[n + 1];

                for (int k = 1; k <= n; k++) {
                    int value = prefixSum[j][k]
                                - prefixSum[j][k - 1]
                                - prefixSum[i - 1][k]
                                + prefixSum[i - 1][k - 1];

                    dp[k] = Math.max(value, value + dp[k - 1]);
                    answer = Math.max(answer, dp[k]);
                }
            }
        }

        System.out.println(answer);
    }
}