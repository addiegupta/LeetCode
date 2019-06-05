/*

LeetCode: 118. Pascal's Triangle

Easy

Link: https://leetcode.com/problems/pascals-triangle/

Topics: Array

Acceptance: 46.1

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if(numRows==0)return ans;
        
        List<Integer> row = new ArrayList();
        // Create first row
        row.add(1);
        ans.add(new ArrayList(row));
        row.clear();
        
        int val=0;
        for(int i=1;i<numRows;i++){
            // Create subsequent rows
            for(int j=0;j<=i;j++){
                if(j>0)val+=ans.get(i-1).get(j-1);
                if(j<i)val+=ans.get(i-1).get(j);
                row.add(val);
                val=0;
            }
            ans.add(new ArrayList(row));
            row.clear();
        }
        return ans;
    }
}