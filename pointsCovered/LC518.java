package pointsCovered;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * LC518
 * 一个coins数组代表拥有的纸币面值，amount代表目标值，返回能凑出amount面值的所有方法
 */
public class LC518 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        int n = scanner.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }
        int result = coinChange(coins, amount);
        System.out.println("totol: " + result);
    }

    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }

        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int r = 0; r <= amount; r++) {

                dp[i][r] = dp[i - 1][r];

                if (r >= coins[i - 1]) {
                    dp[i][r] += dp[i][r - coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }

        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for (int coin : coins) {

            for (int r = coin; r <= amount; r++) {
                dp[r] += dp[r - coin];
            }
        }
        return dp[amount];
    }
}
