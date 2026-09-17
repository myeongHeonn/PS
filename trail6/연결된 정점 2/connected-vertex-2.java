import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static int MAX_N = 100_000;
    
    public static int[] uf = new int[MAX_N + 1];
    public static int[] sz = new int[MAX_N + 1];
    
    public static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        
        if (X != Y) {
            uf[X] = Y;
            sz[Y] += sz[X];
        }
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
        
        for (int i = 1; i <= MAX_N; i++) {
            uf[i] = i;
            sz[i] = 1;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            union(a, b);
            sb.append(sz[find(a)]).append("\n");
        }
        
        System.out.println(sb);
    }
}