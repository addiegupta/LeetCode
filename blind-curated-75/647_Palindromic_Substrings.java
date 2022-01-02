/*

LeetCode: 647. Palindromic Substrings

Medium

Link: https://leetcode.com/problems/palindromic-substrings/

Topics: String, Dynamic Programming

Acceptance: 63.9

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.

*/

class Solution {

    private boolean isPalindrome(int i, int j, String s, boolean dp[][]){
	// is same char used at start and end of string
        boolean isSameChar = s.charAt(i) == s.charAt(j); 
        
	// for 1,2 letter strings, no need to refer to dp
        return isSameChar && (j-i<=1 || dp[i+1][j-1]);
    }
    public int countSubstrings(String s) {
        int n = s.length();
        if(n==0){
            return 0;
        }

        int ans = 0;
        boolean dp[][] = new boolean[n][n];

	// diagonal iteration
        for(int i=0; i<n ; i++){
            for(int j=0; j<n-i; j++){
                int row = j;
                int col = i+j;

                dp[row][col] = isPalindrome(row, col, s, dp);
                if(dp[row][col]){
                    ans+=1;
                }
            }
        }
        return ans;
    }
}
