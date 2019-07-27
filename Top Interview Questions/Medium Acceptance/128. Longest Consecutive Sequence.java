/*

LeetCode: 128. Longest Consecutive Sequence

Hard

Link: https://leetcode.com/problems/longest-consecutive-sequence/

Topics: Array

Acceptance: 42.2

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

*/

class Solution {
    public int longestConsecutive(int[] nums) {
       
        // return HashSetLeetCodeSolution(nums);
        return HashMapStoreLengthSolution(nums);
    }
    
    private int HashMapStoreLengthSolution(int[] nums){
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int ans = 0,left,right;
        int sum;
        for(int num:nums){
            // do not process duplicates
            if(!map.containsKey(num)){
                
                left = map.getOrDefault(num-1,0);
                right = map.getOrDefault(num+1,0);
                
                //length of the sequence num is present in
                sum = left + right +1;
                map.put(num,sum);
                ans = Math.max(ans,sum);
                
                // add sum values to boundary keys in map
                map.put(num-left,sum);
                map.put(num+right,sum);
            }
        }
        return ans;
    }

    private int HashSetLeetCodeSolution(int[] nums){
        
        Set<Integer> set = new HashSet<Integer>();
        
        // Add all values to set
        for(int num:nums)set.add(num);
        int ans=0;
        int curr,streak;
        for(int num:nums){
            // Start counting at smallest value of the desired sequence
            if(!set.contains(num-1)){
                curr = num;
                streak = 1;
                // Increment while sequence values are present in set
                while(set.contains(curr+1)){
                    curr++;
                    streak++;
                }
                ans = Math.max(ans,streak);
            }
        }
        return ans;
    }
}