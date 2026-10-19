import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] grid_A = new int[n + 1][m + 1];
        int[][] grid_B = new int[n + 1][m + 1];
        int[][] grid_C = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            
            for (int j = 1; j <= m; j++) {
                char command = s.charAt(j - 1);
                
                if (command == 'a') {
                    grid_A[i][j] = 1;
                }
                else if (command == 'b') {
                    grid_B[i][j] = 1;
                }
                else if (command == 'c') {
                    grid_C[i][j] = 1;
                }
            }
        }
        
        int[][] prefixSum_A = new int[n + 1][m + 1];
        int[][] prefixSum_B = new int[n + 1][m + 1];
        int[][] prefixSum_C = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefixSum_A[i][j] = prefixSum_A[i - 1][j]
                                    + prefixSum_A[i][j - 1]
                                    - prefixSum_A[i - 1][j - 1]
                                    + grid_A[i][j];
                
                prefixSum_B[i][j] = prefixSum_B[i - 1][j]
                        + prefixSum_B[i][j - 1]
                        - prefixSum_B[i - 1][j - 1]
                        + grid_B[i][j];
                
                prefixSum_C[i][j] = prefixSum_C[i - 1][j]
                        + prefixSum_C[i][j - 1]
                        - prefixSum_C[i - 1][j - 1]
                        + grid_C[i][j];
            }
        }
        
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            
            int cnt_A = prefixSum_A[r2][c2]
                        - prefixSum_A[r2][c1 - 1]
                        - prefixSum_A[r1 - 1][c2]
                        + prefixSum_A[r1 - 1][c1 - 1];
            
            int cnt_B = prefixSum_B[r2][c2]
                    - prefixSum_B[r2][c1 - 1]
                    - prefixSum_B[r1 - 1][c2]
                    + prefixSum_B[r1 - 1][c1 - 1];
            
            int cnt_C = prefixSum_C[r2][c2]
                    - prefixSum_C[r2][c1 - 1]
                    - prefixSum_C[r1 - 1][c2]
                    + prefixSum_C[r1 - 1][c1 - 1];
            
            System.out.println(cnt_A + " " + cnt_B + " " + cnt_C);
        }
    }
}