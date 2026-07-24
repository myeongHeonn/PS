import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] group = new int[n];
        
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int mask = 0;
            
            for (int j = 0; j < m; j++) {
                int idx = sc.nextInt();
                mask |= (1 << idx);
            }
            
            group[i] = mask;
        }
        
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (group[i] + group[j] == (group[i] | group[j])) {
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
}