import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] A = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = sc.nextInt();
        }
        int[] B = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            B[i] = sc.nextInt();
        }
        // Please write your code here.

        int i = 0;
        boolean flag = true;

        for (int j = 1; j <= m; j++) {
            while (i <= n && A[i] != B[j]) {
                i++;
            }

            if (i > n) {
                flag = false;
                break;
            }

            i++;
        }

        System.out.println(flag ? "Yes" : "No");
    }
}