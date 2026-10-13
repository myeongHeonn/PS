import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N + 1];
        
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            indegree[b]++;
            graph[a].add(b);
        }
        
        int[] dp = new int[N + 1];
        Queue<Integer> q = new ArrayDeque<Integer>();
        
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                dp[i] = 1;
                q.add(i);
            }
        }
        
        boolean[] p = new boolean[N + 1];
        
        while (!q.isEmpty()) {
            int x = q.poll();
            
            for (int next : graph[x]) {
                indegree[next]--;
                
                if (dp[next] < dp[x]) {
                    p[next] = false;
                    dp[next] = dp[x];
                }
                else if (dp[next] == dp[x]) {
                    p[next] = true;
                }
                
                if (indegree[next] == 0) {
                    if (p[next]) dp[next]++;
                    q.add(next);
                }
            }
        }
        
        int answer = 0;
        
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dp[i]);
        }
        
        System.out.println(answer);
    }
}