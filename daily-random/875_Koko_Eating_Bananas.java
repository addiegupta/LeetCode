/*

LeetCode: 875. Koko Eating Bananas

Medium

Link: https://leetcode.com/problems/koko-eating-bananas/

Topics: Array, Binary Search

Acceptance: 54

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23
 

Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109 

*/

// idea is to apply binary search on all possible values of k, to find optimal k
// k (min num of bananas per hour to eat to finish all piles)
// will lie between 1 and max(piles[i]) since at least 1 hour would be spent on 1 pile
// and there is no advantage in eating more bananas than max amount in 1 go
// hence apply binary search on 1....max to find best possible answer
// if hours taken is more than h, then not feasible, go right in search
// if hours taken is less than h, then speed is too high can be reduced, go left
// further optimisation: min can be increased from 1 to x where x is
// ceiling of (sum(piles[i]) / h) as at least that many bananas would have to be eaten per hour to solve
class Solution {
    
    public int minEatingSpeed(int[] piles, int h) {
        if(piles == null || piles.length == 0){
            return 0;
        }
        int sum = 0;
        int maxK = 0;
        for(int i = 0; i < piles.length ; i++){
            sum += piles[i];
            maxK = Math.max(maxK, piles[i]);
        }
        // + h -1 is to get ceiling
        int minK = (sum + h - 1)/h;
        
        // for large numbers, integer is overflowing, hence setting minK to 1
        // and ignoring the optimisation of trying to increase min at start
        if(minK <= 0){
            minK = 1;
        }
        
        while(minK <= maxK){
            int mid = minK + (maxK - minK)/2;
            int hours = hoursRequired(piles, mid);
            
            if(hours <= h){
                maxK = mid-1;
            } else{
                minK = mid + 1;
            }
        }
        return minK;
    }
    
    private int hoursRequired(int[] piles, int k){
        int hours = 0;
        for(int pile : piles){
            // + k -1 is to get ceiling of pile/k
            hours += (pile + k - 1)/k;
        }
        return hours;
    }
    
}
