/*

LeetCode: 1143 Longest Common Subsequence

Medium

Link: https://leetcode.com/problems/longest-common-subsequence/

Topics: Dynamic Programming, String

Acceptance: 58.9

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 
 */

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }       
        int length1 = text1.length();
        int length2 = text2.length();
	// dp[i][j] = LCS of text1 from index 0 to i - 1 and of text2 from index 0 to j - 1
        int[][] dp = new int[length1 + 1][length2 + 1];
        
        for(int i = 1; i <= length1; i++){
            for(int j = 1; j <= length2; j++){
		// last characters match; lcs of this position would be lcs of the strings with both these characters removed + 1
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else{
	            // last characters dont match; take max found by losing either 1 character from both strings
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[length1][length2];
    }
}
