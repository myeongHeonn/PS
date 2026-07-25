import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
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
                
                for (int k = 0; k < n; k++) {
                    // k번 노드를 방문한 적 있으면 패스
                    if (((i >> k) & 1) == 1) continue;
                    // j번 노드에서 k번 노드로 가는 길이 없으면 패스
                    if (cost[j][k] == 0) continue;
                    
                    dp[i + (1 << k)][k] = Math.min(dp[i + (1 << k)][k], dp[i][j] + cost[j][k]);
                }
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (cost[i][0] == 0) continue;
            
            ans = Math.min(ans, dp[(1 << n) - 1][i] + cost[i][0]);
        }
        
        System.out.println(ans);
    }
}