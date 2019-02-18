package Exercises.Others;

import java.util.HashMap;
import java.util.Map;

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
