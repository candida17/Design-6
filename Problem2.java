// Time Complexity : O(1) as we store fixed number of senetences in list
// Space Complexity :O(M × L), where M = number of unique sentences and L = average length.
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//We use hashmap to store each sentence and its frequency
//Using trie we insert every sentence and at each trie node we maintain a list of size 3 (hot sentences)
//When user searches any term we return the top 3 results 
//When user enters # we add the seach term back to map and insert into the trie and clear the search box and return empty list to user
//the list is sorted in descending order having the highest freq at start
class AutocompleteSystem {
    HashMap<String, Integer> map;
    String search;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> top3Result;

        public TrieNode() {
            this.children = new TrieNode[128]; //include special char #
            this.top3Result = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - ' '] == null) {
                cur.children[c - ' '] = new TrieNode(); //insert a new word not present in trieNode
            }
            cur = cur.children[c - ' '];

            //add the word in the list if not already present
            List<String> list = cur.top3Result;

            if (!list.contains(word)) {
                list.add(word);
            }
            //sort the list to contain top 3 hot words
            Collections.sort(list, (a, b) -> {
                //freq of 2 words are same
                if (map.get(a).equals(map.get(b))) return a.compareTo(b);
                return map.get(b) - map.get(a); //in descending order
            });

            //check for the list size
            if (list.size() > 3) list.remove(list.size() - 1);
        }
    }

    private List<String> searchPrefix(String search) {
        TrieNode cur = root;
        for (char ch : search.toCharArray()) {
            if (cur.children[ch - ' '] == null) return new ArrayList<>();
            cur = cur.children[ch - ' '];
        }

        return cur.top3Result;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.root = new TrieNode();
        this.search = "";
        //put the sentences into the map along with thier frequencies
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]); //insert each sentence inside trie
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            search = "";
            return new ArrayList<>();

        }
        //normal word seach
        search += c; //append the char to search term
        return searchPrefix(search);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
