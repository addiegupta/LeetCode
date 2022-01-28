/*

LeetCode: 605. Can Place Flowers

Easy

Link: https://leetcode.com/problems/can-place-flowers/

Topics: Array, Greedy

Acceptance: 32

You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.

 

Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 

Constraints:

1 <= flowerbed.length <= 2 * 104
flowerbed[i] is 0 or 1.
There are no two adjacent flowers in flowerbed.
0 <= n <= flowerbed.length

*/

class Solution {
    // Time O(n): iterate over entire array
    // Space O(1): only few fixed amount variables required
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed == null || flowerbed.length == 0){
            return false;
        }

	// no flowers to be inserted
        if(n == 0){
            return true;
        }

        int size = flowerbed.length;
        int count = 0;
        int i = 0;

        while(i < size){
	    // for first and last flowers, prev and next are not applicable respectively
            int prev = i == 0 ? 0 : flowerbed[i-1];
            int next = i == size - 1 ? 0 : flowerbed[i+1];
	    
	    // required condition of no adjacent flowers & current position empty
            if(prev == 0 && next == 0 && flowerbed[i] == 0){
                count++;
		// since flower can be planted here, next spot can definitely not have flower, hence skipping it
                i++;
            }
	    // all flowers can be planted
            if(count == n)return true;
            i++;
        }
        return false;
    }
}
