import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        uf = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            uf[i] = i;
        }
        
        int cnt = n;
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            int A = find(a);
            int B = find(b);
            
            while (A != B) {
                cnt--;
                union(A, B);
                
                A = find(A + 1);
            }
            
            sb.append(cnt).append("\n");
        }
        
        System.out.println(sb);
    }
}
