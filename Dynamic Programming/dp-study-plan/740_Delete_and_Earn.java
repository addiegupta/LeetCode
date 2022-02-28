/*

LeetCode: 740. Delete and Earn

Medium

Link: https://leetcode.com/problems/delete-and-earn/

Topics: Dynamic Programming, Array, Hash Table

Acceptance: 55

You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.

 

Example 1:

Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
Example 2:

Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104

 */

class Solution {
    public int deleteAndEarn(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        //return oldTreeMapSolution(nums);
        return newLinearSolution(nums);
    }

    private int newLinearSolution(int[] nums){
        int n = 10001;
        int[] map = new int[n];
	// increase points for every occurence, based on these points we will pick which one to delete when traversing sorted order
        for(int num: nums){
            map[num] += num;
        }
	// points if last value is skipped, taken respectively
        int skip = 0;
        int take = 0;
	// complexity is due to constraint on largest possible input value
        for(int i = 0; i < n; i++){
	    // last one and this one both can be skipped hence using max
            int newSkip = Math.max(skip, take);
	    // this value can only be taken if previous value is not taken hence adding to skip
            int newTake = skip + map[i];
            take = newTake;
            skip = newSkip;
        }
        return Math.max(take, skip);
    }

    private int oldTreeMapSolution(int[] nums){
	// tree map required to obtain sorted order
        TreeMap<Integer, Integer> map = new TreeMap();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int prev1 = 0;
        int prev2 = 0;
        int prevValue = -1;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int count = entry.getValue();
            int curr;
            if(prevValue == num - 1){
                curr = Math.max(prev1, prev2 + (count * num));
            } else{
                curr = prev1 + (count * num);
            }
            prev2 = prev1;
            prev1 = curr;
            prevValue = num;
        }
        return prev1;
    }
}
