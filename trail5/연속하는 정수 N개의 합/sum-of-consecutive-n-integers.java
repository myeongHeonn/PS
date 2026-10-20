import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int j = 0;
        int sumVal = 0;
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            while (j + 1 <= n && sumVal + arr[j + 1] <= m) {
                sumVal += arr[j + 1];
                j++;
            }

            if (sumVal == m) ans++;

            sumVal -= arr[i];
        }

        System.out.println(ans);
    }
}