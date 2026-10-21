import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        Map<Integer, Integer> countArray = new HashMap<Integer, Integer>();

        int j = 0;
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            while (j + 1 <= n && countArray.getOrDefault(arr[j + 1], 0) < k) {
                    countArray.put(arr[j + 1], countArray.getOrDefault(arr[j + 1], 0) + 1);
                    j++;
                }

            ans = Math.max(ans, j - i + 1);
            countArray.put(arr[i], countArray.get(arr[i]) - 1);    
            }

        System.out.println(ans);
    }
}