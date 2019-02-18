package Exercises.Others;

import java.util.HashMap;
import java.util.Map;
/*
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


上图是一个部分填充的有效的数独。

数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer>[] row = new Map[9];
        Map<Integer, Integer>[] col = new Map[9];
        Map<Integer, Integer>[] box = new Map[9];
        for (int i = 0; i < 9; i++) {
            row[i] = new HashMap<>();
            col[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int n = c - '0';
                int box_idx = (i / 3) * 3 + j / 3;
                row[i].put(n, row[i].getOrDefault(n, 0) + 1);
                col[j].put(n, col[j].getOrDefault(n, 0) + 1);
                box[box_idx].put(n, box[box_idx].getOrDefault(n, 0) + 1);

                if (row[i].get(n) > 1 || col[j].get(n) > 1 || box[box_idx].get(n) > 1)
                    return false;
            }
        }
        return true;
    }
}
