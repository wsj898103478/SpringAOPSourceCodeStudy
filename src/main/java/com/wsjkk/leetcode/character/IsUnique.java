package com.wsjkk.leetcode.character;

import org.junit.Test;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 *
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 */
public class IsUnique {

    public boolean isUnique(String astr){

        boolean flag = true;
        if(astr.length() == 1||astr.length() == 0){
            return flag;
        }
        for(int i = 0;i<astr.length();i++){
//            System.out.println(astr.lastIndexOf(astr.charAt(i)));
            if(astr.indexOf(astr.charAt(i)) != astr.lastIndexOf(astr.charAt(i))){
//                flag=false;
                return !flag;
            }
        }
        return flag;
    }

    @Test
    public void test1(){
        IsUnique is = new IsUnique();
        String astr = "leetcode";
        is.isUnique(astr);
    }

}
