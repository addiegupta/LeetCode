/*

LeetCode: 210. Course Schedule II

Medium

Link: https://leetcode.com/problems/course-schedule-ii/

Topics: Depth first search, breadth first search, Graph, Topological Sort

Acceptance: 35.7

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.


 */

class Solution {
    
    // A few more steps added to Course Schedule 1 to find the sorted order in Course Schedule 2
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // Store topological sort order
        List<Integer> order = new ArrayList();
        if(prerequisites== null )return new int[0];
        
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
                if(!dfs(graph,i,todo,done,order))return new int[0];
            }
        }
        // No cycle present, all courses can be taken
        int[] ans = new int[numCourses];
        int k=0;
        // If any course node has not been visited at all, then it was not a part of prerequisited array
        // add it to course order
        for(int i=0;i<numCourses;i++)if(!done[i])ans[k++]=i;
        // Add numbers to ans array in topologically sorted order
        for(int course:order)ans[k++] = course;
        return ans;
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
    private boolean dfs(List<List<Integer>> graph,int course,boolean[] todo, boolean[] done, List<Integer> order){
        
        // Already explored before, no cycle present from this node
        if(done[course])return true;
        // This node has been encountered before on this particular search hence is present in todo array
        // Therefore a cycle is present, return false
        if(todo[course])return false;
        
        // Mark this as true for current search and then continue dfs for children
        todo[course]=true;
        for(int pre : graph.get(course)){
            if(!dfs(graph,pre,todo,done,order))return false;
        }
        // Remove from current search and add to done array which stores completely explored nodes
        todo[course]=false;
        done[course]=true;
        // Add to topological sort order list
        order.add(course);
        return true;
    }
}