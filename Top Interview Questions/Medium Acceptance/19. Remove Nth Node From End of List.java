/*

LeetCode: 19. Remove Nth Node From End of List

Medium

Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Topics: Linked List,Two Pointers

Acceptance: 34.3

Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
       
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode slow=dummy,fast=dummy;
        
        while(fast.next!=null){
            // Give fast pointer a lead of n nodes
            if(n>0){
                n--;
            }
            // when fast reaches end, slow.next will be the node to be removed
            else{
                slow=slow.next;
            }
            fast=fast.next;
        }
        // Remove nth node from end
        slow.next=slow.next.next;
        return dummy.next;
    }
}