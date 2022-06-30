/*

LeetCode: 1641. Count Sorted Vowel Strings

Medium

Link: https://leetcode.com/problems/count-sorted-vowel-strings/

Topics: Array, Dynamic Programming

Acceptance: 77

Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.

A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

 

Example 1:

Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
Example 2:

Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
Example 3:

Input: n = 33
Output: 66045
 

Constraints:

1 <= n <= 50 
 
 */

class Solution {
    public int countVowelStrings(int n) {
        if(n == 0){
            return 0;
        }
        // using 1d array instead of 2d to save space as only previous 1 row required
        // at every i,j (1 indexed): i represents length of string, j represents
        // number of characters that can be used
        // dp[i][j] = dp[i-1][j] + dp[i][j - 1]
        // adding 1 character(corresponding to j) to every permutation of length i - 1 
        // as well as carry the sum possible with j - 1 allowed characters by adding another j-1th character
        // if string is built from right to left, e.g. j == 0 corresponds to u, 1 corresponds to o
        // adding dp[i-1][1] would mean o being prepended to length of i - 1 (ou , oo) 
        // adding dp[i][0] would mean carrying string starting with u (uu)
        // the subproblem for which was formed by prepending u to length of i -1 (u)
        
        int[] dp = new int[5];
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < 5; j++){
                // first row, permutations is num of characters allowed
                if(i == 0){
                    dp[j] = j + 1;
                } else if(j != 0){
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[4];
    }
}
