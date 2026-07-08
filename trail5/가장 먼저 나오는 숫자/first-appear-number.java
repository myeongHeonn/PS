import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            
            int lb = lower_bound(arr, x);
            int ub = upper_bound(arr, x);
            
            int result = (ub - lb) == 0 ? -1 : lb + 1;
            
            sb.append(result).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static int lower_bound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int min_idx = arr.length;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (arr[mid] >= target) {
                right = mid - 1;
                min_idx = Math.min(min_idx, mid);
            } else {
                left = mid + 1;
            }
        }
        
        return min_idx;
    }
    
    static int upper_bound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int min_idx = arr.length;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (arr[mid] > target) {
                right = mid - 1;
                min_idx = Math.min(min_idx, mid);
            } else {
                left = mid + 1;
            }
        }
        
        return min_idx;
    }

}
