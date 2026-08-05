package pointsCovered;

import java.util.Scanner;

/**
 * Graze
 * 有 n 份草，两名玩家轮流取，每次必须取 4^k 份（k ≥ 0，即 1、4、16、64……），不能不取。
 * 谁取完最后一次（即取完后草数为0）谁获胜。给定初始 n，判断先手是否必胜。
 */
public class Graze {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        String winnerName = winner(target);
        System.out.println("WIN: " + winnerName);
        String wString = winnnerDp(target);
        System.out.println("WIN: " + wString);
    }

    public static String winner(int target) {
        if (target < 5) {
            return (target == 2 || target == 0) == true ? "后手" : "先手";
        }
        int base = 1;
        while (base <= target) {
            while (winner(target - base).equals("后手")) {
                return "先手";
            }
            if (base > target / 4) {
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winnnerDp(int target) {
        if (target == 0) {
            return "后手";
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = false;
        for (int i = 1; i <= target; i++) {
            boolean flag = false;
            int base = 1;
            while (base <= i) {
                if (!dp[i - base]) {
                    flag = true;
                    break;
                }
                base *= 4;
            }
            dp[i] = flag;
        }
        return dp[target] ? "先手" : "后手";
    }

    public static String win(int target) {
        if (target % 5 == 0 || target % 5 == 2) {
            return "先手";
        } else {
            return "后手";
        }
    }
}
