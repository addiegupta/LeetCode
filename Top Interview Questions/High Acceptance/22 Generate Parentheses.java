/*

LeetCode: 22. Generate Parentheses 

Medium

Link: https://leetcode.com/problems/generate-parentheses/

Acceptance: 54.9

Topics: Backtracking, String 

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/

class Solution {
    
    private void recur(List<String> ans,String curr,int n,int open){
        // No more open brackets can be inserted
        if(n==0){
            // Close any unclosed brackets
            if(open>0){
                for(int i=0;i<open;i++)curr+=')';
            }
            // Add current permutation and add to answer
            ans.add(curr);
            return;
        }
        // Recur for adding open and closed brackets
        recur(ans,curr+'(',n-1,open+1);
        if(open>0)recur(ans,curr+')',n,open-1);
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        recur(ans, "",n,0);
        return ans;
    }
}