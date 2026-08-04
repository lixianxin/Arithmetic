package AppleBag;

import java.util.Arrays;
import java.util.Scanner;

public class MinAppleBag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        int bag = getMinAppleBag2(target);
        System.out.println("MaxBag: " + bag);

        int amount = scanner.nextInt();
        int n = scanner.nextInt();
        int[] coins = new int[n];

    }

    public static int getMinAppleBag(int target) {
        if (target < 6 || (target & 1) != 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = target / 8;
        int rest = target - 8 * bag8;
        while (bag8 > 0 && rest < 24) {
            bag6 = rest % 6 == 0 ? rest / 6 : -1;
            if (bag6 != -1) {
                break;
            }
            rest += 8;
            bag8 -= 1;

        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    // 打表法
    public static int getMinAppleBag2(int target) {
        if ((target & 1) != 0) {
            return -1;
        }
        if (target < 18) {
            return target == 0 ? 0
                    : (target == 6 || target == 8) ? 1 : (target == 12 || target == 14 || target == 16) ? 2 : -1;
        }
        return (target - 18) / 8 + 3;
    }

    /**
     * LeetCode322
     * 零钱兑换
     */

    public static int coinChange(int[] coins, int rest) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[][] dp = new int[coins.length][rest + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int result = process(coins, rest, coins.length - 1, dp);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static int process(int[] coins, int rest, int index, int[][] dp) {
        if (index < 0 && rest > 0) {
            return Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return 0;
        }
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        int use = Integer.MAX_VALUE;
        int no = process(coins, rest, index - 1, dp);
        if (rest >= coins[index]) {
            int yes = process(coins, rest - coins[index], index, dp);
            if (yes != Integer.MAX_VALUE) {
                use = 1 + yes;
            }
        }
        int result = Math.min(no, use);
        dp[index][rest] = result;
        return result;
    }

    public static int coinChangeDP(int[] coins, int amount) {
        if (coins == null || coins.length == 0)
            return -1;
        int n = coins.length;
        int INF = Integer.MAX_VALUE;
        int[][] dp = new int[n][amount + 1];

        for (int r = 0; r <= amount; r++) {
            dp[0][r] = (r % coins[0] == 0) ? r / coins[0] : INF;
        }

        for (int i = 1; i < n; i++) {
            for (int r = 0; r <= amount; r++) {
                int noUse = dp[i - 1][r];
                int use = INF;
                if (r >= coins[i] && dp[i][r - coins[i]] != INF) {
                    use = dp[i][r - coins[i]] + 1;
                }
                dp[i][r] = Math.min(noUse, use);
            }
        }
        return dp[n - 1][amount] == INF ? -1 : dp[n - 1][amount];
    }

    public static int coinChangeOne(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int r = coin; r <= amount; r++) {
                dp[r] = Math.min(dp[r], dp[r - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
