package com.wsjkk.leetcode.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//

/**
 * 思路：滑动窗口
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String str){
        //存放不重复的字符集
        Set<Character> occ = new HashSet<>();
        int n = str.length();
        //右指针，初始值为-1，相当于我们在字符串的左边界的左侧，还没开始移动
        int rk = -1,ans = 0;
        for(int i = 0;i < n;i++){
            if(i != 0){
                //左指针向右移动一格，移除一个字符
                occ.remove(str.charAt(i - 1));
            }
            //当下一个字符串未超过边界，且集合中不包含该元素，则不断移动右指针
            while(rk + 1 < n && !occ.contains(str.charAt(rk + 1))){
                //不断移动右指针
                occ.add(str.charAt(rk + 1));
                ++rk;
            }
            ans = Math.max(ans,rk - i + 1);

        }
        return ans;
    }

    @Test
    public void test1(){
        String str = "abcabcbb";
        int equal = 3;
        Assert.assertEquals(3,lengthOfLongestSubstring(str));
    }

    @Test
    public void test2(){
        String str = "bbbbb";
        int equal = 1;
        Assert.assertEquals(1,lengthOfLongestSubstring(str));
    }
    @Test
    public void test3(){
        String str = "pwwkew";
        int equal = 3;
        Assert.assertEquals(3,lengthOfLongestSubstring(str));
    }
}

