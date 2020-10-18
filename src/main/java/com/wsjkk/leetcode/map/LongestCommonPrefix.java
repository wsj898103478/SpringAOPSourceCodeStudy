package com.wsjkk.leetcode.map;

import org.junit.Test;

//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
public class LongestCommonPrefix {
    /**
     * 横向扫描解法（暴力解法）
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs == null){
            return "";
        }
        //将第一个数组作为最长公共前缀
        String prefix = strs[0];
        int count = strs.length;
        for(int i = 1;i<count;i++){
            prefix = longestCommonPrefix(prefix,strs[i]);
            if (prefix == ""){
                return "";
            }
        }
        return prefix;
    }

    /**
     * 两个字符串进行比较，得出最长公共前缀
     * @param prefix
     * @param str
     * @return
     */
    private String longestCommonPrefix(String prefix, String str) {
        int length = Math.min(prefix.length(),str.length());
        int index = 0;
        while(index < length && prefix.charAt(index) == str.charAt(index)){
            index ++;
        }
        return prefix.substring(0,index);
    }

    @Test
    public void test1(){
        String[] arr = {"flower","flow","flight"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String s = longestCommonPrefix.longestCommonPrefix(arr);
        System.out.println(s);
    }

    @Test
    public void test2(){
        String[] arr = {"dog","racecar","car"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String s = longestCommonPrefix.longestCommonPrefix(arr);
        System.out.println(s);
    }
}
