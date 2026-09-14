import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static int INF = 1_000_000_000;
    public static int N;
    
    public static int[][] graph;
    public static int[] dist;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        graph = new int[N + 1][N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[a][b] = c;
            graph[b][a] = c;
        }
        
        dijkstra(N);
        
        int index = 1;
        
        visited = new boolean[N + 1];
        visited[1] = true;
        
        while (index != N) {
            
            for (int i = 1; i <= N; i++) {
                if (graph[index][i] == 0) continue;
                if (visited[i]) continue;
                
                if (dist[index] == dist[i] + graph[index][i]) {
                    graph[index][i] = 0;
                    graph[i][index] = 0;
                    visited[i] = true;
                    index = i;
                    break;
                }
            }
        }
        
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }
        
        dijkstra(1);
        
        System.out.println((dist[N] == INF) ? -1 : dist[N]);
    }
    
    public static void dijkstra(int start) {
        dist[start] = 0;
        
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
                
                dist[j] = Math.min(dist[j], dist[minIndex] + graph[minIndex][j]);
            }
        }
    }
}