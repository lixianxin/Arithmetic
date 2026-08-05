package pointsCovered;

import java.util.Scanner;

/**
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * 最初，黑板上有一个数字 n 。在每个玩家的回合，玩家需要执行以下操作：
 * 选出任一整数 x，满足 0 < x < n 且 n % x == 0 。
 * 用 n - x 替换黑板上的数字 n 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * 只有在爱丽丝在游戏中取得胜利时才返回 true 。假设两个玩家都以最佳状态参与游戏。
 */
public class LC1025 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("爱丽丝先手开局获胜：" + divisorGame(n));
        System.out.println("爱丽丝先手开局获胜：" + divisor(n));
    }

    public static boolean divisorGame(int n) {
        if (n <= 1) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int base = 1;
            boolean flag = false;
            while (base <= n && (i % base) == 0) {
                if (!dp[i - base]) {
                    flag = true;
                    break;
                }
                base += 1;
            }
            dp[i] = flag;
        }
        return dp[n] ? true : false;
    }

    public static boolean divisor(int n) {
        //打表法
        return n % 2 == 0;
    }
}