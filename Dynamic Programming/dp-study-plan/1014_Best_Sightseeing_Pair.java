/*

LeetCode: 1014. Best Sightseeing Pair

Medium

Link: https://leetcode.com/problems/best-sightseeing-pair/

Topics: Dynamic Programming, Array

Acceptance: 57.9

You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.

The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

 

Example 1:

Input: values = [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
Example 2:

Input: values = [1,2]
Output: 2
 

Constraints:

2 <= values.length <= 5 * 104
1 <= values[i] <= 1000
 
*/

class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        if(values == null || values.length < 2){
            return 0;
        }
        int n = values.length;
        int max = 0;
	// value at every step is given score + its index to factor in the degree of closeness i.e. the distance bw 2 spots
	// as we go right, comparing the best left value with current index' value will give answer
        int bestLeft = 0 + values[0];
        for(int i = 1; i < n; i++){
            int pairValue = bestLeft + values[i] - i;
            max = Math.max(max, pairValue);
            bestLeft = Math.max(bestLeft, values[i] + i);
        }
        return max;
    }
}
