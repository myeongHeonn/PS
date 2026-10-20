import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        int[] countArray = new int[100001];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        int j = 0;
        int ans = 1;

        for (int i = 1; i <= n; i++) {
            while (j + 1 <= n && countArray[arr[j + 1]] != 1) {
                countArray[arr[j + 1]]++;
                j++;
            }

            ans = Math.max(ans, j - i + 1);

            countArray[arr[i]]--;
        }

        System.out.println(ans);
    }
}