/*

LeetCode: 23. Merge k Sorted Lists

Hard

Link: https://leetcode.com/problems/merge-k-sorted-lists/

Topics: Linked List, Divide and Conquer, Heap

Acceptance: 35.3

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0)return null;       
        
        // Using a lambda for the comparator slowed runtime from 5 ms to 36 ms
        PriorityQueue<ListNode> pq= new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                return Integer.compare(o1.val,o2.val);    
            }
        });
        
        // Add first node of every list to min heap
        for(ListNode node : lists){
            if(node!=null) pq.add(node);
        }
        
        ListNode ans = new ListNode(0);
        ListNode trav = ans;
    
        while(!pq.isEmpty()){
            // Add smallest node to ans list
            trav.next = pq.poll();
            trav = trav.next;
            
            // Add next linked list node to heap
            if(trav.next!=null) pq.add(trav.next);
        }
        return ans.next;
    }
}