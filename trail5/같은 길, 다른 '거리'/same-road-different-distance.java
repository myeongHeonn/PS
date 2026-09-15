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
    
    public static int INF = 1_000_000_000;
    
    public static ArrayList<Node>[] graph_A;
    public static ArrayList<Node>[] graph_B;
    public static ArrayList<Node>[] graph_C;
    
    public static int[] dist_A;
    public static int[] dist_B;
    public static int[] dist_C;
    
    public static int[] path_A;
    public static int[] path_B;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        graph_A = new ArrayList[N + 1];
        graph_B = new ArrayList[N + 1];
        graph_C = new ArrayList[N + 1];
        
        dist_A = new int[N + 1];
        dist_B = new int[N + 1];
        dist_C = new int[N + 1];
        
        path_A = new int[N + 1];
        path_B = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph_A[i] = new ArrayList<Node>();
            graph_B[i] = new ArrayList<Node>();
            graph_C[i] = new ArrayList<Node>();
            
            dist_A[i] = INF;
            dist_B[i] = INF;
            dist_C[i] = INF;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            
            graph_A[b].add(new Node(a, c1));
            graph_B[b].add(new Node(a, c2));
            graph_C[a].add(new Node(b, 0));
        }
        
        dijkstra(N, graph_A, dist_A, path_A);
        dijkstra(N, graph_B, dist_B, path_B);
        
        for (int i = 1; i <= N; i++) {
            
            int next_A = path_A[i];
            int next_B = path_B[i];
            
            for (Node node : graph_C[i]) {
                int cnt = 0;
                
                if (node.to != next_A) cnt++;
                if (node.to != next_B) cnt++;
                
                node.cost = cnt;
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(1, 0));
        dist_C[1] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.cost > dist_C[now.to]) continue;
            
            for (Node next : graph_C[now.to]) {
                int newCost = now.cost + next.cost;
                
                if (newCost < dist_C[next.to]) {
                    dist_C[next.to] = newCost;
                    pq.add(new Node(next.to, newCost));
                }
            }
        }
        
        System.out.println(dist_C[N]);
    }
    
    public static void dijkstra(int start, ArrayList<Node>[] graph, int[] dist, int[] path) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.cost > dist[now.to]) continue;
            
            for (Node next : graph[now.to]) {
                int newCost = now.cost + next.cost;
                
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.add(new Node(next.to, newCost));
                    path[next.to] = now.to;
                }
            }
        }
    }
}