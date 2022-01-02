/*

LeetCode: 424. Longest Repeating Character Replacement

Medium

Link: https://leetcode.com/problems/longest-repeating-character-replacement/

Topics: Hash Table, String, Sliding Window

Acceptance: 50.1

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

 

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 

Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length

*/

class Solution {
    // Solved using sliding window approach
    public int characterReplacement(String s, int k) {
        if(s==null || s.length()==0){
            return 0;
        }
        
        int n = s.length();
	// character count in sliding window
        int[] count = new int[26];
        int ans = 0, start=0, end=0,maxCount = 0;
        for(end = 0; end < n; end++){
            // update maxCount and count with latest end char in window	
            char endChar = s.charAt(end);
            int endCharInd = endChar - 'A';
            count[endCharInd]++;
	    // maxCount is only updated when end is incremented ( and not when start is decremented) 
	    // hence maxWindow will be an incorrect value at times. but it does not give wrong answer as we are only interested
	    // in max possible length and maxCount being incorrect does not affect that,
	    // since if a higher value is possible later, it will be larger than previous incorrect maxCount
            maxCount = Math.max(maxCount, count[endCharInd]);
            
	    // if in current sliding window, (length - maxCount) i.e. the count of characters that needs to be replaced
	    // is larger than k, then our window is too large, need to increase start pointer to decrease window size
            while(end - start + 1 - maxCount > k){
                count[s.charAt(start) - 'A']--;
                start++;
            }
            ans = Math.max(ans, end-start+1);
        }
        return ans;
    }
}
