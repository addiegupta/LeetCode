/*

LeetCode: 46. Permutations   

Link: https://leetcode.com/problems/permutations/

Acceptance: 55.2

Topics: Backtracking

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/


// Java
class Solution {
    
    // Backtracking method
    private void recur(List<List<Integer>> ans,List<Integer> curr,int nums[]){
        
        // Reached end of a permutation
        if(curr.size()==nums.length){

            // Create clone of list as Java will otherwise pass by reference
            ans.add(new ArrayList(curr));
            return;
        }
        // Create permutations
        for(int i=0;i<nums.length;i++) {
            if (curr.contains(nums[i])) continue;
            
            // Add this value to permutation
            curr.add(nums[i]);
            // Recur
            recur(ans, curr, nums);
            // Backtrack and remove
            curr.remove(curr.size()-1);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums==null)return ans;
        recur(ans,new ArrayList<Integer>(),nums);
        return ans;
    }
}

