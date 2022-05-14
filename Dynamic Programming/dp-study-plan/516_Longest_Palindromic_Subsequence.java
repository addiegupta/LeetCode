/*

LeetCode: 516. Longest Palindromic Subsequence

Medium

Link: https://leetcode.com/problems/longest-palindromic-subsequence/

Topics: Dynamic Programming, String

Acceptance: 59.0

 Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
 

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.

 */

class Solution {
    // Can also be done by using Longest Common Subsequence on s & reverse(s)
    //Time: O (n ^ 2)
    // Space: O(n ^ 2)
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - i; j++){
                // Diagonal traversal
                int r = j;
                int c = j + i;
                // both letters can be added to the final palindromic subsequence
                // find longest palindromic subseq between these 2 chars so that they can be appended to it
                if(s.charAt(r) == s.charAt(c)){
                    // edge cases for 1 & 2 letter subsequences
                    if(c - r <= 1){
                        dp[r][c] = c - r + 1;
                    } else{ 
                        dp[r][c] = dp[r + 1][c - 1] + 2;
                    }
                } else{
                    // no match, find longest subseq in 2 strings (excluding either start or end)
                    // and keep it as the longest till now
                    dp[r][c] = Math.max(dp[r][c - 1], dp[r + 1][c]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
