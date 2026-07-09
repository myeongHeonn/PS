import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static int N, M;
    public static int[] arr;
    
    public static boolean isPossible(int n) {
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            count += (arr[i] / n);
        }
        
        return count >= M;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        int left = 1;
        int right = 100_000;
        int max = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (isPossible(mid)) {
                left = mid + 1;
                max = Math.max(max, mid);
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(max);
    }
}