import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Artem Ogarkov at 10:25 on 14.11.2015.
 * artem.ogarkov@gmail.com
 */
public class A2_Max2Substring implements Runnable{
    class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
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
        public boolean insert(String word) {
            HashMap<Character, TrieNode> children = root.children;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode newNode;
                if (children.containsKey(c)) {
                    newNode = children.get(c);
                }
                else {
                    newNode = new TrieNode(c);
                    children.put(c, newNode);
                }
                children = newNode.children;
                if (i == word.length()-1) {
                    if (newNode.isLeaf) {
                        return true;
                    }
                    else {
                        newNode.isLeaf = true;
                    }
                }
            }
            return false;
        }
    }
    int n;
    String str;
    Trie trie;
    int findMaxSubs(int minLen, int maxLen) {
        int l = minLen, r = maxLen, m;
        while (l+1 < r) {
            //long i_before = System.currentTimeMillis();//---comp-------
            m = l + (r-l)/2;
            System.out.println(l + " " + m + " " + r);
            //System.out.println("Iteration at m = " + m);//---comp-------
            boolean findMatch = false;
            for (int i = 0; i < n - m + 1; i++) {
                if (trie.insert(str.substring(i, i + m))) {
                    findMatch = true;
                    break;
                }
            }
            if (findMatch) {
                l = m + 1;
            }
            else {
                r = m;
            }
            //System.out.println(System.currentTimeMillis() - i_before);//---comp-------
        }
        return r-1;
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
            int smallStep = (int) Math.sqrt(n);
            int smallerStep = (int) Math.sqrt(smallStep);
            int moveStartAtBigData = 0;
            if (n>26) {
                moveStartAtBigData = 1;
            }
            if (n>650) {
                moveStartAtBigData = 2;
            }
            if (n>15600) {
                moveStartAtBigData = 3;
            }
            int maxSubLen = findMaxSubs(moveStartAtBigData, smallerStep);
            if (maxSubLen < smallerStep-1) {
                System.out.println(maxSubLen);
            }
            else {
                maxSubLen = findMaxSubs(smallerStep-1, smallStep);
                if (maxSubLen < smallStep-1) {
                    System.out.println(maxSubLen);
                }
                else {
                    maxSubLen = findMaxSubs(smallerStep-1, smallStep);
                    System.out.println(findMaxSubs(smallStep-1, n));
                }
            }
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
