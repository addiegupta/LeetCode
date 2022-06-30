/*

LeetCode: 1319. Number of Operations to Make Network Connected

Medium

Link: https://leetcode.com/problems/number-of-operations-to-make-network-connected/

Topics: Graph, Depth first Search, Breadth first search, Matrix, Array

Acceptance: 58

 There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

 

Example 1:


Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:


Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
 

Constraints:

1 <= n <= 105
1 <= connections.length <= min(n * (n - 1) / 2, 105)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections.
No two computers are connected by more than one cable.

*/

// similar to finding number of islands, as total number of already connected computer networks are required
// done using union find, could also be done by dfs
// starting dfs at every node and count how many sources were needed to start new dfs
class Solution {
    int[] parent;
    private int findParent(int i){
        return parent[i] == i ? i : findParent(parent[i]);
    }
    private boolean union(int i, int j){
        int parentI = findParent(i);
        int parentJ = findParent(j);
        if(parentI == parentJ) return false;
        parent[parentJ] = parentI;
        return true;
    }
    
    public int makeConnected(int n, int[][] connections) {
        if(connections == null || connections.length == 0){
            return 0;
        }
        int len = connections.length;
        
        // init parent array
        parent = new int[n];
        for(int i = 0 ; i < n; i++){
            parent[i] = i;
        }
        int cablesAvailable = len;
        int totalComponents = n;
        int totalComputers = n;
        
        for(int i = 0 ; i < len; i++){
            int comp1 = connections[i][0];
            int comp2 = connections[i][1];
            boolean unionDone = union(comp1, comp2);
            if(unionDone){
                totalComponents--;
            }
        }
        
        int newCablesRequired = totalComponents - 1;
        int totalCablesRequired = totalComputers - 1;
        return cablesAvailable >= totalCablesRequired ? newCablesRequired : -1;
    }
}
