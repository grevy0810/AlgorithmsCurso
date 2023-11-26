public class CoinChange {
    public static void main(String[] args) {
        int change = 30;
        int[] coins = {1, 3, 4};

        int result = getMoneyChange(change, coins);
        System.out.println("Minimum number of coins needed: " + result);
    }

    private static int getMoneyChange(int change, int[] coins) {
        int[] dp = new int[change + 1];

        // Initialize dp[0] to 0 since no coins are needed to make change for 0 units.
        dp[0] = 0;

        // Fill the dp array using dynamic programming.
        for (int i = 1; i <= change; i++) {
            dp[i] = Integer.MAX_VALUE;

            // Consider each coin in the set.
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // The result is stored in dp[change].
        return dp[change];
    }
}
    