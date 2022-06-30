/*

LeetCode: 433. Minimum Genetic Mutation

Medium

Link: https://leetcode.com/problems/minimum-genetic-mutation/

Topics: Graph, Breadth first search, Hash Table, String

Acceptance: 47.0

 A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.

 

Example 1:

Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1
Example 2:

Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2
Example 3:

Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
Output: 3
 

Constraints:

start.length == 8
end.length == 8
0 <= bank.length <= 10
bank[i].length == 8
start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].

*/

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if(bank == null || bank.length == 0){
            return -1;
        }
        // use bfs, start from first gene
        // iterate over values in bank and if a value differs from curr node only by 1 then visit it next
        // will reach end eventually with shortest path due to bfs
        // alteranatively could also try out all possible combinations of 1 letter change in every 
        // position in 8 digit gene but since bank size is small (10) , i thought of doing this instead
        Queue<String> q = new LinkedList();
        Set<String> visited = new HashSet();
        q.offer(start);
        int level = 0;
        while(!q.isEmpty()){
            int len = q.size();
            while(len-- > 0){
                String curr = q.poll();
                if(visited.contains(curr))continue;
                visited.add(curr);
                if(curr.equals(end)){
                    return level;
                }
                for(int i = 0 ; i < bank.length; i++){
                    String b = bank[i];
                    boolean isValid = isValidMutation(curr, b);
                    if(isValid && !visited.contains(b)){
                        q.offer(b);
                    }
                }
            }
            level++;
        }
        return -1;
    }
    private boolean isValidMutation(String a, String b){
        int diff = 0;
        for(int i = 0 ; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                diff++;
                if(diff>1)return false;
            }
        }
        return diff == 1;
    }
}
