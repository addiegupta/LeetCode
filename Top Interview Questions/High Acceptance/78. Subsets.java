/*

LeetCode: 78. Subsets

Medium

Link: https://leetcode.com/problems/subsets/

Topics: Array,Backtracking, Bit Manipulation

Acceptance: 52.8

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

*/
class Solution {
   
    private void recur(List<List<Integer>> ans,List<Integer> curr,int[] nums,int i){
        // Reached end of array
        if(i==nums.length){
            ans.add(new ArrayList<>(curr));
            return;
        }
        // Recur for 2 cases: adding this element and not adding this element
        curr.add(nums[i]);
        recur(ans,curr,nums,i+1);
        curr.remove(curr.size()-1);
        recur(ans,curr,nums,i+1);
    }
    private void recurWithForLoop(List<List<Integer>> ans,List<Integer> curr,int[] nums,int start){
        // Curr subset is added to ans at start of method
        ans.add(new ArrayList<>(curr));
        for(int i = start; i < nums.length; i++){
            // Add current element to subset and recursively call
            // to add next elements
            curr.add(nums[i]);
            recurWithForLoop(ans,curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        recur(ans,curr,nums,0);
        recurWithForLoop(ans,curr,nums,0);
        return ans;
    }
}     