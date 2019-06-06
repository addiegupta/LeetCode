/*

LeetCode: 49. Group Anagrams

Medium

Link: https://leetcode.com/problems/group-anagrams/

Topics: Hash Table, String

Acceptance: 46.9

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

 */
 
 class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        return characterCountHashMethod(strs);
		// return sortedStringHashMethod(strs);
    }
	private List<List<String>> sortedStringHashMethod(String[] strs){
        if(strs.length==0)return new ArrayList<>();

	    Map<String,List<String>> map = new HashMap<>();
        
        int n = strs.length;
        for(int i=0;i<n;i++){
            
			char[] ca = strs[i].toCharArray();
			Arrays.sort(ca);
			String sorted = new String(ca);
    
			if(map.containsKey(sorted))map.get(sorted).add(strs[i]);
            else{
                List<String> ls = new ArrayList<>();
                ls.add(strs[i]);
                map.put(sorted,ls);
            }
        }
        return new ArrayList(map.values());
    
	}
    private List<List<String>> characterCountHashMethod(String[] strs){
        if(strs.length==0)return new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();

        int n = strs.length;
        for(int i=0;i<n;i++){
            String str = getHashString(strs[i]);
            if(map.containsKey(str))map.get(str).add(strs[i]);
            else{
                List<String> ls = new ArrayList<>();
                ls.add(strs[i]);
                map.put(str,ls);
            }
        }
        return new ArrayList(map.values());
    }
	// Returns a string consisting of character counts, delimited by '#'
	// This string is used as key in HashMap
    private String getHashString(String s){
        int[] hash = new int[26];
        for(char c : s.toCharArray()){
            hash[c -'a']++;
        }
        StringBuilder ans= new StringBuilder("");
        for(int i=0;i<26;i++){
            ans.append('#');
            ans.append(hash[i]);
        }
        return ans.toString();
    }
}