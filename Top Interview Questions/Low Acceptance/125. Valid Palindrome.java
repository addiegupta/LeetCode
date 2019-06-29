/*

LeetCode: 125. Valid Palindrome

Easy

Link: https://leetcode.com/problems/valid-palindrome/

Topics: Two Pointers, String

Acceptance: 31.4

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false

 */
class Solution {
    private boolean isAlphaNum(char c){return (c>='a'&& c<='z') ||(c>='0' && c<='9' );}
    public boolean isPalindrome(String s) {
        
        if(s==null || s=="")return true;
        s = s.toLowerCase();
        int i=0,j=s.length()-1;
        while(i<j){
            if(!isAlphaNum(s.charAt(i)))i++;
            else if(!isAlphaNum(s.charAt(j)))j--;
            else if(s.charAt(i)!= s.charAt(j))return false;
            else{
                i++;
                j--;
            }
        }
        return true;
    }
}