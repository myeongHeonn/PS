import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Clue {
    int a;
    int b;
    
    public Clue (int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class Main {
    
    public static int n;
    public static int m;
    
    public static Clue[] clues;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        clues = new Clue[m];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            clues[i] = new Clue(a, b);
        }
        
        int left = 1;
        int right = m;
        
        int answer = -1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (!isCycle(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(answer == m ? "Consistent" : answer + 1);
    }
    // 처음 k 개의 단서만 썼을 때 모순(사이클)이 있는지 판별
    // O(N + M)
    public static boolean isCycle(int k) {
        int[] indegree = new int[n + 1];
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < k; i++) {
            Clue clue = clues[i];
            
            graph[clue.a].add(clue.b);
            indegree[clue.b]++;
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
        
        if (cnt == n) return false;
        else return true;
    }
}