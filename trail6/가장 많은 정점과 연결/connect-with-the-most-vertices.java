import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int no;
    int cost;
    
    public Node (int no, int cost) {
        this.no = no;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class Main {
    
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        List<Node> list = new ArrayList<Node>();
        
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(st.nextToken());
            list.add(new Node(i, value));
        }
        
        Collections.sort(list);
        
        uf = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            uf[i] = i;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            union(a, b);
        }
        
        int ans = 0;
        
        for (int i = 1; i < N; i++) {
            int min_root = find(list.get(0).no);
            int min_cost = list.get(0).cost;
            int root = find(list.get(i).no);
            int cost = list.get(i).cost;
            
            if (min_root != root) {
                union(list.get(0).no, list.get(i).no);
                ans += (min_cost + cost);
            }
        }
        
        System.out.println((ans > K) ? "NO" : ans);
    }
}