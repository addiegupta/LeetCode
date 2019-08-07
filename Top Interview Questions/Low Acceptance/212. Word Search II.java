/*

LeetCode: 212. Word Search II

Hard

Link: https://leetcode.com/problems/word-search-ii/

Topics: Backtracking, Trie

Acceptance: 29.5

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
 

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.

 */
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        
        List<String> ans = new ArrayList();
        
        // Store all words in Trie
        TrieNode root = createTrie(words);

        // Start at every position in the matrix and look for any possible words in the Trie
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                dfs(board,root,i,j,ans);
            }
        }
        // ans has been populated in dfs backtracking
        return ans;
    }
   
    class TrieNode{
        TrieNode[] next = new TrieNode[26];
        // Save the entire string instead of using boolean to simplify recursion
        String word;
    }
    private TrieNode createTrie(String[] words){
        // add all words to trie and return it
        TrieNode root = new TrieNode();
        TrieNode node = root;
        for(String word: words){
            node = root;
            for(char c : word.toCharArray()){
                if(node.next[c-'a']==null)node.next[c-'a'] = new TrieNode();
                node = node.next[c-'a'];
            }
            node.word = word;
        }
        
        return root;
    }
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    private void dfs(char[][] board,TrieNode node, int i,int j,List<String> ans){
        
        // Out of bounds
        if(i<0 || j<0 || i>=board.length || j>= board[i].length)return;
        
        // Current character
        char c = board[i][j];

        // Visited or the word being formed does not exist in trie, no point in looking
        if(c=='#' || node.next[c-'a']==null)return;
        // go deep in trie
        node = node.next[c-'a'];
        
        // Found complete word
        if(node.word != null){
            ans.add(node.word);
            node.word = null; // to avoid adding duplicates to ans
        }
        // Mark as visited
        board[i][j] = '#';
        // Go in all 4 directions
        for(int x=0;x<4;x++){
            dfs(board,node,i+dirs[x][0],j + dirs[x][1],ans);
        }
        // Remove visited mark; backtracking
        board[i][j] = c;
    }
}