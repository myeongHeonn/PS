import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair>{
    int a;
    int b;
    int cost;
    
    public Pair (int a, int b, int cost) {
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
        
        uf = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            uf[i] = i;
        }
        
        Pair[] pairs = new Pair[M];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            pairs[i] = new Pair(a, b, c);
        }
        
        Arrays.sort(pairs);
        
        int mst = 0;
        
        for (Pair pair : pairs) {
            
            if (find(pair.a) == find(pair.b)) continue;
            
            union(pair.a, pair.b);
            mst += pair.cost;
        }
        
        System.out.println(mst);
    }
}
