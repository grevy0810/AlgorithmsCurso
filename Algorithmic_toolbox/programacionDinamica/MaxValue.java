public class MaxValue {
    public static void main(String[] args){
        String operation = "5-8+7*4-8+9";
        int max_value = maxValue(operation);
        System.out.println(max_value);
    }

    private static int maxValue(String operation){
        /*
            Returns the max value possible for the given operation.
            The value variates depending on the parenthesis that can be used
         */

        int[] values = getValues(operation);
        char[] operations = getOperations(operation);

        int[][] minArray = new int[values.length][values.length];
        int[][] maxArray = new int[values.length][values.length];

        // Fill the main diagonal
        for (int i = 0; i < values.length; i++){
            minArray[i][i] = values[i];
            maxArray[i][i] = values[i];
        }

        int j;
        int[] minMax;
        // Fill the matrices
        for (int s = 1; s < values.length; s ++){
            for (int i = 0; i < values.length - s; i++){
                j = i+s;
                minMax = getMinMax(minArray, maxArray, operations, i, j);
                minArray[i][j] = minMax[0];
                maxArray[i][j] = minMax[1];
            }
        }

        return maxArray[0][values.length-1];
    }

    private static int[] getMinMax(int[][] minArray, int[][] maxArray, char[] operations, int i, int j){
        /*
            Returns an array, where the first value is the min possible value for the
            given numbers an operations, and the second value is the same but with the
            max.
         */
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int a, b, c, d;
        for (int k = i; k < j; k ++){{
            a = getResult(minArray[i][k], minArray[k+1][j], operations[k]);
            b = getResult(minArray[i][k], maxArray[k+1][j], operations[k]);
            c = getResult(maxArray[i][k], maxArray[k+1][j], operations[k]);
            d = getResult(maxArray[i][k], minArray[k+1][j], operations[k]);

            min = Math.min(min, Math.min(Math.min(a, b), Math.min(c, d)));
            max = Math.max(max, Math.max(Math.max(a, b), Math.max(c, d)));
        }}

        return new int[] {min, max};
    }

    private static int[] getValues(String operation){
        /*
            Returns an array with all the values, used to fill the main
            diagonal
         */
        int[] values = new int[(int) Math.ceil(operation.length() / 2.0)];

        int index = 0;
        for (int i = 0; i < operation.length(); i++){
            if (i % 2 == 0) {
                values[index] = Integer.parseInt(String.valueOf(operation.charAt(i)));
                index ++;
            }
        }
        return values;
    }

    private static int getResult(int a, int b, char operation){
        /*
            Returns the result of the operations between 2 numbers
         */
        if (operation == '+'){
            return a + b;
        }
        if (operation == '-'){
            return a - b;
        }
        return a * b;
    }

    private static char[] getOperations(String operation){
        /*
            Returns an array with all the operations inside the
            big operation string
         */
        char[] operations = new char[operation.length() / 2];

        int index = 0;
        for (int i = 0; i < operation.length(); i++){
            if (i % 2 == 1){
                operations[index] = operation.charAt(i);
                index ++;
            }
        }
        return operations;
    }
}