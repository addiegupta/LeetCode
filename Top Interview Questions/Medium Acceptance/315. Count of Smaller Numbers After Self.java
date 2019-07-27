/*

LeetCode: 315. Count of Smaller Numbers After Self

Hard

Link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/

Topics: Binary Search, Sort, Binary Indexed Tree, Divide and Conquer, Segment Tree

Acceptance: 38.7

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

*/
class Solution {
    
    public List<Integer> countSmaller(int[] nums) {
        
        return BSTMethod(nums);
        
        // return mergeSortMethod(nums);
        
    }

    // A TreeNode contains value, left and right children and the count of elements on left
    class TreeNode{
        int val,leftCount;
        TreeNode left,right;
        TreeNode(int x){
            this.val = x;
            this.leftCount = 0;
            this.left = null;
            this.right = null;
        }
    }

    // Adds value to the BST
    private int addToTree(TreeNode root,int x,int count){

        if(root==null)return count;
        
        // x is larger than some value previously inserted ( hence count of smaller numbers after self should be incremented)
        if(root.val<x){
            // Place x as right child or if it exists already, then insert in right tree
            if(root.right == null){
                root.right = new TreeNode(x);
                return count+root.leftCount+1;
            }
            else return addToTree(root.right,x,count+root.leftCount+1);
        }
        // x is smaller than previously inserted value
        else {
            // Insert towards left
            root.leftCount++;
            if(root.left == null){
                root.left = new TreeNode(x);
                return count;
            }
            return addToTree(root.left,x,count);
        }
    }
    
    // Creates a BST of the elements of the array in reverse order (i.e. right to left)
    // Count of smaller numbers after self is found by the number of children to left in tree
    private List<Integer> BSTMethod(int[] nums){

        List<Integer> ans = new ArrayList();
        if(nums==null || nums.length ==0){
            return ans;
        }
        int n = nums.length;
        
        // Last element will have 0 children smaller to left
        TreeNode root = new TreeNode(nums[n-1]);
        ans.add(0);

        // Add the element in tree at correct position right to left
        for(int i=n-2;i>=0;i--){
            ans.add(addToTree(root,nums[i],0));
        }

        // Reverse the list to obtain correct left to right order 
        Collections.reverse(ans);
        return ans;
    }

    public class Pair{
        int ind,val;
        Pair(int i,int v){
            this.ind = i;
            this.val = v;
        }
    }
    // While sorting via merge sort, keep track of how many numbers are moved from right to left
    private List<Integer> mergeSortMethod(int[] nums){
        
        List<Integer> ans = new ArrayList();

        if(nums==null || nums.length==0)return ans;       

        // Keep index,val pairs for every element
        Pair[] arr = new Pair[nums.length];

        // Stores the count of smaller after self
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller,0);
        
        // Init pair arr
        for(int i=0;i<nums.length;i++)arr[i] = new Pair(i,nums[i]);

        mergeSort(arr,smaller);
        ans.addAll(Arrays.asList(smaller));
        return ans;
    }
    private Pair[] mergeSort(Pair[] arr,Integer[] smaller){
        // Sorted array
        if(arr.length<=1)return arr;
        
        // Standard merge sort steps
        int mid = arr.length/2;
        Pair[] left = mergeSort(Arrays.copyOfRange(arr,0,mid),smaller);
        Pair[] right = mergeSort(Arrays.copyOfRange(arr,mid,arr.length),smaller);

        // Tricky part
        // Iterate till both arrays are not exhausted
        for(int i=0,j=0;i<left.length || j<right.length;){

            // Element from left array is smaller i.e. element from right array is NOT moved to left
            if(j==right.length || i<left.length && left[i].val <=right[j].val){

                // Place element in merged arr
                arr[i+j] = left[i];

                // IMPORTANT--------------------
                // j is the count of elements that have been moved to left BEFORE the current element is moved
                // hence j elements are smaller than current element towards right
                smaller[left[i].ind] +=j;
                
                // Increment iterator
                i++;
            }
            else{
                // Place element in merged arr and increment iterator, no need to increment count of smaller
                arr[i+j] = right[j];
                j++;
            }
        }
        return arr;
    }
}