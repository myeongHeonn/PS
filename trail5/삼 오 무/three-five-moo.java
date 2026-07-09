import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    public static int MAX = Integer.MAX_VALUE;
    public static long N;
    // x 가 몇 번째 수인지 확인하는 함수
    public static long func(long x) {
        return x - ((x / 3) + (x / 5) - (x / 15));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        
        long left = 1;
        long right = MAX;
        long ans = MAX;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (func(mid) >= N) {
                right = mid - 1;
                ans = Math.min(ans, mid);
            }
            else {
                left = mid + 1;
            }
        }
        
        System.out.println(ans);
    }
}
