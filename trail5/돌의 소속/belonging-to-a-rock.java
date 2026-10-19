import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] stones = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stones[i] = sc.nextInt();
        }

        int[] prefixSum_1 = new int[n + 1];
        int[] prefixSum_2 = new int[n + 1];
        int[] prefixSum_3 = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int stone_1 = stones[i] != 1 ? 0 : 1;
            int stone_2 = stones[i] != 2 ? 0 : 1;
            int stone_3 = stones[i] != 3 ? 0 : 1;

            prefixSum_1[i] = prefixSum_1[i - 1] + stone_1;
            prefixSum_2[i] = prefixSum_2[i - 1] + stone_2;
            prefixSum_3[i] = prefixSum_3[i - 1] + stone_3;
        }

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            int cnt_a = prefixSum_1[b] - prefixSum_1[a - 1];
            int cnt_b = prefixSum_2[b] - prefixSum_2[a - 1];
            int cnt_c = prefixSum_3[b] - prefixSum_3[a - 1];

            System.out.println(cnt_a + " " + cnt_b + " " + cnt_c);
        }
    }
}