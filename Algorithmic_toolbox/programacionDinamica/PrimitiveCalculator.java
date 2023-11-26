import java.util.Stack;

public class PrimitiveCalculator {
    public static void main(String[] args){
        int num = 96234;
        int[] divisors = {2,3};
        int[] operations = getOperations(num, divisors);
        printOperations(operations, divisors);
    }

    private static int[] getOperations(int num, int[] divisors){
        /*
            Returns an array with the minimum amount of operations
            from 0 to the given number
         */
        int[] dp = new int[num+1];
        dp[1] = 0;

        for (int i = 2; i < num+1; i++){
            dp[i] = Integer.MAX_VALUE;
            int operations;
            for (int divisor: divisors){
                if (i % divisor == 0){
                    operations = dp[i/divisor] + 1;
                    if (operations < dp[i]) dp[i] = operations;
                }
            }
            operations = dp[i-1] + 1;
            if (operations < dp[i]) dp[i] = operations;
        }

        return dp;
    }

    private static void printOperations(int[] dp, int[] divisors) {
        /*
            Prints the minimum amount of operations needed and the
            sequence of numbers that will lead to the given number
         */
        Stack<Integer> nums = new Stack<>();

        int i = dp.length-1;
        nums.push(dp.length-1);
        while (i > 1){
            int minOperations = Integer.MAX_VALUE, operations = Integer.MAX_VALUE;
            int num = 0;
            for (int divisor : divisors) {
                if (i % divisor == 0) {
                    operations = dp[i / divisor];
                }
                if (operations < minOperations) {
                    minOperations = operations;
                    num = i / divisor;
                }
            }
            operations = dp[i - 1];
            if (operations < minOperations) {
                num = i - 1;
            }
            nums.push(num);
            i = num;
        }

        System.out.println(dp[dp.length-1]);

        while(!nums.isEmpty()){
            System.out.print(nums.pop() + " ");
        }
    }

}

/*
    Time complexity:
        Getting the min operations requires O(nm), being n the number given and m
        the number of possible operations. The same with getting the needed operations
        to get n. So, it would be O(nm + nm)
 */
