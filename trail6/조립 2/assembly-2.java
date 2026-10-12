import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] indegree = new int[N + 1];
        
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
         for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < k; j++) {
                int b = Integer.parseInt(st.nextToken());
                graph[b].add(a);
                indegree[a]++;
            }
        }
         
         int L = Integer.parseInt(br.readLine());
         st = new StringTokenizer(br.readLine());
         
         Queue<Integer> q = new ArrayDeque<Integer>();
         boolean[] visited = new boolean[N + 1];
         
         for (int i = 0; i < L; i++) {
             int slice = Integer.parseInt(st.nextToken());
             q.add(slice);
             visited[slice] = true;
         }
         
         ArrayList<Integer> answerList = new ArrayList<Integer>();
         
         while (!q.isEmpty()) {
             int x = q.poll();
             answerList.add(x);
             
             for (int next : graph[x]) {
                 indegree[next]--;
                 
                 if (indegree[next] == 0 && !visited[next]) {
                     q.add(next);
                     visited[next] = true;
                 }
             }
         }
         
         Collections.sort(answerList);
         
         StringBuilder sb = new StringBuilder();
         
         sb.append(answerList.size()).append("\n");
         
         for (int x : answerList) {
             sb.append(x).append(" ");
         }
         
         System.out.println(sb);
    }
}
