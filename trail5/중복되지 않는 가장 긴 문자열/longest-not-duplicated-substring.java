import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        // Please write your code here.
        int[] countArr = new int[26];
        int n = s.length();

        int j = -1;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            while (j + 1 < n && countArr[(int) (s.charAt(j + 1) - 'a')] != 1) {
                countArr[(int) (s.charAt(j + 1) - 'a')]++;
                j++;
            }

            ans = Math.max(ans, j - i + 1);
            countArr[(int) (s.charAt(i) - 'a')]--;
        }

        System.out.println(ans);
    }
}