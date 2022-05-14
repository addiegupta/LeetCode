/*

LeetCode: 151. Reverse Words in a String

Medium

Asked in: Creditas

Link: https://leetcode.com/problems/reverse-words-in-a-string/

Topics: Two Pointers, String

Acceptance: 28

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

 

Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Constraints:

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.
 

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 
*/

// A variation of this also involving replacing alternate vowels with '-' and consonants appearing more than x times with '_' was asked in Creditas
class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        Stack<String> words = new Stack();
        int n = s.length();
        int start = 0;
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c == ' '){
                if(start != i){
                    words.push(s.substring(start, i));
                }
                start = i + 1;
            }
        }
        if(start != n){
            words.push(s.substring(start, n));
        }
        StringBuilder ans = new StringBuilder();
        while(!words.isEmpty()){
            ans.append(words.pop());
            if(!words.isEmpty()){
                ans.append(" ");
            }
        }
        return ans.toString();
    }
}
