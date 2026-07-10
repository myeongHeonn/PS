import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            
            if (command.equals("push_front")) {
                int x = Integer.parseInt(st.nextToken());
                linkedList.addFirst(x);
            }
            else if (command.equals("push_back")) {
                int x = Integer.parseInt(st.nextToken());
                linkedList.addLast(x);
            }
            else if (command.equals("pop_front")) {
                int x = linkedList.pollFirst();
                sb.append(x).append("\n");
            }
            else if (command.equals("pop_back")) {
                int x = linkedList.pollLast();
                sb.append(x).append("\n");
            }
            else if (command.equals("size")) {
                int x = linkedList.size();
                sb.append(x).append("\n");
            }
            else if (command.equals("empty")) {
                boolean x = linkedList.isEmpty();
                sb.append(x ? 1 : 0).append("\n");
            }
            else if (command.equals("front")) {
                int x = linkedList.peekFirst();
                sb.append(x).append("\n");
            }
            else if (command.equals("back")) {
                int x = linkedList.peekLast();
                sb.append(x).append("\n");
            }
        }
        
        System.out.println(sb);
    }
}