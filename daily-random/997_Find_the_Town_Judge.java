/*

LeetCode: 997. Find the Town Judge

Easy

Link: https://leetcode.com/problems/find-the-town-judge/

Topics: Array, Hash Table, Graph

Acceptance: 50.1

In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

 

Example 1:

Input: n = 2, trust = [[1,2]]
Output: 2
Example 2:

Input: n = 3, trust = [[1,3],[2,3]]
Output: 3
Example 3:

Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1
 

Constraints:

1 <= n <= 1000
0 <= trust.length <= 104
trust[i].length == 2
All the pairs of trust are unique.
ai != bi
1 <= ai, bi <= n

*/
class Solution {
    // much simpler solution, store trust counts in an array and
    // increment at position of i if i is trusted by someone, and decrement if i trusts someone
    // for judge, count[i] should be equal to n-1 as judge is trusted by all others and judge trusts no one
    // Time: O(n); only iterate over all elements twice
    // Space: O(n); need to store array of trust counts
    private int cleanerSolution(int n, int[][] trust){
        int[] count = new int[n+1];
        for(int i=0; i < trust.length; i++){
            count[trust[i][0]]--;
            count[trust[i][1]]++;
        }
        for(int i = 1; i <= n; i++){
            if(count[i] == n-1){
                return i;
            }
        }
        return -1;
        
    }
    // idea is to create a graph of with edge from a to [b, c] denoting that a is trusted by b and c and store the nodes in map
    // then iterate over the graph finding a node that has n-1 nodes in its trust list
    // instead of storing this list, probably just a count could have been stored as value in map & a set maintained for those
    // who trust someone so they can be ignored
    // Time: O(n ^ 2); in worst case, would have to iterate over all combinations
    // Space: O(n ^ 2); everyone could be in trust list of everyone else (almost)
    private int originalSolution(int n, int[][] trust){
        HashMap<Integer, List<Integer>> map = new HashMap();
        for(int i = 0; i < trust.length; i++){
            List list = map.getOrDefault(trust[i][1], new ArrayList());
            list.add(trust[i][0]);
            map.put(trust[i][1], list);
        }
        for(int i = 1; i <= n; i++){
	    // size n-1 means trusted by all others
            if(map.containsKey(i) && map.get(i).size() == n-1){
		// now need to check if these others are not trusted by i
                boolean isJudge = true;
                for(int j = 1; j <= n ; j++){
                    if (j == i){
                        continue;
                    }
                    if(map.containsKey(j) && map.get(j).contains(i)){
                        isJudge = false;
                        break;
                    }
                }
                if(isJudge){
                    return i;
                }
            }
        }
        return -1;
        
    }
    public int findJudge(int n, int[][] trust) {
        
        if(n==0)return -1;
	// only 1 person, shouldnt trust himself else not a judge
        if(n==1 && trust.length == 0) return 1;
	
        // return originalSolution(n, trust);
        return cleanerSolution(n,trust);
    }
}
