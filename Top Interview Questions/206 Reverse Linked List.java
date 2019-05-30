/*

LeetCode: 206. Reverse Linked List   

Link: https://leetcode.com/problems/reverse-linked-list/

Topics: Linked List

Acceptance: 54.7

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?


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
   
    // Iterative Solution
    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode temp;
        while(head!=null){
            temp = head;
            head = head.next;
            temp.next = prev;
            prev = temp;
        }
        return prev;
    }

    // Recursive Solution
    public ListNode reverseListRecursive(ListNode head){
        // Recursive return new head of list
        if(head==null || head.next==null)return head;
        ListNode x = reverseListRecursive(head.next);
        
        // Reverse next pointer
        head.next.next = head;
        // Remove original next pointer
        head.next = null;
        return x;
    }


}