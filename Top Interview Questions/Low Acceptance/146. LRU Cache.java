/*

LeetCode: 146. LRU Cache

Medium

Link: https://leetcode.com/problems/lru-cache/

Topics: Design

Acceptance: 26.4


Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

// 2 is the capacity
LRUCache cache = new LRUCache(2 );


cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

// The idea is to use a doubly linked list to store the order of recently used key 
// and a hash table to store the key to node mapping for quick lookup in the linked list
class LRUCache {

    // Doubly linked list node
    class DLLNode{
        int key,val;
        DLLNode prev,next;
        DLLNode(int k,int v){
            this.key = k;
            this.val = v;
            this.prev = null;
            this.next = null;
        }
    }

    // Capacity of the LRU Cache
    int cap;

    // Dummy head and tail of the list
    DLLNode head;
    DLLNode tail;

    // HashMap for quick lookup of the node in linked list based on key
    Map<Integer,DLLNode> map;

    public LRUCache(int capacity) {
        this.cap = capacity;
       
        // Connect head and tail
        this.head = new DLLNode(0,0);
        this.tail = new DLLNode(-1,-1);
        head.next = tail;
        tail.prev = head;
       
        this.map = new HashMap<Integer,DLLNode>();
    }
    
    // O(1) get operation of a key that also places the element at start of linked list as it is most recently used
    public int get(int key) {

        // Not present in the list
        if(!map.containsKey(key))return -1;
        
        // Find the node
        DLLNode node = map.get(key);

        // Detach the node from its position and insert after dummy head
        removeNode(node);
        insertAfterHead(node);
        
        return node.val;
    }

    // O(1) operation to put a new key value pair or existing key new value pair in the list
    // Places the element at start of list as it is most recently used
    public void put(int key, int value) {

        // Already exists in list, update value and move to front
        if(map.containsKey(key)){
            
            // Update value
            DLLNode node = map.get(key);
            node.val = value;
            
            // Move to front
            removeNode(node);
            insertAfterHead(node);

            return;
        }
        
        // Does not exist in list, create new kv element
        DLLNode node = new DLLNode(key,value);
        map.put(key,node);
        insertAfterHead(node);

        // Excess of elements, remove LRU
        if(map.size()>cap){
            map.remove(tail.prev.key);
            removeNode(tail.prev);
        }
    }

    private void removeNode(DLLNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void insertAfterHead(DLLNode node){
       
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev=head;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */