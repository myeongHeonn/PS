import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        long M = Long.parseLong(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        int min = 100;
        int max = 0;
        
        for (long i = A; i <= B; i++) {
            int c = binary_search(i, M);
            min = Math.min(min, c);
            max = Math.max(max, c);
        }
        
        System.out.println(min + " " + max);
    }
    
    static int binary_search(long target, long end) {
        long left = 1;
        long right = end;
        int count = 0;
        
        while (left <= right) {
            count++;
            long mid = (left + right) / 2;
            
            if (mid == target) return count;
            
            if (mid > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return -1;
    }

}
