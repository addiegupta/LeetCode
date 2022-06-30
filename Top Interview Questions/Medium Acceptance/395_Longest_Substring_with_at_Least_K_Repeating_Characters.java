/*

LeetCode: 395. Longest Substring with At Least K Repeating Characters

Medium

Link: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/

Topics: Hash Table, String, Divide & Conquer, Sliding Window

Acceptance: 44.8

Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

 

Example 1:

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 

Constraints:

1 <= s.length <= 104
s consists of only lowercase English letters.
1 <= k <= 105

*/

class Solution {
    int ans = 0;
    public int longestSubstring(String s, int k) {
        if (s == null || s.isEmpty() || k > s.length()) {
            return 0;
        }
        // return initialSlowSolution(s, k);
        //return newCleanSolution(s, k);
        return bestOptimisedSolution(s, k);
    }
    
    // Time O(n ^2) (worst case, althought performs better mostly)
    // Space O(1)
    private int bestOptimisedSolution(String s, int k){
        int n = s.length();
       
        //store count of each character in this string
        int[] freq = new int[26];
        for(int i = 0 ; i < n; i++){
            freq[s.charAt(i) - 'a']++;
        }
        
        int ans = 0;
        // start index of current substring
        int start = 0;
        boolean isValid = true;
        
        for(int i = 0 ; i < n; i++){
            int index = s.charAt(i) - 'a';
            // this character cannot be included in final answer
            // check for left substring, right substring would be formed by continuing the current for loop
            if(freq[index] > 0 && freq[index] < k){
                String sub = s.substring(start, i);
                ans = Math.max(ans, bestOptimisedSolution(sub, k));
                // to check for right substring later
                start = i + 1;
                isValid = false;
            }
        }
        // entire string fulfils condition
        if(isValid){
            return n;
        } else {
            return Math.max(ans, bestOptimisedSolution(s.substring(start, n), k));
        }
    }
    // brute force, time complexity still O(n ^2)
    private int initialSlowSolution(String s, int k){
        int[] countMap = new int[26];
        int n = s.length();
        int result = 0;
        for (int start = 0; start < n; start++) {
            // reset the count map
            Arrays.fill(countMap, 0);
            for (int end = start; end < n; end++) {
                countMap[s.charAt(end) - 'a']++;
                if (isValid(s, start, end, k, countMap)) {
                    result = Math.max(result, end - start + 1);
                }
            }
        }
        return result;
    }

    private boolean isValid(String s, int start, int end, int k, int[] countMap) {
        int countLetters = 0, countAtLeastK = 0;
        for (int freq : countMap) {
            if (freq > 0) countLetters++;
            if (freq >= k) countAtLeastK++;
        }
        return countAtLeastK == countLetters;
    }
    
    private int newCleanSolution(String s, int k) {
        
        int n = s.length();
        int[] map = new int[26];
        for(int i = 0; i < n ; i++){
            int ind = s.charAt(i) - 'a';
            map[ind]++;
        }
        int i = 0;
        int j = n-1;
        helper(s, i, j, map, k);
        return ans;
    }
    private void helper(String s, int i, int j, int[] map, int k){
        if(i > j || j-i+1 < k){
            return;
        }
        
        boolean allGreater = true;
        int minIndex = -1;
        // all letters in substring > k, return length
        for(int x = 0 ; x < 26; x++){
            if(map[x] !=0 && map[x] < k){
                if(minIndex == -1){
                    minIndex = x;
                } else if(map[minIndex] > map[x]){
                    minIndex = x;
                }
                allGreater = false;
            }
        }
        if(allGreater){
            ans = Math.max(ans, j - i + 1);
            return;
        }
        char minChar = (char)(minIndex + 'a');
        for(int x = i; x <= j ; x++){
            if(s.charAt(x) == minChar){
                minIndex = x;
                break;
            }
        }
        int[] originalMap = new int[26];
        for(int x = 0 ; x < 26; x++){
            originalMap[x] = map[x];
        }
        for(int x = i; x <= minIndex; x++){
            map[s.charAt(x) - 'a']--;
        }
        helper(s, minIndex+1, j, map, k);
        
        for(int x = j; x >= minIndex; x--){
            originalMap[s.charAt(x) - 'a']--;
        }
        helper(s, i, minIndex - 1, originalMap, k);
    }
}
