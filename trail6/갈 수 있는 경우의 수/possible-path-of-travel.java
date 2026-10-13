import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] indegree = new int[n + 1];
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            indegree[b]++;
            graph[a].add(b);
        }
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        int[] dp = new int[n + 1];
        
        q.add(1);
        dp[1] = 1;
        
        while (!q.isEmpty()) {
            int x = q.poll();
            
            for (int next : graph[x]) {
                indegree[next]--;
                dp[next] = (dp[next] + dp[x]) % MOD;
                
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        
        System.out.println(dp[n]);
    }
}