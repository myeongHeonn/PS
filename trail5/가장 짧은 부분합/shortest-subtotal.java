import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        int j = 0;
        int sumVal = 0;
        int ans = 1_000_000_000;

        for (int i = 1; i <= n; i++) {
            if (sumVal >= s) {
                ans = Math.min(ans, j - i + 1);
            }
            else {
                while (j + 1 <= n) {
                    sumVal += arr[j + 1];
                    j++;

                    if (sumVal >= s) {
                        ans = Math.min(ans, j - i + 1);
                        break;
                    }
                }
            }

            sumVal -= arr[i];
        }

        System.out.println(ans == 1_000_000_000 ? -1 : ans);
    }
}