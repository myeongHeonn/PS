import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        int[] indegree = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            graph[a].add(b);
            indegree[b]++;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        
        while (!pq.isEmpty()) {
            int x = pq.poll();
            sb.append(x).append(" ");
            cnt++;
            
            for (int next : graph[x]) {
                indegree[next]--;
                
                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
        
        if (cnt == n) {
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }
}