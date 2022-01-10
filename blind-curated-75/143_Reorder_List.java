/*

LeetCode: 143. Reorder List

Medium

Link: https://leetcode.com/problems/reorder-list/

Topics: Linked List, Two Pointers, Stack, Recursion

Acceptance: 45.9

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.



Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]


Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 */

// for the desired reorder, first find midpoint of list, reverse the second half, and then interweave the 2 lists (1 node from each)
class Solution {
    private ListNode reverseList(ListNode root){
        ListNode temp, prev=null;
        while(root != null){
            temp = root.next;
            root.next = prev;
            prev = root;
            root = temp;
        }
        return prev;
    }
    private ListNode getMidPoint(ListNode root){
        ListNode slow = root, fast=root;
        while(fast != null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        return temp;
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode head2 = reverseList(getMidPoint(head));
        ListNode trav1 = head, trav2 = head2;
        ListNode temp1, temp2;
        while(trav1!=null && trav2!=null){
            temp1 = trav1.next;
            temp2 = trav2.next;
            trav1.next = trav2;
            trav2.next = temp1;
            trav1 = temp1;
            trav2 = temp2;
        }
    }
}}
