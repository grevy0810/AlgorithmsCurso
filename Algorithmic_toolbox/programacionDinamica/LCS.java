import java.util.Stack;

public class LCS {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 1, 1};
        int[] b = {2, 4, 1, 1, 1, 3};

        int[][] dp = getLCS(a, b);
        printLCS(dp, a, b);
    }

    public static int[][] getLCS(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp;
    }

    public static void printLCS(int[][] dp, int[] a, int[] b) {
        int m = a.length;
        int n = b.length;

        int lcsLength = dp[m][n];
        System.out.println("Longitud de la Subsecuencia Común Más Larga (LCS): " + lcsLength);

        Stack<Integer> commonSubsequence = new Stack<>();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                commonSubsequence.push(a[i - 1]);
                i--;
                j--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }

        System.out.print("LCS: ");
        while (!commonSubsequence.isEmpty()) {
            System.out.print(commonSubsequence.pop() + " ");
        }
    }
}
