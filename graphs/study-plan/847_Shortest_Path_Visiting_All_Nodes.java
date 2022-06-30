/*

LeetCode: 847. Shortest Path Visiting All Nodes

Hard

Link: https://leetcode.com/problems/shortest-path-visiting-all-nodes/

Topics: Graph,  Breadth first search, Dynamic Programming, Bit Manipulation, Bitmask

Acceptance: 61

 You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:


Input: graph = [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:


Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
 

Constraints:

n == graph.length
1 <= n <= 12
0 <= graph[i].length < n
graph[i] does not contain i.
If graph[a] contains b, then graph[b] contains a.
The input graph is always connected.
 
*/
/*
 Time complexity: O(2 ^ N * N ^ 2)

The total number of possible states that can be put in our queue is O(2 ^ N * N) because there are 2 ^ N2 

  possibilities for mask, each of which can be paired with one of NN nodes.

At each state, we use a for loop to loop through all the edges the given node has. In the worst case, when the graph is fully connected, each node will have N - 1N−1 neighbors, so this step costs O(N)O(N) as the work done inside the for-loop is O(1)O(1).

Despite having the same time complexity as the first approach, in most cases, this algorithm will outperform the first one for the reasons we talked about in the intuition section, particularly because this algorithm will exit early as soon as it finds a solution.

Space complexity: O(2 ^ N \cdot N)O(2 
N
 ⋅N)

By the end of the algorithm, most of our extra space will be occupied by seen. Same as in the previous approach, depending on the implementation, seen will either be the same size as the number of states when it is initialized or it will eventually grow to that size by the end of the algorithm in the worst-case scenario.
 *
 * 
 * */
class Solution {
    class NodeState {
        int n;
        int visited;
        NodeState (int n, int visited){
            this.n = n;
            this.visited = visited;
        }
        
        @Override
        public boolean equals(Object o){
            NodeState ns = (NodeState)o;
            return ns.n == this.n && ns.visited == this.visited;
        }
        
        @Override
        public int hashCode(){
            return this.n * 31 + this.visited;
        }
    }
    // Multisource bfs starting from every possible node
    // (shortest path could start from any node, since visiting same node multiple times is allowed,
    // might have to traverse same path multiple times
    // to store in visited hashset, keep 2 variables which is part of class NodeState
    // n: the value of the node
    // visited: a bitmap of which nodes have been visited in current path
    // since same node can be visited multiple times, visited will not check simply if num has been visited
    // it will also compare if same node has been visited in the state where given nodes have also been visited to stop repetition of visiting nodes
    // e.g. traversal starts at 0 ,state is {n: 0, bitmap: {0}}, goes to 1, state is 
    // {n: 1, bitmap {0,1}}, goes back to 0, state is {n: 0, bitmap {0,1}} (note bitmap also contains 1 now, if only 0 was there, this would have been a repeated state and would have been skipped to avoid cycle)
    public int shortestPathLength(int[][] graph) {
        if(graph == null || graph.length == 0){
            return 0;
        }
        int n = graph.length;
        int expectedVisited = 0;
        // bitmap: all values from 0 to n are set
        expectedVisited = ((1<<(n)) - 1);
        Queue<NodeState> q = new LinkedList();
        Set<NodeState> set = new HashSet();
        
        //multisource bfs, start from every node together
        for(int i = 0 ; i < n; i++){
            q.offer(new NodeState(i, (1<<i)));
        }
        int path = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            while(len-- > 0){
                NodeState node = q.poll();
                if(set.contains(node))continue;
                // all nodes visited, since bfs this would be shortest hence return
                if(node.visited == expectedVisited){
                    return path;
                }
                set.add(node);
                for(int j = 0 ; j < graph[node.n].length; j++){
                    // set the bit correspoding to value of this node
                    NodeState neighbor = new NodeState(graph[node.n][j], node.visited | (1<<graph[node.n][j]));
                    if(!set.contains(neighbor)){
                        q.offer(neighbor);
                    }
                }
            }
            path++;
        }
        return -1;
    }
}
