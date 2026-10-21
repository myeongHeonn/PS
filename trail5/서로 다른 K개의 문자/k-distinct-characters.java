import java.util.Scanner;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        // Please write your code here.
        int n = s.length();

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put((char) ('a' + i), 0);
        }

        int j = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            // 다음 문자를 넣어도 서로 다른 문자가 k개 이하일 때만 확장
            while (j < n) {
                char c = s.charAt(j);
                // 새 문자인데 이미 k개면 중단
                if (map.get(c) == 0 && count(map) == k) break;
                map.put(c, map.get(c) + 1);
                j++;
            }

            ans = Math.max(ans, j - i);

            char c = s.charAt(i);
            map.put(c, map.get(c) - 1);
        }

        System.out.println(ans);
    }

    // map을 순회하며 서로 다른 문자의 개수를 구하는 함수
    public static int count(HashMap<Character, Integer> map) {
        int cnt = 0;

        for (char c : map.keySet()) {
            if (map.get(c) != 0) cnt++;
        }

        return cnt;
    }
}