import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Node implements Comparable<Node>{
    int to;
    int C;
    int cost;
    
    public Node(int to, int C, int cost) {
        this.to = to;
        this.C = C;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class Main {
    
    public static int INF = 1_000_000_000;
    
    public static ArrayList<Node>[] graph;
    public static int dist[];
    public static TreeSet<Integer> set = new TreeSet<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        
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
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            graph[a].add(new Node(b, C, L));
            graph[b].add(new Node(a, C, L));
            
            set.add(C);
        }
        
        int min = INF;
        
        while (!set.isEmpty()) {
            int limit = set.pollFirst();
            
            for (int i = 1; i <= N; i++) dist[i] = INF;
            int min_C = dijkstra(limit);

            if (dist[N] != INF) {
                min = Math.min(min, dist[N] + X / min_C);
            }
        }
        
        System.out.println(min);
    }
    
    public static int dijkstra(int limit) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(1, limit, 0));
        dist[1] = 0;
        int min_C = INF;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.cost > dist[now.to]) continue;
            
            for (Node next : graph[now.to]) {
                if (next.C < limit) continue;
                
                int newCost = now.cost + next.cost;
                
                if (newCost < dist[next.to]) {
                    min_C = Math.min(min_C, next.C);
                    dist[next.to] = newCost;
                    pq.add(new Node(next.to, next.C, newCost));
                }
            }
        }
        
        return min_C;
    }
}