import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
                int[][] dp = new int[(1 << n)][n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1_000_000_000;
            }
        }
        
        dp[1][0] = 0;
        
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) continue;
                
                for (int l = 0; l < n; l++) {
                    if (((i >> l) & 1) == 1) continue;
                    if (cost[j][l] == 0) continue;
                    
                    dp[i + (1 << l)][l] = Math.min(dp[i + (1 << l)][l], dp[i][j] + cost[j][l]);
                }
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << n); i++) {
            if ((i & 1) == 1 && Integer.bitCount(i) == k + 1) {
                for (int j = 0; j < n; j++) {
                    if (cost[j][0] != 0) {
                        ans = Math.min(ans, dp[i][j] + cost[j][0]);
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
}