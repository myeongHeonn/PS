import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] graph = new int[N + 1][N + 1];
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[a][b] = c;
        }
        
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }
        
        dist[1] = 0;
        
        for (int i = 1; i <= N; i++) {
            int minIndex = -1;
            
            for (int j = 1; j <= N; j++) {
                if (visited[j]) continue;
                
                if (minIndex == -1 || dist[j] < dist[minIndex]) {
                    minIndex = j;
                }
            }
            
            visited[minIndex] = true;
            
            for (int j = 1; j <= N; j++) {
                if (graph[minIndex][j] == 0) continue;
                
                if (dist[j] > dist[minIndex] + graph[minIndex][j]) {
                    dist[j] = dist[minIndex] + graph[minIndex][j];
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 2; i <= N; i++) {
            int value = (dist[i] == INF) ? -1 : dist[i];
            sb.append(value).append("\n");
        }
        
        System.out.println(sb);
    }
}