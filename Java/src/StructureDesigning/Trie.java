package StructureDesigning;
/*
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。
 */

public class Trie {
    private final int R = 256;
    private Node root;
    private class Node{
        boolean isWord;
        Node[] next = new Node[R];
    }

    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root = insert(word, root, 0);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(word, root, 0);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startWith(prefix, root, 0);
    }

    private Node insert(String word, Node node, int depth){
        if (node == null) node = new Node();
        if (depth == word.length()) {
            node.isWord = true;
            return node;
        }
        char c = word.charAt(depth);
        node.next[c] = insert(word, node.next[c], depth+1);
        return node;
    }

    private boolean search(String word, Node node, int depth){
        if (node == null) return false;
        if (depth == word.length()) return node.isWord;
        char c = word.charAt(depth);
        return search(word, node.next[c], depth+1);
    }

    private boolean startWith(String word, Node node, int depth){
        if (node == null) return false;
        if (depth == word.length()) return true;
        char c = word.charAt(depth);
        return startWith(word, node.next[c], depth+1);
    }
}
