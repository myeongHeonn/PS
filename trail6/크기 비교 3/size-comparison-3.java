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
            
            graph[a].add(b);
            indegree[b]++;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            int x = pq.poll();
            sb.append(x).append(" ");
            
            for (int next : graph[x]) {
                indegree[next]--;
                
                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
        
        System.out.println(sb);
    }
}