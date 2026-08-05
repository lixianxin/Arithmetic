package pointsCovered;


import java.util.Arrays;
import java.util.Scanner;

public class LC1140 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] piles = new int[n];
        for (int i = 0; i < n; i++) {
            piles[i] = scanner.nextInt();
        }
        int maxScore = stoneGameII(piles);
        System.out.println(maxScore);
    }

    public static int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        int[][] memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 1, piles, suffix, memo);
    }

    private static int dfs(int i, int M, int[] piles, int[] suffix, int[][] memo) {
        int n = piles.length;
        if (i == n) {
            return 0;
        }
        if (2 * M >= n - i) {
            return suffix[i];
        }
        if (memo[i][M] != -1) {
            return memo[i][M];
        }
        int minOpponent = Integer.MAX_VALUE;
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {
            int nextM = Math.max(M, X);

            int opponent = dfs(i + X, nextM, piles, suffix, memo);
            if (opponent < minOpponent) {
                minOpponent = opponent;
            }
        }
        int result = suffix[i] - minOpponent;
        memo[i][M] = result;
        return result;
    }
}