/*

LeetCode: 605. Can Place Flowers

Easy

Link: https://leetcode.com/problems/detect-capital/

Topics: String

Acceptance: 51

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

 

Example 1:

Input: word = "USA"
Output: true
Example 2:

Input: word = "FlaG"
Output: false
 

Constraints:

1 <= word.length <= 100
word consists of lowercase and uppercase English letters.
*/

class Solution {
    public boolean detectCapitalUse(String word) {
        if(word == null){
            return true;
        }
        int count = 0;
        for(int i = 0 ; i < word.length(); i++){
            char c = word.charAt(i);
            // is uppercase
            if(c - 'A' <= 26 && c-'A' >=0){
                count++;
                if(count!=0 && count < i + 1){
                    return false;
                }
            }
        }
        return count <= 1 || count == word.length();
    }
}
