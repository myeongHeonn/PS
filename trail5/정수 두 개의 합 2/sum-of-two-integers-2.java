import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n + 1];
        for(int i = 1; i <= n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        
        int cnt = 0;

        for (int i = 1; i < n; i++) {
            int left = i + 1;
            int right = n;
            int val = 0;

            while (left <= right) {
                
                int mid = (left + right) / 2;
                
                if (arr[i] + arr[mid] <= k) {
                    val = mid - i;
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }

            cnt += val;
        }

        System.out.println(cnt);
    }
}