// Time Complexity : get() → O(1) check() → O(1) release() → O(1)
// Space Complexity :O(n) for set and queue
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//We use two data structures that is Hashset and linear data structure Queue
//For the range from 0 to max numbers given we store these in queue and set
//During get operation we assign the numbers from queue and also remove them from set meaning its been assigned to someone. If no numbers left in queue that means all are assigned
//For check, we check if the number to be checked is in set, if yes return true else return false
//for release, we release the given number by adding it back to queue and set meaning it is available for assignment
class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    public int get() {
        if (q.isEmpty())
            return -1;
        int cur = q.poll();
        set.remove(cur);
        return cur;
    }

    public boolean check(int number) {
        return set.contains(number);

    }

    public void release(int number) {
        if (!set.contains(number)) {
            q.add(number);
            set.add(number);
        }

    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
