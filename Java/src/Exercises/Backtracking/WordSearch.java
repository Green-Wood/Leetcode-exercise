package Exercises.Backtracking;
/*
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word.toCharArray(), 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] word, int i, int x, int y) {         // 不可回头使用相同的字符
        if (i == word.length) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || word[i] != board[x][y]) return false;
        board[x][y] ^= 256;                                             // 用异或操作使board[x][y] 失效  'A' ^ 256 = 321
        boolean canFind = dfs(board, word, i + 1, x - 1, y) ||
                dfs(board, word, i + 1, x + 1, y) ||
                dfs(board, word, i + 1, x, y - 1) ||
                dfs(board, word, i + 1, x, y + 1);
        board[x][y] ^= 256;                                         //  用异或操作使board[x][y] 还原 'A' ^ 256 ^ 256 = 'A'
        return canFind;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        System.out.println(new WordSearch().exist(board, "ABCB"));
        System.out.println('A' ^ 256);
    }
}
