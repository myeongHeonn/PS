import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
    int a;
    int b;
    int cost;
    
    public Edge (int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {
    
    public static ArrayList<Edge> edges;
    
    public static int[] uf;
    
    public static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        
        uf[X] = Y;
    }
    
    public static int find(int x) {
        if (uf[x] == x) return x;
        
        uf[x] = find(uf[x]);
        return uf[x];
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        edges = new ArrayList<Edge>();
        
        uf = new int[N + 1];
        for (int i = 1; i <= N; i++) uf[i] = i;
        
        int totalCost = 0;
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            edges.add(new Edge(a, b, c));
            totalCost += c;
        }
        
        Collections.sort(edges);
        
        int mstCost = 0;
        
        for (Edge edge : edges) {
            int a = edge.a;
            int b = edge.b;
            int cost = edge.cost;
            
            if (find(a) == find(b)) continue;
            
            union(a, b);
            mstCost += cost;
        }
        
        System.out.println(totalCost - mstCost);
    }
}