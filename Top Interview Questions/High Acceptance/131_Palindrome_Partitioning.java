/*

LeetCode: 131. Palindrome Partitioning

Medium (Should be Hard)

Link: https://leetcode.com/problems/palindrome-partitioning/

Acceptance: 58.0

Topics: String, Dynamic Programming, Backtracking

*Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 
*/

class Solution {
    boolean[][] dp;
    List<List<String>> ans = new ArrayList();
    
    // Space O(n ^ 2):  for dp array; without dp O(n) for recursion stack
    // Time O(n * 2 ^ n): 2^n different paths can be obtained as at every character
    // either partition can be created or not (2 choices); 
    // and for every path substring operation would also require O(n)
    // Additionally, dp bottom up takes O(n^2) but in O(n * 2^n) + O(n ^ 2),
    // the dp time complexity gets dropped
    // without dp, time complexity would still be O(n * 2 ^ n)
    // hence dp in this problem is not improving the time complexity, but is just an optimisation
    //
    // More Details copied from LC comment

    // For each character in the string we have 2 choices to create new palindrom substrings,
    // one is to join with previous substring (for(...end++)) and another is to start a new palindrom substring
    // (dfs(..end+1..)). Thus in the worst case there are 2^N palindrom substrings.
    // Each substring will take O(N) time to check if it's palindrom and O(N) time to 
    // generate substring from start to end indexes.
    //
    // In total we have O(2^N * (N + N)) = O(2^N * 2N) = O(N*2^N)
    //
    // In the second approach we use dp to remove the check for palindrom,
    // but for each palindrom we still need O(N) time to generate substring from start to end indexes.
    // The complexity is still the same O(N*2^N)
    
    public List<List<String>> partition(String s) {
        if(s == null || s.length() == 0){
            return ans;
        }
        initPalindromeDp(s);
        backtrack(new ArrayList(), s, 0); 
        return ans;
    }
    
    private void initPalindromeDp(String s){
        int n = s.length();
        dp = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < n - i; j++) {
                int row = j;
                int col = i + j;
                
                dp[row][col] = s.charAt(row) == s.charAt(col);
                // for 1,2 length strings, just end characters need to be compared
                if(col-row>1){
                    dp[row][col] = dp[row][col] && dp[row+1][col-1];
                }
            }
        }
    }
    
    private void backtrack(List<String> path, String s, int start){
        if(start == s.length()){
            ans.add(new ArrayList(path));
            return;
        }
        for(int i = start; i < s.length(); i++){
            if(dp[start][i]){
                // create partition here
                path.add(s.substring(start, i + 1));
                backtrack(path, s, i + 1);
                path.remove(path.size() - 1);
            } 
        }
    }
}
