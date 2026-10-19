import java.util.Scanner;
public class Main {

    public static int MAX = 1_000_000;
    public static int[] points = new int[MAX + 1];
    public static int[] prefixSum = new int[MAX + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        for (int i = 0; i < n; i++) {
            points[sc.nextInt()] = 1;
        }

        prefixSum[0] = points[0];

        for (int i = 1; i <= MAX; i++) {
            prefixSum[i] = prefixSum[i - 1] + points[i];
        }

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            if (a == 0) {
                System.out.println(prefixSum[b]);
            }
            else {
                System.out.println(prefixSum[b] - prefixSum[a - 1]);
            }
        }
    }
}