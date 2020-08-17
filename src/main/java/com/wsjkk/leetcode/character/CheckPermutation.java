package com.wsjkk.leetcode.character;

import org.junit.Test;

/**
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 *
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 *
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 *
 */
public class CheckPermutation {
    /**
     * 思路
     * 1、s1和s2互为字符重排的充分必要条件是：构成s1的字符种类数目和构成s2的字符种类数目相同，并且同种字符的数量也相同。
     * 2、迄今为止，国际共ASCII编码标准共编码了128个字符，并且每个字符都唯一的ASCII码。Unicode编码编了好多好多。
     * 3、设置128个桶，用ASCII码作为序号访问桶，统计每种字符的出现次数。
     * @param str1
     * @param str2
     * @return
     */
    public boolean checkPermutation(String str1,String str2){
        int str1Length = str1.length();
        int str2Length = str2.length();
        //长度不同肯定为false
        if(str1Length != str2Length){
            return false;
        }
        //长度相同时，根据ASCII码运用桶计法
        //s1作为原串，s2作为重排串
        int[] index = new int[128];
        for(int i =0;i<str1Length;i++){
            index[str1.charAt(i)]++; //统计原串中每种字符的数量
            index[str2.charAt(i)]--; //重排串用掉了字符
        }
        //如果互为字符重排串，那么array数组中所有元素都应该为0
        for(int i = 0;i<128;i++){
            if(index[i] != 0){
                return false;
            }
        }
        return true;
    }

    @Test
    public void test01(){
        String s1 = "a", s2 = "b";
        boolean flag = new CheckPermutation().checkPermutation(s1,s2);
    }

}
