/*

LeetCode: 3. Longest Substring Without Repeating Characters

Medium

Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/

Topics: Hash Table, Two Pointers, String, Sliding Window

Acceptance: 28.6

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {

        int ans=0;
        if(s==null || s.length()==0)return ans;
        
        // Stores previous occurence of characters
        boolean[] seen = new boolean[256];
        int n = s.length();
        char c;

        // Two Pointer Sliding Window approach
        int start=0,end;
        
        for(int i=0;i<n;i++){
            c = s.charAt(i);
            // Keep increasing start pointer till current character is found
            // The index of this character can also be stored in a map instead to reduce time complexity
            while(seen[c]){
                seen[s.charAt(start)] = false;
                start++;
            }
            // Mark this char as seen and increase end pointer and revise ans variable
            seen[c]=true;
            end = i;
            if(ans<end-start+1){
                ans = end-start+1;
            }
        }
        return ans;
    }
}