/*

LeetCode: 392. Is Subsequence

Easy

Link: https://leetcode.com/problems/is-subsequence/

Topics: Dynamic Programming, String, Two Pointers

Acceptance: 50.8

 Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

 

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
 

Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.
 

Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?

*/

// For followup, a map of the string t can be made with keys as alphabets and values as list of indices where that alphabet is contained
// e.g. for t = 'abca' map would be { 'a': [0, 3], 'b':[1], 'c': [2]}
class Solution {
    public boolean isSubsequence(String s, String t) {
        if(s==null || s.length() == 0){
            return true;
        }
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        while(i < m && j < n){
	    // once a character from s is found in t, there would be no case in future where s would have to be looked at again from start
	    // i.e. there is no reset case where i would have to be set to 0, since only relative order of letters is required
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        return i == m;
    }
}
