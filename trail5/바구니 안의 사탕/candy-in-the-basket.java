import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static int MAX_R = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[MAX_R + 1];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            int candy = Integer.parseInt(st.nextToken());
            int location = Integer.parseInt(st.nextToken());
            
            arr[location] += candy;
        }
        
        int[] prefixSum = new int[MAX_R + 1];
        prefixSum[0] = arr[0];
        for (int i = 1; i <= MAX_R; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        
        k = (k >= 500_000) ? MAX_R : 2 * k;
        int ans = 0;
        
        for (int i = 0; i <= MAX_R - k; i++) {
            ans = Math.max(ans, prefixSum[i + k] - prefixSum[i] + arr[i]);
        }
        
        System.out.println(ans);
    }
}