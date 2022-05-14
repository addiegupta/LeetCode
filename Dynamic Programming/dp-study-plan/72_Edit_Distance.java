/*

LeetCode: 72. Edit Distance

Hard

Link: https://leetcode.com/problems/edit-distance/

Topics: String, Dynamic Programming

Acceptance: 50.4

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 
 */

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null){
            return 0;
        }
       // return initialSquaredSpaceDpSolution(word1, word2);
        return improvedLinearSpaceDpSolution(word1, word2);
        
    }
    // Time: O(mn); Space: O(mn)
    private int initialSquaredSpaceDpSolution(String word1, String word2){
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0){
                    // j insert operations to convert to substring of word2 till j 
                    dp[i][j] = j;    
                } else if(j == 0){
                    // i delete operations to convert to empty string
                    dp[i][j] = i;
                } else if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    // characters are matching, no operation needed here
                    // use subproblem for when both these characters are not present i.e. top left
                    dp[i][j] = dp[i - 1][j - 1];
                } else{
                    // Delete operation; deleted char at i - 1 and now looking for same substring of word2
                    // but substring of word1 till 1 character before
                    int top = dp[i - 1][j];
                    // Insert operation; inserted the j - 1th character and now looking for match with word2
                    // till 1 character before
                    int left = dp[i][j - 1];
                    // Replace operation; char at i - 1 replaced with char at j - 1; now look at subproblem without these chars ( note, i - 1 because i == 0 corresponds to empty string and not first character)
                    int topLeft = dp[i - 1][j - 1];
                    // pick the min number of operations
                    int best = Math.min(top, Math.min(left, topLeft));
                    // add 1 for this current operation
                    dp[i][j] = best + 1;
                }
            }
        }
        return dp[m][n];
    }
    // since only 2 rows of dp table are required (current and 1 row before i.e. prev)
    // can use 2 arrays of n length rather than m*n table to bring space from O(mn) to O(n)
    // Time: O(mn); Space: O(n)
    private int improvedLinearSpaceDpSolution(String word1, String word2){
        int m = word1.length();
        int n = word2.length();
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0){
                    curr[j] = j;    
                } else if(j == 0){
                    curr[j] = i;
                } else if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    curr[j] = prev[j - 1];
                } else{
                    int top = prev[j];
                    int left = curr[j - 1];
                    int topLeft = prev[j - 1];
                    int best = Math.min(top, Math.min(left, topLeft));
                    curr[j] = best + 1;
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
}
