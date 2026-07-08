import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        arr[N] = 1_000_000_001;
        
        Arrays.sort(arr);
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            
            int lb = lower_bound(arr, L);
            int ub = upper_bound(arr, R);
            
            sb.append(ub - lb).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static int lower_bound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 2;
        int min_idx = arr.length - 1;
        
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
        int right = arr.length - 2;
        int min_idx = arr.length - 1;
        
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