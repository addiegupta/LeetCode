/*

LeetCode: 242. Valid Anagram

Easy

Link: https://leetcode.com/problems/valid-anagram/

Topics: Hash Table, Sort

Acceptance: 52.2


Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

*/

class Solution {
    public boolean isAnagram(String s, String t) {
        // Create hash of letters of one string
        int[] hash = new int[26];
        int n = s.length();
        if(n!=t.length())return false;
        for(int i=0;i<n;i++)hash[s.charAt(i)-'a']++;
        
        // Compare second string with hash
        char c;
        for(int i=0;i<n;i++){
            c=t.charAt(i);
            if(hash[c-'a']==0)return false;
            hash[c-'a']--;
        }
        return true;
    }
}