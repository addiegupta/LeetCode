/*

LeetCode: 208. Implement Trie (Prefix Tree)

Medium

Link: https://leetcode.com/problems/implement-trie-prefix-tree/

Topics: Design, Trie

Acceptance: 39.6

Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */
class Trie {
    Trie[] children;
    boolean isEnd;
    
    /** Initialize your data structure here. */
    public Trie() {
        this.children = new Trie[26];
        this.isEnd = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        // Start at root node
        Trie node = this;
        int c;
        for(int i=0;i<word.length();i++){

            // Get index corresponding to this character
            c = word.charAt(i)-'a';

            // Create a trie node is it doesnt exist already
            if(node.children[c]==null){
                node.children[c] = new Trie();
            }

            // Switch current node to child node for char c 
            node = node.children[c];
        }
        // Mark this as an end node for the word inserted
        // Note if word is "cat" , the node corresponding to root->c->a->t will have isEnd = true
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int c;
        Trie node = this;
        for(int i=0;i<word.length();i++){
            c = word.charAt(i) - 'a';

            // Null node, word doesnt exist
            if(node.val[c]==null)return false;
            node = node.val[c];
        }
        // If isEnd is false then only this prefix exists for some other word
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int c;
        Trie node = this;
        for(int i=0;i<prefix.length();i++){
            c = prefix.charAt(i) - 'a';
            if(node.val[c]==null)return false;
            node = node.val[c];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */