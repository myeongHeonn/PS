import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge {
    int to;
    int cost;
    
    public Edge (int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] indegree = new int[N + 1];
        int[] r_indegree = new int[N + 1];
        
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[a].add(new Edge(b, c));
            indegree[b]++;
            r_indegree[a]++;
        }
        
        ArrayList<Integer> small_slice = new ArrayList<Integer>();
        
        for (int i = 1; i <= N; i++) {
            if (r_indegree[i] == 0) {
                small_slice.add(i);
            }
        }
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        
        for (int i = 1; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            
            for (Edge next : graph[x]) {
                int to = next.to;
                int cost = next.cost;
                
                indegree[to]--;
                
                if (indegree[to] == 0) {
                    q.add(to);
                }
            }
        }
        
        
        int[] dp = new int[N + 1];
        
        dp[N] = 1;
        q.add(N);
        
        while (!q.isEmpty()) {
            int x = q.poll();
            
            for (Edge next : graph[x]) {
                int to = next.to;
                int cost = next.cost;
                
                indegree[to]--;
                dp[to] += (dp[x] * cost);
                
                if (indegree[to] == 0) {
                    q.add(to);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int x : small_slice) {
            if (dp[x] > 0) {
                sb.append(x).append(" ").append(dp[x]).append("\n");
            }
        }
        
        System.out.println(sb);
    }
}