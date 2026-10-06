import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
    int a;
    int b;
    int dist;
    
    public Edge (int a, int b, int dist) {
        this.a = a;
        this.b = b;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Edge o) {
        return this.dist - o.dist;
    }
}

public class Main {

    public static int MAX = 1_000_000_000;
    public static int N;
    
    public static int[] dy = { -1, 1, 0, 0 };
    public static int[] dx = { 0, 0, -1, 1 };
    
    public static int[][] grid;
    public static HashMap<Integer, int[]> map;
    public static ArrayList<Edge> graph;
    
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
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        map = new HashMap<Integer, int[]>();
        graph = new ArrayList<Edge>();
        
        int num = 2;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                
                if (grid[i][j] == 1) {
                    map.put(1, new int[] { i, j });
                }
                else if (grid[i][j] == 2) {
                    grid[i][j] = num;
                    map.put(num++, new int[] { i, j });
                }
            }
        }
        
        int K = map.size();
        uf = new int[K + 1];
        for (int i = 1; i <= K; i++) uf[i] = i;
        
        for (int a : map.keySet()) {
            int y = map.get(a)[0];
            int x = map.get(a)[1];
            
            // 현재 기지를 기준으로 모든 기지까지 최단거리(BFS)를 구한다.
            boolean[][] visited = new boolean[N][N];
            Queue<int[]> q = new ArrayDeque<int[]>();
            
            q.add(new int[] { y, x, 0 });
            visited[y][x] = true;
            
            while (!q.isEmpty()) {
                int[] now = q.poll();
                int cy = now[0];
                int cx = now[1];
                int cdist = now[2];
                
                for (int i = 0; i < 4; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    int ndist = cdist + 1;
                    
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                    if (grid[ny][nx] == -1) continue;
                    if (visited[ny][nx]) continue;
                    
                    if (grid[ny][nx] >= 1) {
                        int b = grid[ny][nx];
                        graph.add(new Edge (a, b, ndist));
                    }
                    
                    visited[ny][nx] = true;
                    q.add(new int[] { ny, nx, ndist });
                }
            }
        }
        
        Collections.sort(graph);
        
        int mstCost = 0;
        int mstCount = 0;
        
        for (Edge edge : graph) {
            int a = edge.a;
            int b = edge.b;
            int dist = edge.dist;
            
            if (find(a) != find(b)) {
                union(a, b);
                mstCost += dist;
                mstCount++;
            }
        }
        
        System.out.println(mstCount == (K - 1) ? mstCost : -1);
    }
}