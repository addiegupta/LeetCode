/*

LeetCode: 127. Word Ladder

Medium

Link: https://leetcode.com/problems/word-ladder/

Topics: Breadth First Search, Graph(?)

Acceptance: 25.0

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */

class Solution {

    // BFS Pattern is followed in this problem
    // 2 words are connected nodes in the graph if they are different in one character only
    //------------
    // An optimization to this is to carry out double BFS
    // Start at begin as well as end word and meet midway
    // this is more efficient as beginword might have high number of neighbors and carry out many more BFS comparisons
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        // return doubleBFSMethod(beginWord,endWord,wordList);

        // Store all words
        Set<String> dict = new HashSet(wordList);
        
        // Store visited nodes
        Set<String> vis = new HashSet();
        
        // Store neighbors
        Queue<String> q = new LinkedList();

        q.add(beginWord);
        // Beginword is being counted in the transformation count as well
        // Len will keep count of transformations required i.e. distance from source node in the graph
        for (int len = 1; !q.isEmpty(); len++) {
            // only operate on nodes inserted in previous operation
            // this way all neighbors are operated on in 1 loop
            // and count of len results in answer eventually
            for (int i = q.size(); i > 0; i--) {

                // remove node from queue
                String w = q.poll();
                // ans found; return distance from source word
                if (w.equals(endWord)) return len;

                // Add all nodes which are formed by changing one letter only and which are present in the dictionary
                // AND which have not been visited
                // Thus this is more efficient than traversing all dictionary words and matching them with this word
                // as dictionary might have high number of words while in this only n*26 words will be formed where n is word length
                // e.g. if n is 5 and dictionary contains 10^5 words
                // then current approach operation count: 26 * 5
                //      matching every word operations count: 10^5 * 5
                // Another approach discussed in LeetCode editorial is to store all possible generic word formats for every word
                // e.g. dog is converted to *og, d*g and do* ; this will take n times more space
                for (int j = 0; j < w.length(); j++) {
                    char[] ch = w.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == w.charAt(j)) continue;
                        ch[j] = c;
                        String newWord = String.valueOf(ch);
                        // Add to vis if not present; if already present, then add method returns false
                        if (dict.contains(newWord) && vis.add(newWord)){    
                            q.offer(newWord);
                        } 
                    }
                }
            }
        }
        // Not found
        return 0;
    }

    private int doubleBFSMethod(String beginWord, String endWord, Set<String> words) {

        Set<String> wordList = new HashSet<String>(words);
        if(!wordList.contains(endWord))return 0;
        
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }
        
        return 0;
    }
                    
    
}