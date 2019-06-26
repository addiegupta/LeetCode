/*

LeetCode: 14. Longest Common Prefix

Easy

Link: https://leetcode.com/problems/longest-common-prefix/

Topics: String

Acceptance: 33.6

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.

 */class Solution {
    public String longestCommonPrefix(String[] strs) {
		return horizontalScanMethod(strs);
		return verticalScanMethod(strs);
	}
	// Match complete strings
	private String horizontalScanMethod(String[] strs){
		String ans = "";
        if(strs==null || strs.length == 0)return ans;
		
        int n = strs.length;
        String pre = strs[0];
        String curr;
        for(int i=1;i<n;i++){
            
			// Method 1 Horizontal Scan 1
            curr = strs[i];
			// Shorten prefix if current string is smaller
            if(pre.length>curr.length)pre = pre.substring(0,curr.length);
            for(int j=0;j<pre.length();j++){
				// Mismatch; shorten prefix
                if(pre.charAt(j)!=curr.charAt(j)){
                    pre = pre.substring(0,j);
                    break;
                }
            }
            
            // Method 2 Horizontal Scan 2
            /*
                // str.indexOf(s) returns position of substring s in str
                while(strs[i].indexOf(prefix)!=0){
                    prefix = prefix.substring(0,prefix.length()-1);
                    if(prefix.isEmpty())return "";
                }
            */    
        }
        return pre;
        
    }
	// Match characters of every string in order i.e. first character of each followed by second
	private String verticalScanMethod(String[] strs){
    
		if (strs == null || strs.length == 0) return "";
		for(int i=0;i<strs[0].length;i++){
			char c = strs[0].charAt(i);
			for(int j=1;j<strs.length;j++){
				if(i== strs[j].length || strs[j].charAt(i)!= c){
					return strs[0].substring(0,i);
				}
			}
		}
		return strs[0];
	}
}