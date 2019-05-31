/*

LeetCode: 387. First Unique Character in a String

Easy

Link: https://leetcode.com/problems/first-unique-character-in-a-string/

Topics: Hash Table,String

Acceptance: 50.0

Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 
*/
class Solution {
    public int firstUniqChar(String s) {
        // Store all encountered chars in hash
        int[] hash = new int[26];
        int n = s.length();
        for(int i=0;i<n;i++){
            hash[s.charAt(i)-'a']++;
        }
        for(int i=0;i<n;i++){
            // Only one occurence, return index
            if(hash[s.charAt(i)-'a']==1)return i;
        }
        return -1;
    }
}