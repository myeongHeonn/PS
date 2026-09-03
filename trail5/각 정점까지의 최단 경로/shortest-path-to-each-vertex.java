import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
    int to;
    int cost;
    
    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {
    
    static int N, M, K;
    static ArrayList<Edge>[] graph;
    static int[] dist;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<Edge>();
        
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) dist[i] = 1_000_000_000;
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        
        dijkstra(K);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= N; i++) {
            int value = (dist[i] == 1_000_000_000 ? -1 : dist[i]);
            sb.append(value).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            
            if (now.cost > dist[now.to]) continue;
            
            for (Edge next : graph[now.to]) {
                
                int newCost = now.cost + next.cost;
                
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.add(new Edge(next.to, newCost));
                }
            }
        }
    }
}