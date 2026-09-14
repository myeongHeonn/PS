import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static int[] uf;
    
    public static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        
        uf[X] = Y;
    }
    
    public static int find(int x) {
        if(uf[x] == x) return x;
        
        uf[x] = find(uf[x]);
        return uf[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        uf = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            uf[i] = i;
        }
        
        int cnt = 0;
        
        for (int i = 1; i <= M; i++) {
            int k = Integer.parseInt(br.readLine());
            
            if (find(k) == 0) break;
            
            union(k, find(k) - 1);
            cnt++;

            if (i == N) break;
        }
        
        System.out.println(cnt);
    }
}