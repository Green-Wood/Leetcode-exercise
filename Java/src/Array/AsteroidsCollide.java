package Array;

import java.util.*;
/*
给定一个整数数组 asteroids，表示在同一行的行星。

对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。

找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。

示例 1:

输入:
asteroids = [5, 10, -5]
输出: [5, 10]
解释:
10 和 -5 碰撞后只剩下 10。 5 和 10 永远不会发生碰撞。
示例 2:

输入:
asteroids = [8, -8]
输出: []
解释:
8 和 -8 碰撞后，两者都发生爆炸。
示例 3:

输入:
asteroids = [10, 2, -5]
输出: [10]
解释:
2 和 -5 发生碰撞后剩下 -5。10 和 -5 发生碰撞后剩下 10。
示例 4:

输入:
asteroids = [-2, -1, 1, 2]
输出: [-2, -1, 1, 2]
解释:
-2 和 -1 向左移动，而 1 和 2 向右移动。
由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。

HINT: Stack
 */

public class AsteroidsCollide {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        for (int asteroid: asteroids) {
            if (asteroid > 0) {
                stack.push(asteroid);
            } else {
                boolean isLive = true;
                while (!stack.isEmpty() && isLive) {
                    if (stack.peek() + asteroid > 0) {
                        isLive = false;
                    } else if (stack.peek() + asteroid < 0) {
                        stack.pop();
                    } else {
                        stack.pop();
                        isLive = false;
                    }
                }
                if (isLive) ans.add(asteroid);
            }
        }
        while (!stack.isEmpty()) {
            ans.add(stack.pollLast());
        }
        int[] ret = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }


    private int[] shortVersion(int[] asteroids) {
        Deque<Integer> list = new ArrayDeque<>();
        for (int a: asteroids) {
            if (a > 0) {
                list.addLast(a);
            } else {
                while (!list.isEmpty() && list.getLast() > 0 && list.getLast() < -a) {     // 与前面所有的正向移动行星碰撞
                    list.pollLast();
                }
                if (!list.isEmpty() && list.getLast() == -a) {               // 抵消
                    list.pollLast();
                } else if (list.isEmpty() || list.getLast() < 0) {        // 碰撞完所有的正向行星
                    list.addLast(a);
                }
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AsteroidsCollide().asteroidCollision(new int[]{5, 10, -5})));
    }
}
