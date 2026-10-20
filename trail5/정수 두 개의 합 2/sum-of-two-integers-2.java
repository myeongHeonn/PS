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
        
        int j = n;
        int cnt = 0;

        for (int i = 1; i < n; i++) {
            while (j != 1 && arr[i] + arr[j] > k) {
                j--;
            }

            if (j <= i) break;

            cnt += (j - i);
        }

        System.out.println(cnt);
    }
}