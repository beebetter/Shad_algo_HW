import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Artem Ogarkov at 10:25 on 14.11.2015.
 * artem.ogarkov@gmail.com
 */
public class A2_Max2Substring implements Runnable{
    class TrieNode {
        char c;
        //HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        TrieNode[] children = new TrieNode[26];
        boolean isLeaf;
        public TrieNode() {}
        public TrieNode(char c){
            this.c = c;
        }
    }

    public class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(int begin, int end) {

            //HashMap<Character, TrieNode> children = root.children;
            TrieNode[] children = root.children;
            for (int i = 0; i < end - begin; i++) {
                char c = str.charAt(begin + i);
                TrieNode newNode;
                //if (children.containsKey(c)) {
                //    newNode = children.get(c);
                if (children[c-'a'] != null) {
                    newNode = children[c-'a'];
                }
                else {
                    newNode = new TrieNode(c);
                    //children.put(c, newNode);
                    children[c-'a'] = newNode;
                }
                children = newNode.children;
                if (newNode.isLeaf && curMax < i+1) {
                    System.out.println(str.substring(begin, end));
                    curMax = i+1;
                }
                else {
                    newNode.isLeaf = true;
                }
            }
        }
    }

    int n;
    String str;
    Trie trie;
    int curMax;

    int findMaxSubs() {
        curMax = 0;
        for (int i = 1; i < n; i++) {
            trie.insert(i, n);
        }
        return curMax;
    }

    public static void main(String[] args) {
        new A2_Max2Substring().run();
    }

    @Override
    public void run() {
        trie = new Trie();
        try{
            //long before = System.currentTimeMillis();//---comp-------
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = reader.readLine().trim().split(" ");
            n = Integer.parseInt(tmp[0]);
            str = reader.readLine().trim();
            //System.out.println("Reading from input");//---comp-------
            //System.out.println(System.currentTimeMillis() - before);//---comp-------
            System.out.println(findMaxSubs());
            //System.out.println(System.currentTimeMillis() - before);//---comp-------
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            trie = null;
        }
    }

}
