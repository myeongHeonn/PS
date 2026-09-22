import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        int[][] grid = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int ans = 0;
        
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= N - 3; j++) {
                
                int cnt = 0;
                
                for (int ii = i; ii < i + 3; ii++) {
                    for (int jj = j; jj < j + 3; jj++) {
                        if (grid[ii][jj] == 1) cnt++;
                    }
                }
                
                ans = Math.max(ans, cnt);
            }
        }
        
        System.out.println(ans);
    }
}
