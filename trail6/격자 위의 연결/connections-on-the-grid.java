import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
    int a;
    int b;
    int cost;

    public Pair(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pair o) {
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
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int NM = N * M;
        
        uf = new int[NM];
        
        for (int i = 0; i < NM; i++) uf[i] = i;
        
        ArrayList<Pair> edges = new ArrayList<Pair>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = i * M + 0;
            int b = i * M + 1;
            // M - 1번 (행 간 연결 간선)
            for (int j = 1; j < M ; j++) {
                int cost = Integer.parseInt(st.nextToken());
                
                edges.add(new Pair(a++, b++, cost));
            }
        }
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = i * M;
            int b = (i + 1) * M;
            // M번 (열 간 연결 간선)
            for (int j = 1; j <= M ; j++) {
                int cost = Integer.parseInt(st.nextToken());
                
                edges.add(new Pair(a++, b++, cost));
            }
        }
        
        Collections.sort(edges);
        
        int mstCost = 0;
        
        for (Pair pair : edges) {
            int a = pair.a;
            int b = pair.b;
            int cost = pair.cost;
            
            if (find(a) == find(b)) continue;
            
            union(a, b);
            mstCost += cost;
        }
        
        System.out.println(mstCost);
    }
}