import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int ans = 0;
        
        for (int i = 0; i < N; i++) {
            
            int prev = -1;
            int cnt = 1;
            int max = 1;
            
            for (int j = 0; j < N; j++) {
                int now = grid[i][j];
                
                if (prev == now) {
                    cnt++;
                    max = Math.max(max, cnt);
                }
                else {
                    prev = now;
                    cnt = 1;
                }
            }
            
            if (max >= M) ans++;
        }
        
        for (int i = 0; i < N; i++) {
            
            int prev = -1;
            int cnt = 1;
            int max = 1;
            
            for (int j = 0; j < N; j++) {
                int now = grid[j][i];
                
                if (prev == now) {
                    cnt++;
                    max = Math.max(max, cnt);
                }
                else {
                    prev = now;
                    cnt = 1;
                }
            }
            
            if (max >= M) ans++;
        }
        
        System.out.println(ans);
    }
}