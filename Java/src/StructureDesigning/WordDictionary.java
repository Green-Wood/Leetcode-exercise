package StructureDesigning;

/*
设计一个支持以下两种操作的数据结构：

void addWord(word)
bool search(word)
search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

示例:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
说明:

你可以假设所有单词都是由小写字母 a-z 组成的。
 */
public class WordDictionary {
    /** Initialize your data structure here. */
    private final int R = 256;
    private class Node{
        boolean isWord;
        Node[] next = new Node[R];
    }
    private Node root;
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        root = insert(word, 0, root);
    }

    private Node insert(String word, int depth, Node x){
        if (x == null) x = new Node();
        if (depth == word.length()){
            x.isWord = true;
            return x;
        }
        char c = word.charAt(depth);
        x.next[c] = insert(word, depth+1, x.next[c]);
        return x;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int depth, Node x){
        if (x == null) return false;
        if (depth == word.length()) return x.isWord;
        char c = word.charAt(depth);
        if (c == '.'){
            boolean isword = false;
            for (int i = 0; i < R; i++){
                if (x.next[i] != null){
                    isword = isword || search(word, depth+1, x.next[i]);
                }
            }
            return isword;
        } else {
            return search(word, depth+1, x.next[c]);
        }
    }

    public static void main(String[] args){
        WordDictionary dic = new WordDictionary();
        dic.addWord("bad");
        dic.addWord("bca");
        dic.addWord("dad");
        System.out.println(dic.search("bc."));
    }
}
