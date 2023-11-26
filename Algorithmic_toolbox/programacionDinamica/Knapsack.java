public class Knapsack {

    public static void main(String[] args) {
        int maxWeight = 10;
        int[] weights = {6, 3, 4, 2};

        int result = optimalWeight(maxWeight, weights);
        System.out.println("Peso máximo de oro que se puede obtener: " + result);
    }

    public static int optimalWeight(int maxWeight, int[] weights) {
        int[][] dp = new int[weights.length + 1][maxWeight + 1];

        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                dp[i][j] = dp[i - 1][j];

                if (weights[i - 1] <= j) {
                    int include_it = dp[i - 1][j - weights[i - 1]] + weights[i - 1];
                    dp[i][j] = Math.max(dp[i][j], include_it);
                }
            }
        }

        return dp[weights.length][maxWeight];
    }
}
