import java.util.*;

class Node implements Comparable<Node> {
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
    
    public static int N;
    public static int M;
    public static int K;
    public static ArrayList<Node>[] graph;
    public static int[] dist;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = 1_000_000_000;
        }
        
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        dijkstra(K);
        
        for (int i = 1; i <= N; i++) {
            System.out.println((dist[i] == 1_000_000_000) ? -1 : dist[i]);
        }
    }
    
    public static void dijkstra(int start) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.cost > dist[now.to]) continue;
            
            for (Node next : graph[now.to]) {
                int newCost = dist[now.to] + next.cost;
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.offer(next);
                }
            }
        }
        
    }
}