import java.util.ArrayList;

public class MaximumNumberofPrizes {
    public static void main(String[] args){
        int candies = 15;
        ArrayList<Integer> prizes = getNumberPrizes(candies);

        System.out.println("Number of prizes: " + prizes.size());
        System.out.println("List of prizes: " + prizes);
    }

    public static ArrayList<Integer> getNumberPrizes(int candies){
        ArrayList<Integer> result = new ArrayList<>();

        // Remove from 1, then 2, 3...  until there are no more candies.
        int prize = 1;
        while (true){
            if (candies - prize == 0){
                result.add(prize);
                break;
            }

            // If the prize is n and the available candies is n-x.
            // Then there is not enough candies, so add those remaining candies to the
            // First winner
            else if (candies - prize < 0){
                int last = result.get(result.size()-1);
                result.set(result.size()-1, last+candies);
                break;
            }

            result.add(prize);
            candies -= prize;
            prize ++;
        }

        return result;
    }
}

/*
    Time complexity:
        The sum from 1 to k is k(k+1)/2. We are doing that but with subtractions.
        The idea is that k(k+1)/2 <= n, with n being the number of candies.
        So, we could find up to k(k+1)/2 prices, and k(k+1)/2 = (k^2+k)/2.
        Then it is O(n^2)
 */