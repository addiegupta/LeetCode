/*

LeetCode: 207. Course Schedule

Medium

Link: https://leetcode.com/problems/course-schedule/

Topics: Depth first search, breadth first search, Graph, Topological Sort

Acceptance: 38.6

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */


class Solution {
    // The problem is equivalent to finding a cycle in the graph
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites== null || prerequisites.length==0)return true;
        
        int n = prerequisites.length;
        
        // Nodes currently being explored
        boolean[] todo = new boolean[numCourses];
        // Nodes completely explored, no cycle found
        boolean[] done = new boolean[numCourses];
        
        // Create a adjacency list graph out of the given data
        // If this step was not done, and edges were observed by iterating over the given matrix,
        // then time taken was too high
        List<List<Integer>> graph = buildGraph(prerequisites,numCourses);
        
        // For every node, start a dfs recursion
        for(int i=0;i<numCourses;i++){
            // Skip already explored nodes 
            if(!done[i]){
                // If false is returned, then a cycle is present
                if(!dfs(graph,i,todo,done))return false;
            }
        }
        // No cycle present, all courses can be taken
        return true;
    }

    // Create adjacency list graph out of given edge data
    private List<List<Integer>> buildGraph(int[][] prereqs,int n){
        List<List<Integer>> graph = new ArrayList();
        for(int i=0;i<n;i++)graph.add(new ArrayList());
        for(int i=0;i<prereqs.length;i++){
            int course = prereqs[i][0];
            graph.get(course).add(prereqs[i][1]);
        }
        return graph;
    }

    // Recursive dfs method, returns false if a cycle is present else true
    private boolean dfs(List<List<Integer>> graph,int course,boolean[] todo, boolean[] done){
        
        // Already explored before, no cycle present from this node
        if(done[course])return true;
        // This node has been encountered before on this particular search hence is present in todo array
        // Therefore a cycle is present, return false
        if(todo[course])return false;
        
        // Mark this as true for current search and then continue dfs for children
        todo[course]=true;
        for(int pre : graph.get(course)){
            if(!dfs(graph,pre,todo,done))return false;
        }
        // Remove from current search and add to done array which stores completely explored nodes
        todo[course]=false;
        done[course]=true;
        return true;
    }
    
}