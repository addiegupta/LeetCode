/*

LeetCode: 139. Word Break

Medium

Link: https://leetcode.com/problems/word-break/

Topics: Dynamic Programming

Acceptance: 36.0

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        // return initialSolution(s,wordDict);
        
        return dpSolution(s,wordDict);
    }

    // Clean dp solution
    private boolean dpSolution(String s,List<String> wordDict){
        
        // dp[i] signifies whether s.substring(0,i) is a valid wordbreak
        boolean[] dp = new boolean[s.length()+1];

        // Convert dict to hashset for fast access
        Set<String> dict = new HashSet(wordDict);

        // s.substring(0,0) is empty string hence valid
        dp[0] = true;
        
        // In s.substring(j,i) , i is exclusive hence the = sign
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                
                // Check if previous substring is valid and new one formed is contained in dictionary
                if(dp[j] && dict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[s.length()];
        
    }

    // Initially thought solution; messy but essentially works on dp only
    private boolean initialSolution(String s,List<String> wordDict){
                
        Set<String> dict = new HashSet(wordDict);
        if(dict.size()==0)return false;
        int[][] dp = new int[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            for(int j=0;j<s.length();j++)dp[i][j]=-1;
        }
        return findWords(s,0,0,dict,dp);
        
    }
    private boolean findWords(String s,int i,int j,Set<String> dict,int[][] dp){
        // Either complete word at current character or continue with recursion
        if(dp[i][j]!=-1)return dp[i][j]==1;
        if(j==s.length()-1){
            dp[i][j] = dict.contains(s.substring(i,j+1))?1:0; 
            return dp[i][j]==1;
        }
        boolean breakPossible = false;
        if(dict.contains(s.substring(i,j+1))){
            dp[j+1][j+1] = findWords(s,j+1,j+1,dict,dp)?1:0;
            breakPossible = breakPossible || dp[j+1][j+1] ==1;
        } 
        
        dp[i][j+1] = findWords(s,i,j+1,dict,dp)?1:0;
        return breakPossible || dp[i][j+1] ==1;
    }
}