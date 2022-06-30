/*

LeetCode: 1306. Jump Game III

Medium

Link: https://leetcode.com/problems/jump-game-iii/

Topics: Graph, Depth first Search, Breadth first search, Array

Acceptance: 62.8

 Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length

*/
class Solution {
    public boolean canReach(int[] arr, int start) {
        if(arr == null || arr.length == 0){
            return true;
        }
        int n = arr.length;
        // start bfs from start position
        Queue<Integer> q = new LinkedList();
        q.offer(start);
        Set<Integer> visited = new HashSet();
        while(!q.isEmpty()){
            int node = q.poll();
            if(visited.contains(node))continue;
            
            // found solution
            if(arr[node] == 0)return true;
            visited.add(node);
            int jump = arr[node];
            
            // jump left and right in bfs
            if(node + jump < n && !visited.contains(node + jump)){
                q.offer(node + jump);
            }
            if(node - jump >= 0 && !visited.contains(node - jump)) {
                q.offer(node - jump);
            }
        }
        return false;
    }
}
