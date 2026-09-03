import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int to;
    int cost;
    
    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class Main {
    
    static int INF = 1_000_000_000;
    static int N, M;
    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Node>();
            dist[i] = INF;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[b].add(new Node(a, c));
        }
        
        dijkstra(N);
        
        int maxDist = 0;
        
        for (int i = 1; i < N; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }
        
        System.out.println(maxDist);
    }
    
    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.cost > dist[now.to]) continue;
            
            for (Node next : graph[now.to]) {
                int newCost = dist[now.to] + next.cost;
                
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.add(new Node(next.to, newCost));
                }
            }
        }
    }
}