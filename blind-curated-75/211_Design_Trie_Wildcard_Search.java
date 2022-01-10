/*

LeetCode: 211. Design Add and Search Words Data Structure

Medium

Link: https://leetcode.com/problems/design-add-and-search-words-data-structure/

Topics: String, Depth First Search, Design, Trie

Acceptance: 42.9

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.

*/

class WordDictionary {

    private class Node{
        Node[] children;
        boolean isEndOfWord;

        public Node(){
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }

    Node trie;
    public WordDictionary() {
        this.trie = new Node();
    }

    public void addWord(String word) {
        if(word == null || word.length() == 0){
            return;
        }

        int n = word.length();
        Node node = this.trie;
        for(int i = 0; i < n; i++){
            char c = word.charAt(i);
            int charIndex = c - 'a';

            if(node.children[charIndex] == null){
                node.children[charIndex] = new Node();
            }
            node = node.children[charIndex];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        return searchUtil(word, 0, this.trie);
    }
    private boolean searchUtil(String word, int index, Node node){
        if(node == null){
            return false;
        }
        if(word.length() == index){
            return node.isEndOfWord;
        }

        char c = word.charAt(index);
	// wildcard match, need to branch out in all letters and recurse
        if(c == '.'){
            boolean match = false;
            for(int i = 0;i < 26; i++){
                match = searchUtil(word, index + 1, node.children[i]);
                if(match){
                    break;
                }
            }
            return match;
        } else{
            Node nextNode = node.children[c-'a'];
            return nextNode != null && searchUtil(word, index + 1, nextNode);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
