import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static int N;
    public static int M;
    
    public static int[][] arr;
    
    public static int[][][] dy_dx = {
            {
                { 0, -1 }, { 0, 1 }
            }, 
            {
                { -1, 0 }, { 1, 0 }
            },
            {
                { 0, -1 }, { -1, 0 }
            },
            {
                { 0, -1 }, { 1, 0 }
            },
            {
                { -1, 0 }, { 0, 1 }
            },
            {
                { 1, 0 }, { 0, 1 }
            }
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int ans = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, tromino(i, j));
            }
        }
        
        System.out.println(ans);
    }
    
    public static int tromino(int y, int x) {
        
        int max = 0;
        
        for (int[][] block : dy_dx) {
            int y1 = y + block[0][0];
            int x1 = x + block[0][1];
            
            int y2 = y + block[1][0];
            int x2 = x + block[1][1];
            
            if (!checked(y1, x1) || !checked(y2, x2)) continue;
            
            max = Math.max(max, arr[y][x] + arr[y1][x1] + arr[y2][x2]);
        }
        
        return max;
    }
    
    public static boolean checked(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < M) return true;
        return false;
    }
}