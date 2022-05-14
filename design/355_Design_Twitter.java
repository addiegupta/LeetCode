/*

LeetCode: 409. Longest Palindrome

Medium

Link: https://leetcode.com/problems/design-twitter/

Asked in: Rupifi

Topics: Hash Table, Linked List, Design, Heap (Priority Queue)

Acceptance: 34.0

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 

Example 1:

Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 

Constraints:

1 <= userId, followerId, followeeId <= 500
0 <= tweetId <= 104
All the tweets have unique IDs.
At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.

 
*/

//not very optimal solution, getNewsFeed is very brute force, can do with priority queue optimisation
//havent improved this much, this is code completed within 25 mins in interview
// this problem is similar to merge k sorted lists
// can refer to this post https://leetcode.com/problems/design-twitter/discuss/82825/Java-OO-Design-with-most-efficient-function-getNewsFeed
// or this video https://www.youtube.com/watch?v=pNichitDD2E&ab_channel=NeetCode for better solutions
class Twitter {
    int count = 0;
    class Tweet{
        int tweetId;
        int time;
        public Tweet(int tweetId, int time){
            this.tweetId = tweetId;
            this.time = time;
        }
    }
    Map<Integer, Set<Integer>> followMap;
    Map<Integer, List<Tweet>> tweetMap;
    
    public Twitter() {
        this.followMap = new HashMap<Integer, Set<Integer>>();
        this.tweetMap = new HashMap<Integer, List<Tweet>>();
    }
    
    public void postTweet(int userId, int tweetId) {
        if(!tweetMap.containsKey(userId)){
            tweetMap.put(userId, new ArrayList());
        }
        tweetMap.get(userId).add(new Tweet(tweetId, count++));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Tweet> tweets = new ArrayList();
        addTweets(tweets, userId);
        if(followMap.containsKey(userId)){
            for(int followee: followMap.get(userId)){
                addTweets(tweets, followee);
            }
        }
        Collections.sort(tweets, (Tweet a, Tweet b)->{
            return b.time - a.time;
        });
        List<Integer> ans = new ArrayList();
        for(int i = 0; i < 10; i++){
            if(i>=tweets.size())break;
            ans.add(tweets.get(i).tweetId);
        }
        return ans;
    }
    private void addTweets(List<Tweet> tweets, int userId){
        if(!tweetMap.containsKey(userId)){
            return;
        }
        List<Tweet> t = tweetMap.get(userId);
        int c = 10;
        int i = t.size() - 1;
        while(i>=0 && c-- > 0){
            tweets.add(t.get(i));
            i--;
        }
    }
    
    public void follow(int followerId, int followeeId) {
        if(!followMap.containsKey(followerId)){
            followMap.put(followerId, new HashSet());
        }
        followMap.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(!followMap.containsKey(followerId)){
           return;
        }
        followMap.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
