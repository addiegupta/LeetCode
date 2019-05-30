/*

LeetCode: 344. Reverse String   

Link: https://leetcode.com/problems/reverse-string

Topics: Two Pointers, String

Acceptance: 63.3

Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.


Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]



*/

// Java
class Solution {
    public void reverseString(char[] s) {
        int n = s.length;
        for(int i=0;i<n/2;i++){
            s[i] = (char)(s[i]+s[n-i-1]);
            s[n-i-1] = (char)(s[i]-s[n-i-1]);
            s[i] = (char)(s[i]-s[n-i-1]);
        }
    }
}

// C++
/*
class Solution {
public:
    void reverseString(vector<char>& s) {
        int n = s.size();
        for(int i=0;i<n/2;i++){
            s[n-i-1] += s[i];
            s[i]=s[n-i-1]-s[i];
            s[n-i-1] = s[n-i-1]-s[i]; 
        }
    }
};
*/
