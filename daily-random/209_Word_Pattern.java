/*

LeetCode: 290. Word Pattern

Easy

Link: https://leetcode.com/problems/word-pattern/

Topics: Hash Table, String

Acceptance: 39

Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 

Example 1:

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false
 

Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.


*/

class Solution {
    // Time: O(n): iterate over entire strings
    // Space: O(1): map will only have fixed amount of words
    public boolean wordPattern(String pattern, String s) {
        if(pattern == null || s == null || pattern.length() == 0 || s.length() == 0){
            return true;
        }
        
	// only lowercase letter input constraint hence 26 size
	// map: letter -> word associated with it
        String[] map = new String[26];

        int charIndex;
        String word;
        int i = 0, j = 0;
        int m = pattern.length();
        int n = s.length();
        while(i < m && j < n){
            charIndex = pattern.charAt(i) - 'a';
            int start = j;
            
            // probably could have used String.split()
            while(j < n && s.charAt(j) != ' '){
                j++;
            }
            j++;

	    // current word
            word = s.substring(start, Math.min(n, j - 1));
            String toMatch = map[charIndex];
	    // pattern char does not already have a word associated with it
	    // store this word in map, if same word does not associate with some other char
            if(toMatch == null){
                for(int k = 0 ; k < 26; k++){
                    if(word.equals(map[k])){
                        return false;
                    }
                }
                map[charIndex] = word;
            } else if(!toMatch.equals(word)){
		// same pattern char is already associated with some other word, pattern does not match
                return false;
            } 
            i++;
        }
	// some chars in pattern OR words in string are still left, does not match
        if(i < m || j < n){
            return false;
        }
        return true;
    }
}
