/*

LeetCode: 148. Sort List

Medium

Link: https://leetcode.com/problems/sort-list/

Topics: Linked List, Sort

Acceptance: 35.7

Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5


 */
 
 
 
 class Solution {
    private ListNode getMiddle(ListNode head){
        if(head==null)return null;
		
		// When fast reaches end of list, slow.next will be the head for second list
        ListNode slow=head,fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
			
		// Split the list in 2 halves
		ListNode temp = slow.next;
		slow.next = null;
        return temp;
    }
	private ListNode merge(ListNode left,ListNode right){
		// Base case
		if(left==null){
            return right;
        }
        if(right==null){
            return left;
        }
        
		// Iterative merge
        ListNode ans = new ListNode(0),trav=ans;
        
        while(left!=null && right != null){
            if(left.val < right.val){
                trav.next = left;
                left = left.next;
            }else{
                trav.next = right;
                right = right.next;
            }
            trav = trav.next;
        }
        if(left!=null) trav.next = left;
        if(right!=null)trav.next = right;
        return ans.next;
        
        // Recursive merge
		/*
			ListNode ans = null;
			if(left.val <= right.val){
				ans = left;
				ans.next = merge(left.next,right);
			}
			else{
				ans = right;
				ans.next = merge(left,right.next);
			}
			return ans;
        */
	}
    public ListNode sortList(ListNode head) {
		// Null node or single node
        if(head==null || head.next==null)return head;
		
		// Split list in 2 halves
		ListNode mid = getMiddle(head);
        
		// Sort left half
        ListNode left = sortList(head);
        
		// Sort right half
        ListNode right = sortList(mid);
		
		// Merge left and right halves
		return merge(left,right);
    }
}