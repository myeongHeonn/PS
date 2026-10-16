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
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        int[] indegree = new int[N + 1];
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        int[] dp = new int[N + 1];
        int[] p = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 1; i <= N; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            int command = Integer.parseInt(st.nextToken());
            
            p[i] = command;
            
            while (true) {
                
                command = Integer.parseInt(st.nextToken());
                
                if (command == -1) break;
                
                graph[command].add(i);
                indegree[i]++;
            }
        }
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                dp[i] = p[i];
            }
        }
        
        while (!q.isEmpty()) {
            int x = q.poll();
            
            for (int next : graph[x]) {
                indegree[next]--;
                
                dp[next] = Math.max(dp[next], dp[x] + p[next]);
                
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append("\n");
        }
        
        System.out.println(sb);
    }
}