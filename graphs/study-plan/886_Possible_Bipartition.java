/*

LeetCode: 886. Possible Bipartition

Medium

Link: https://leetcode.com/problems/possible-bipartition/

Topics: Graph, Depth first Search, Breadth first search, Union Find

Acceptance: 47.7

 We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4] and group2 [2,3].
Example 2:

Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 

Constraints:

1 <= n <= 2000
0 <= dislikes.length <= 104
dislikes[i].length == 2
1 <= dislikes[i][j] <= n
ai < bi
All the pairs of dislikes are unique.


*/

class Solution {
    class Node {
        int x;
        boolean setA;
        Node(int x , boolean setA){
            this.x = x;
            this.setA = setA;
        }
    }
    
    public boolean possibleBipartition(int n, int[][] dislikes) {
        if(dislikes == null || dislikes.length == 0){
            return true;
        }
        //return initialSolution(n, dislikes);
        return graphColorSolution(n, dislikes);
    }
    
    private boolean graphColorSolution(int n, int[][] dislikes) {
        // passing true to build undirected graph, direction does not really matter
        // using undirected will make easier to keep track of adjacent colored nodes
        Map<Integer, List<Integer>> graph = buildGraph(dislikes, true);
        // 0: not colored yet
        // 1: colored black
        // -1: colored white
        // all adjacent nodes should have opposite colors for graph to be bipartite
        int[] color = new int[n + 1];
        // at index 0: val, 1: color
        Queue<int[]> q = new LinkedList();
        for(int i = 1 ; i <= n; i++){
            // already visited
            if(color[i] != 0){
                continue;
            }
            q.offer(new int[]{i, 1});
            while(!q.isEmpty()){
                int[] node = q.poll();
                int x = node[0];
                if(color[x] != 0)continue;
                color[x] = node[1];
                if(graph.containsKey(x)){
                    for(int nbr: graph.get(x)){
                        if(color[nbr] == 0){
                            q.offer(new int[]{nbr, -color[x]});
                            continue;
                        }
                        // adjacent nodes going to be same color, cannot bipartition
                        if(color[x] * color[nbr] == 1){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int[][] edges, boolean undirected){
        Map<Integer, List<Integer>> graph = new HashMap();
        for(int i = 0 ; i < edges.length; i++){
            int src = edges[i][0];
            int dest = edges[i][1];
            if(!graph.containsKey(src)){
                graph.put(src, new ArrayList());
            }
            graph.get(src).add(dest);
            if(undirected){
                if(!graph.containsKey(dest)){
                    graph.put(dest, new ArrayList());
                }
                graph.get(dest).add(src);
            }
        }
        return graph;
    }
    
    private boolean initialSolution(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = buildGraph(dislikes, false);
        // add every node to set A or set B and ensure no 2 dislikers are in same set
        Set<Integer> setA = new HashSet();
        Set<Integer> setB = new HashSet();
        Queue<Node> q = new LinkedList();
        for(int i = 1 ; i <= n; i++){
            if(setA.contains(i) || setB.contains(i)){
                continue;
            }
            boolean toA = true;
            if(graph.containsKey(i)){
                for(int nbr : graph.get(i)){
                    if(setA.contains(nbr)){
                        toA = false;
                        break;
                    }
                }
            }
            q.offer(new Node(i, toA));
            while(!q.isEmpty()){
                Node node = q.poll();
                int x = node.x;
                boolean addToSetA = node.setA;
                if((addToSetA && setB.contains(x)) || (!addToSetA && setA.contains(x))){
                    return false;
                }
                if(setA.contains(x) || setB.contains(x)){
                    continue;
                }
                if(addToSetA){
                    setA.add(x);
                } else {
                    setB.add(x);
                }
                if(!graph.containsKey(x)){
                    continue;
                }
                for(int nbr : graph.get(x)){
                    boolean addNextToA = !addToSetA;
                    q.offer(new Node(nbr, addNextToA));
                }
            }
            
        }
        return true;
    }
}
