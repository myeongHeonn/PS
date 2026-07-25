import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] graph = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            
            graph[u][v] = w;
        }
        // Please write your code here.
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) dist[i] = 1_000_000_000;
        dist[1] = 0;
        
        for (int i = 1; i <= n; i++) {
            int minIndex = -1;
            
            for (int j = 1; j <= n; j++) {
                if (visited[j]) continue;
                
                if (minIndex == -1 || dist[minIndex] > dist[j]) {
                    minIndex = j;
                }
            }
            
            visited[minIndex] = true;
            
            for (int j = 1; j <= n; j++) {
                if (graph[minIndex][j] == 0) continue;
                
                dist[j] = Math.min(dist[j], dist[minIndex] + graph[minIndex][j]);
            }
        }
        
        for (int i = 2; i <= n; i++) {
            System.out.println(dist[i] == 1_000_000_000 ? -1 : dist[i]);
        }
    }
}