package Exercises;

/*
南京大学 - 软件工程 - 赵文祺

答案：256
 */

public class HappyEnd {
    int count = 0;             // 用来计数能够攻略的总数

    public void run(){             // 运行并输出的方法
        findSolution(0, 0);
        System.out.println(count);
    }

    private void findSolution(int times, int favor){
        if (times < 9){
            if (times < 4){            // 前四个比较浪漫的场景
                findSolution(times+1, favor+2);
                findSolution(times+1, favor);
            } else {                     // 后五个普通的场景
                findSolution(times+1, favor+1);
                findSolution(times+1, favor-1);
            }
        } else {
            if (favor >= 5) count++;       // 用过所有场景后，如果好感度大于等于五则计数增加
        }
    }

    public static void main(String[] args){
        new HappyEnd().run();
    }
}
