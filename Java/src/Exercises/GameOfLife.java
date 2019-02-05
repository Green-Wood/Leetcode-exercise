package Exercises;

import java.io.IOException;

/*
根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

示例:

输入:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
输出:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
进阶:

你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class GameOfLife {

    private int[][] board;

    public GameOfLife(int m, int n, int initLives) {
        board = new int[m][n];
        for (int i = 0; i < initLives; i++) {
            int x = (int) (Math.random() * m);
            int y = (int) (Math.random() * n);
            board[x][y] = 1;
        }
    }

    private void evolution(int times) {
        for (int i = 0; i < times; i++) {
            nextState();
        }
    }

    public void nextState() {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = livesNum(m, n, i, j);
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2;
                }
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int livesNum(int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i-1, 0); x <= Math.min(i+1, m-1); x++) {
            for (int y = Math.max(j-1, 0); y <= Math.min(j+1, n-1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }

    public void display() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) System.out.print("● ");
                else System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void clearConsole() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(int evolutionTime, int loops, int sleepTime) {
        for (int i = 0; i < loops; i++) {
            System.out.println(i + ":");
            display();
            evolution(evolutionTime);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearConsole();
        }
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(25, 40, 600);
        game.play(5,100, 500);
    }
}
