/*

LeetCode: 234. Palindrome Linked List

Easy

Link: https://leetcode.com/problems/palindrome-linked-list/

Topics: Linked List, Two Pointers

Acceptance: 36.2

Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?

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
    // Reverse linked list and return new hode
    private ListNode reverseList(ListNode head){
        ListNode temp;
        ListNode prev = null;
        while(head!=null){
            temp = head.next;
            head.next = prev;
            prev = head;
            head=temp;
        }
        return prev;
    }
    public boolean isPalindrome(ListNode head) {
        if(head==null)return true;
        ListNode slow =head,fast=head;
        // Find mid point of listby traversing fast at 2x speed of slow
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        // odd nodes; let right half smaller
        if(fast!=null)slow=slow.next;
        
        // Reverse right half
        slow = reverseList(slow);
        fast = head;
        
        // Compare left half of list with right half
        while(slow!=null){
            if(slow.val!=fast.val)return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
}