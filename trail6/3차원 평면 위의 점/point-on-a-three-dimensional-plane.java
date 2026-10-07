import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Dot {
    int num;
    int x;
    int y;
    int z;
    
    public Dot (int num, int x, int y, int z) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        uf = new int[N + 1];
        for (int i = 1; i <= N; i++) uf[i] = i;
        
        ArrayList<Dot> graph_X = new ArrayList<Dot>();
        ArrayList<Dot> graph_Y = new ArrayList<Dot>();
        ArrayList<Dot> graph_Z = new ArrayList<Dot>();
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            
            Dot dot = new Dot(i, x, y, z);
            
            graph_X.add(dot);
            graph_Y.add(dot);
            graph_Z.add(dot);
        }
        
        Collections.sort(graph_X, (o1, o2) -> o1.x - o2.x);
        Collections.sort(graph_Y, (o1, o2) -> o1.y - o2.y);
        Collections.sort(graph_Z, (o1, o2) -> o1.z - o2.z);
        
        ArrayList<Edge> graph = new ArrayList<Edge>();
        
        for (int i = 0; i < N - 1; i++) {
            int a1 = graph_X.get(i + 1).num;
            int b1 = graph_X.get(i).num;
            int dist1 = graph_X.get(i + 1).x - graph_X.get(i).x;
            graph.add(new Edge(a1, b1, dist1));

            int a2 = graph_Y.get(i + 1).num;
            int b2 = graph_Y.get(i).num;
            int dist2 = graph_Y.get(i + 1).y - graph_Y.get(i).y;
            graph.add(new Edge(a2, b2, dist2));

            int a3 = graph_Z.get(i + 1).num;
            int b3 = graph_Z.get(i).num;
            int dist3 = graph_Z.get(i + 1).z - graph_Z.get(i).z;
            graph.add(new Edge(a3, b3, dist3));
        }
        
        Collections.sort(graph);
        
        long mstCost = 0;

        for (Edge edge : graph) {
            int a = edge.a;
            int b = edge.b;
            int cost = edge.cost;
            
            if (find(a) != find(b)) {
                union(a, b);
                mstCost += (long)cost;
            }
        }
        
        System.out.println(mstCost);
    }
}