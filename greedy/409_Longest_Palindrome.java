/*

LeetCode: 409. Longest Palindrome

Easy

Link: https://leetcode.com/problems/longest-palindrome/

Topics: Hash Table, String, Greedy

Acceptance: 53.0

Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome here.

 

Example 1:

Input: s = "abccccdd"
Output: 7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
Example 2:

Input: s = "a"
Output: 1
Example 3:

Input: s = "bb"
Output: 2
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase and/or uppercase English letters only. 
*/


class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        int n = s.length();
        int count = 0;

	// seen[i] will indicate if character of index i is encountered before in this string or not
        boolean[] seen = new boolean[128];

        for(int i = 0; i < n; i++){
            int index = s.charAt(i);
	    // same character present before, can use 2 letters to add in palindrome
            if(seen[index]){
                count += 2;
            }
	    // invert in both cases: if seen before, it has been used up in this iteration; if not seen then mark as seen
            seen[index] = !seen[index];
        }
        
	// if 1 extra character can be added, palindrome can be made of odd length with 1 unmatched character
        if(count < n){
            count += 1;
        }
        return count;
    }
}
