package Exercises.Others;

import java.util.Arrays;

/*
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").


示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False


注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间
 */
public class checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        s1 = sort(s1);
        for (int i = 0; i + s1.length() <= s2.length(); i++){
            String subString = s2.substring(i, i + s1.length());
            if (s1.equals(sort(subString)))
                return true;
        }
        return false;
    }

    private String sort(String s){
        char[] cArray = s.toCharArray();
        Arrays.sort(cArray);
        return new String(cArray);
    }

    public static void main(String[] args){
        System.out.println(new checkInclusion().checkInclusion("adc", "dcda"));
    }
}
