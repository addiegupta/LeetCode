/*

LeetCode: 5. Longest Palindromic Substring

Medium

Link: https://leetcode.com/problems/longest-palindromic-substring/

Topics: String, Dynamic Programming

Acceptance: 27.6

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

*/

class Solution {
    public String longestPalindrome(String s) {
        if(s==null || s.length() <2 ) return s;
        
        // return dpSolution(s);
        return extendPalinSolution(s);
    }

    private String dpSolution(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0,end =0;
        // Start creating dp table from bottom right corner of the table i.e. the last letter of the string
        // and move upward only to the right of the main diagonal, left to right
        for(int i=n-1;i>=0;i--){
            for(int j=i;j<n;j++){
                // substring from i to j is palindrome if the letters are same and the substring without i and j is 
                // also palindromic
                dp[i][j] = s.charAt(i)==s.charAt(j) && (j-i<3 || dp[i+1][j-1]);
                
                if(dp[i][j] && j-i > end-start){
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start,end+1);      
    }
    
    // global variables for extend palindrome method
    private int lo, maxLen;
    
    // This solution works faster than dp solution surprisingly
    private String extendPalinSolution(String s){
        int len = s.length();

        // At every letter try creating palindrome toward left and right
        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }
    
    private void extendPalindrome(String s, int j, int k) {
        // extend palindrome towards both directions
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
}