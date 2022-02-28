/*

LeetCode: 438. Find All Anagrams in a String

Medium

Link: https://leetcode.com/problems/find-all-anagrams-in-a-string/

Topics: String, Hash Table, Sliding Window

Acceptance: 47.2


Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if(s == null || p == null || s.length() == 0 || p.length() == 0){
            return new ArrayList();
        }
        List<Integer> ans = new ArrayList();
	// only lowercase letters present in both strings as per constraint
        int[] sMap = new int[26];
        int[] pMap = new int[26];
        int sLength = s.length();
        int pLength = p.length();
	
	// create map of characters in p
        for(int i = 0; i < pLength; i++){
            int index = p.charAt(i) - 'a';
            pMap[index]++;
        }
        
        int i = 0;
        int j = 0;
	// sliding window; for every substring compare maps of the substring and p
	// to get next substring, remove first character and append a new character from right
        while(j < sLength){
            int iIndex = s.charAt(i) - 'a';
            int jIndex = s.charAt(j) - 'a';
	    // length of window exceeded, remove first character
            if((j - i + 1) > pLength){
                sMap[iIndex]--;
                i++;
            }
            sMap[jIndex]++;
	    // length of window same as p, check for anagram
            if((j - i + 1) == pLength && isAnagram(sMap, pMap)){
                ans.add(i);
            }
            j++;
        }
        return ans;
        
    }
    private boolean isAnagram(int[] map1, int[] map2){
        for(int i = 0 ; i < 26; i++){
            if(map1[i] != map2[i]){
                return false;
            }
        }
        return true;
    }
}
