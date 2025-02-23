import java.util.ArrayList;
import java.util.Arrays;

public class DividePirateBooty {
    public static void main(String[] args){
        int[] nums = {3, 6, 4, 1, 9, 6, 9, 1};
        if (!is_possible(nums)){
            System.out.println(0);
            return;
        }

        // Get the goal of each loot
        int goal = 0;
        for (int num: nums) goal += num;
        goal /= 3;

        // Initialize array with the lists for each subset
        ArrayList<Integer>[] subsets = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            subsets[i] = new ArrayList<Integer>();
        }

        // Check if it is possible or not
        if (can_split(nums, 0, subsets, goal)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }

    private static boolean is_possible(int[] nums){
        /*
            Returns true if the sum of the list is a divisible by 3
            and its length is greater than 2
         */
        if (nums.length < 3) {
            return false;
        }
        int sum = 0;
        for (int num: nums){
            sum += num;
        }

        return sum % 3 == 0;
    }

    private static boolean can_split(int[] nums, int index, ArrayList<Integer>[] subsets, int goal){
        /*
            Returns if the given list of numbers can be split into 3 equal parts. To do it,
            it uses backtracking
         */

        if (index == nums.length){
            if (sum(subsets[0]) == goal && sum(subsets[1]) == goal) {
                System.out.println(Arrays.toString(subsets));
                return true;
            }
            return false;
        }

        for (int i = 0; i < 3; i++){
            if (nums[index] + sum(subsets[i]) <= goal){
                subsets[i].add(nums[index]);
                if (can_split(nums, index+1, subsets, goal)){
                    return true;
                }
                subsets[i].remove(subsets[i].size()-1);
            }
        }
        return false;
    }

    private static int sum(ArrayList<Integer> values){
        /*
            Returns the sum of integers inside an array list
         */
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        return sum;
    }
}