import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        
        int[] indegree = new int[n + 1];
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < m1; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            graph[a].add(b);
            indegree[b]++;
        }
        
        for (int i = 0; i < m2; i++) {
            st = new StringTokenizer(br.readLine());
        }
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        
        int cnt = 0;
        
        while (!q.isEmpty()) {
            int x = q.poll();
            cnt++;
            
            for (int next : graph[x]) {
                indegree[next]--;
                
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        
        System.out.println(cnt == n ? "Yes" : "No");

    }
}
