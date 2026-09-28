import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int a;
    int b;
    double cost;
    
    public Edge (int a, int b, double cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.cost, o.cost);
    }
}

public class Main {
    
    public static int[][] points;
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
        
        uf = new int[N + 1];
        for (int i = 1; i <= N; i++) uf[i] = i;
        
        points = new int[N + 1][2];
        edges = new ArrayList<Edge>();
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            points[i][0] = x;
            points[i][1] = y;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int point_1_x = points[i][0];
                int point_1_y = points[i][1];
                int point_2_x = points[j][0];
                int point_2_y = points[j][1];
                
                double cost = Math.sqrt(Math.pow(point_1_x - point_2_x, 2)
                                      + Math.pow(point_1_y - point_2_y, 2));
                
                edges.add(new Edge(i, j, cost));
            }
        }
        
        Collections.sort(edges);
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            union(a, b);
        }
        
        double mstCost = 0;
        
        for (Edge edge : edges) {
            int a = edge.a;
            int b = edge.b;
            double cost = edge.cost;
            
            if (find(a) == find(b)) continue;
            
            union(a, b);
            mstCost += cost;
        }
        
        System.out.printf("%.2f", mstCost);
    }

}
