import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        // 노드의 이름을 저장
        ArrayList<String> name = new ArrayList<String>();
        // 노드의 간선 정보를 저장
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        // 노드의 차수를 저장 
        HashMap<String, Integer> indegree = new HashMap<String, Integer>();
        // 노드의 자식을 저장 
        HashMap<String, ArrayList<String>> child = new HashMap<String, ArrayList<String>>();
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            String s = st.nextToken();
            
            name.add(s);
            graph.put(s, new ArrayList<String>());
            indegree.put(s, 0);
            child.put(s, new ArrayList<String>());
        }
        
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            String a = st.nextToken();
            String b = st.nextToken();
            
            graph.get(b).add(a);
            indegree.put(a, indegree.get(a) + 1);
        }
        
        for (String s : graph.keySet()) {
            Collections.sort(graph.get(s));
        }
        
        
        Queue<String> q = new ArrayDeque<String>();
        
        ArrayList<String> root = new ArrayList<String>();
        
        for (String s : indegree.keySet()) {
            if (indegree.get(s) == 0) {
                q.add(s);
                root.add(s);
            }
        }
        
        while (!q.isEmpty()) {
            String node = q.poll();
            
            for (String next : graph.get(node)) {
                indegree.put(next, indegree.get(next) - 1);
                
                if (indegree.get(next) == 0) {
                    q.add(next);
                    child.get(node).add(next);
                }
            }
        }
        
        Collections.sort(root);
        Collections.sort(name);
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(root.size()).append("\n");
        
        for (String s : root) {
            sb.append(s).append(" ");
        }
        
        sb.append("\n");
        
        for (String s : name) {
            sb.append(s).append(" ").append(child.get(s).size()).append(" ");
            
            for (String c : child.get(s)) {
                sb.append(c).append(" ");
            }
            
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}