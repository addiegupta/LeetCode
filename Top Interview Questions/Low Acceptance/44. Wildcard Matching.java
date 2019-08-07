/*

LeetCode: 44. Wildcard Matching

Hard

Link: https://leetcode.com/problems/wildcard-matching/

Topics: String, Dynamic Programming, Backtracking, Greedy

Acceptance: 23.2

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

 */
class Solution {
    
    public boolean isMatch(String s, String p) {
        // return initialSolution(s,p);
        return cleanDpSolution(s,p);
    }
    
    private boolean initialSolution(String s,String p){
        int[][] dp = new int[s.length()+1][p.length()+1];
        
        // Solution not yet computed
        for(int i=0;i<=s.length();i++){
            for(int j=0;j<=p.length();j++) dp[i][j] = -1;
        }
        // recursive function using memoization
        return matchRecur(s,p,0,0,dp);
    }
    // Check if string starting from i matches with pattern starting with j
    private boolean matchRecur(String s, String p,int i,int j,int[][] dp){
        boolean matched;

        // Precomputed; return result
        if(dp[i][j]!=-1)return dp[i][j] == 1;
        
        // String finished check if pattern contains only * or is finished
        else if(i==s.length()){
            matched =  j==p.length() || (p.charAt(j) == '*' && matchRecur(s,p,i,j+1,dp));
            dp[i][j] = matched?1:0;
            return matched;
        }
        // pattern finished, check if string is also complete
        else if(j==p.length()){
            dp[i][j] = i==s.length()?1:0;
            return i==s.length();
        } 
        char txtChar = s.charAt(i);
        char patChar = p.charAt(j);
        // Matching characters
        if(patChar=='?' || txtChar == patChar){
            // Check if rest of string matches
            matched = matchRecur(s,p,i+1,j+1,dp);
            dp[i][j] = matched?1:0;
            return matched;
        }
        // Multiple character wildcard
        else if(patChar == '*'){
            // Use * as     : empty string ||       single character ||         multiple characters
            matched = matchRecur(s,p,i,j+1,dp) || matchRecur(s,p,i+1,j+1,dp) || matchRecur(s,p,i+1,j,dp);
            dp[i][j] = matched?1:0;
            return matched;
        }
        else{
            dp[i][j] = 0;
            return false;
        }
    }
    
    private boolean cleanDpSolution(String s,String p){
        
        // Could be solved by using 1D array, done with 2D array for easier understanding
        // dp[i][j] represents if s.substring(0,i+1) is a match with p.substring(0,j+1)
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        
        // Empty strings are a match
        dp[0][0] = true;
        int m = s.length(),n = p.length(); 

        // Empty string matches with single/multiple '*'
        for(int j=1;j<=n;j++){
            if(p.charAt(j-1)!='*')break;
            dp[0][j]=true;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                // Single char match , check if string till previous char matches with pattern till previous char
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1)=='?') dp[i][j] = dp[i-1][j-1];
                // Multiple char wildcard; 
                // Check for match with using * as : current char || empty string
                // In this way at later iterations, * will be able to represent multiple chars as well
                else if(p.charAt(j-1)=='*')dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        }
        // entire string matches with entire pattern
        return dp[m][n];
    }
}