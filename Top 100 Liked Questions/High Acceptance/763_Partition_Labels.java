/*

LeetCode: 763. Partition Labels

Medium

Link: https://leetcode.com/problems/partition-labels/

Topics: Hash Table, Two Pointers, String, Greedy

Acceptance: 78.6

You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 
 */

class Solution {
    public List<Integer> partitionLabels(String s) {
        if(s == null || s.isEmpty()){
            return new ArrayList();
        }
        //return initialSolution(s);
        return lcCleanSolution(s);
    }
    // Time: O(n)
    // Space: O(1)
    private List<Integer> lcCleanSolution(String s){
        List<Integer> ans = new ArrayList();
        int n = s.length();
        int[] map = new int[26];
        for(int i = 0; i < n; i++){
            // store the last occuring index of every char, can only create partitions at these index
            map[s.charAt(i) - 'a'] = i;
        }
        int last = 0;
        int start = 0;
        for(int i = 0; i< n; i++){
            // for already encountered characters, keep max last so that
            // all encountered chars appear in current partition only
            last = Math.max(last, map[s.charAt(i) - 'a']);
            if(last == i){
                ans.add(last - start + 1);
                start = i + 1;
            }
        }
        return ans;
    }
    
    // Time: O(n)
    // Space: O(1)
    private List<Integer> initialSolution(String s){
        List<Integer> ans = new ArrayList();
        int n = s.length();
	// store count of chars to left and right of current pointer
        int[] right = new int[26];
        int[] left = new int[26];
        for(int i = 0; i < n; i++){
            right[s.charAt(i) - 'a']++;
        }
        int partitionStart = 0;
        for(int i = 0; i < n; i++){
            int ind = s.charAt(i) - 'a';
            right[ind]--;
            left[ind]++;
	    // no more occurences at right, could be valid place to partition
            if(right[ind] == 0 && isValid(left, right)){
                ans.add(i - partitionStart + 1);
                partitionStart = i+1;
            }
        }
        return ans;
    }
    
    private boolean isValid(int[] left, int[] right){
        for(int i = 0; i < 26; i++){
            if(left[i] != 0 && right[i] != 0){
                return false;
            }
        }
        return true;
    }
}
