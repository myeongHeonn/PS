import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int b = sc.nextInt();
        int[] isNumbers = new int[n + 1];
        for (int i = 1; i <= n; i++) isNumbers[i] = 1;

        for (int i = 0; i < b; i++) {
            isNumbers[sc.nextInt()] = 0;
        }

        int[] p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] + isNumbers[i];
        }

        int max = 0;
        for (int i = 1; i <= n - k + 1; i++) {
            max = Math.max(max, p[i + k - 1] - p[i - 1]);
        }

        System.out.println(k - max);
    }
}