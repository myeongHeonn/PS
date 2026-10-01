import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge {
    int a;
    int b;
    int type;
    
    public Edge (int a, int b, int type) {
        this.a = a;
        this.b = b;
        this.type = type;
    }
}

public class Main {
    
    public static int[] uf;
    public static ArrayList<Edge> edges;
    
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
        
        edges = new ArrayList<Edge>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            
            edges.add(new Edge(a, b, type));
        }
        
        Collections.sort(edges, (a, b) -> a.type - b.type);
        
        int max = 0;
        
        for (Edge edge : edges) {
            int a = edge.a;
            int b = edge.b;
            int type = edge.type;
            
            if (find(a) == find(b)) continue;
            
            union(a, b);
            
            if (type == 0) max++;
        }
        
        max *= max;
        
        
        for (int i = 1; i <= N; i++) uf[i] = i;
        
        Collections.sort(edges, (a, b) -> b.type - a.type);
        
        int min = 0;
        
        for (Edge edge : edges) {
            int a = edge.a;
            int b = edge.b;
            int type = edge.type;
            
            if (find(a) == find(b)) continue;
            
            union(a, b);
            
            if (type == 0) min++;
        }

        min *= min;
        
        
        System.out.println(max - min);
    }
}