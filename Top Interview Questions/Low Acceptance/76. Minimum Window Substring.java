/*

LeetCode: 76. Minimum Window Substring

Hard

Link: https://leetcode.com/problems/minimum-window-substring/

Topics: String, Hash Table, Two Pointers, Sliding Window

Acceptance: 31.5

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

 */


class Solution {
    public String minWindow(String s, String t) {
       
        if(s.length()==0 || t.length()==0)return "";
        
        // Store character count for string and pattern
        int[] hash = new int[256];
        int[] window = new int[256];
        
        int m = t.length(),n = s.length();
        
        // Count: count of characters in pattern
        // Curr: is count present in window
        int count=m,curr=0;  
        
        // Create hash for pattern
        for(int i=0;i<m;i++) hash[t.charAt(i)]++;
        
        int ansL=-1,ansR=-1,l=0,r=-1;
        
        while(r<n){
            
            // Window has all required characters, try to reduce it
            if(curr == count ){
                char start = s.charAt(l);

                // Better window found
                if(ansL == -1 || ansR-ansL > r-l){
                    ansR = r;
                    ansL = l;
                }
                // Remove this character and advance
                if(hash[start]>0){
                    window[start]--;
                    // A required character is being removed from window
                    if(window[start]<hash[start])curr--;
                }
                // Shrink the window
                l++;
            }
            // Need to find a valid window
            else{
                r++;
                if(r==n)break;
                char end = s.charAt(r);
                // Required character
                if(hash[end]>0){
                    window[end]++;
                    if(window[end]<=hash[end])curr++;
                }
            }
        }

        // Empty string if no valid window present
        return (ansL == -1)?"":s.substring(ansL,ansR+1);
    }
}