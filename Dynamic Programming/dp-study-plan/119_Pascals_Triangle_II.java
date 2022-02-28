/*

LeetCode: 119. Pascal's Triangle II

Easy

Link: https://leetcode.com/problems/pascals-triangle-ii/

Topics: Array, Dynamic Programming

Acceptance: 56.5

Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

 

Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]
Example 2:

Input: rowIndex = 0
Output: [1]
Example 3:

Input: rowIndex = 1
Output: [1,1]
 

Constraints:

0 <= rowIndex <= 33
 

Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 
 */

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list =  new ArrayList();
        if(rowIndex < 0){
            return list;
        }
	// pascals triangle creates row by adding top and top left numbers together
	// since we only need to return 1 row, we can use only 1 list and treat every number as top number while creating new row
	// iteration is done back to front since we need top and top left. if front to back was done, left value would have been changed
        for(int i = 0 ; i <= rowIndex; i++){
            list.add(1);
            for(int j = list.size() - 2; j > 0; j--){
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }
        return list;
    }
}
