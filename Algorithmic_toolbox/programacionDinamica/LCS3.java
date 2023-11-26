import java.util.Stack;

public class LCS3 {
    public static void main(String[] args){
        int[] a = {8,3,2,1,7};
        int[] b = {8,2,1,3,8,10,7};
        int[] c = {6,8,3,1,4,7};

        int[][][] LCS = getLCS(a, b, c);
        printLCS(LCS, a, b, c);
    }

    private static int[][][] getLCS(int[] a, int[] b, int[] c){
        /*
            Returns the dp (dynamic programming) array used to get
            the longest common subsequence
         */
        int[][][] dp = new int[a.length+1][b.length+1][c.length+1];

        for (int i = 0; i < a.length+1; i++){
            for (int j = 0; j < b.length+1; j++){
                for (int k = 0; k < c.length+1; k++){
                    if (i == 0){
                        dp[0][j][k] = 0;
                    }
                    else if (j == 0){
                        dp[i][0][k] = 0;
                    }
                    else if (k == 0){
                        dp[i][j][0] = 0;
                    }
                    else if (a[i-1] == b[j-1] && b[j-1] == c[k-1]){
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    }
                    else{
                        dp[i][j][k] = max(dp[i][j][k-1], dp[i][j-1][k], dp[i-1][j][k]);
                    }
                }
            }
        }


        return dp;
    }

    private static void printLCS(int[][][] dp, int[] a, int[] b, int[] c){
        /*
            Prints the Longest Common Subsequence
         */

        int i = a.length;
        int j = b.length;
        int k = c.length;
        Stack<Integer> commonSubsequence = new Stack<>();

        while (i > 0 && j > 0 && k > 0){
            int max = max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1]);

            if (a[i-1] == b[j-1] && b[j-1] == c[k-1]){
                commonSubsequence.push(a[i-1]);
                i--; j--; k--;
            }
            else if (max == dp[i-1][j][k]){
                i--;
            }
            else if (max == dp[i][j-1][k]){
                j--;
            }
            else{
                k--;
            }
        }

        System.out.println("LCS: " + dp[dp.length-1][dp[0].length-1][dp[0][0].length-1]);
        while(!commonSubsequence.isEmpty()){
            System.out.print(commonSubsequence.pop());
        } System.out.println();
    }

    private static int max(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
}