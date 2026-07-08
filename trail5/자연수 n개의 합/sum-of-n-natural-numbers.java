import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        
        long left = 1;
        long right = (long) Math.sqrt(2 * S);
        long max_N = 0;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if ((mid * (mid + 1) / 2) <= S) {
                left = mid + 1;
                max_N = Math.max(max_N, mid);
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(max_N);
    }
}
