package com.wsjkk.leetcode.map;
/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IsIsomorphicHelper {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character,Character> map = new HashMap<>(n);
        for(int i = 0;i<n;i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map.containsKey(c1)){
                if(map.get(c1) != c2){
                    return false;
                }
            }else {
                map.put(c1,c2);
            }
        }
        return true;
    }



    @Test
    public void testIsIsomorphic1(){
        String s = "egg", t = "add";
        boolean isomorphic = isIsomorphic(s, t);
        Assert.assertEquals(true,isIsomorphic(s, t)&&isIsomorphic(t,s));
    }

    @Test
    public void testIsIsomorphic2(){
        String s = "foo", t = "bar";
        boolean isomorphic = isIsomorphic(s, t);
        Assert.assertEquals(false,isIsomorphic(s, t)&&isIsomorphic(t,s));
    }

    @Test
    public void testIsIsomorphic3(){
        String s = "paper", t = "title";
//        boolean isomorphic = isIsomorphic(s, t);
        Assert.assertEquals(true,isIsomorphic(s, t)&&isIsomorphic(t,s));
    }

    @Test
    public void testIsIsomorphic4(){
        String s = "ab", t = "aa";
        Assert.assertEquals(false,isIsomorphic(s, t)&&isIsomorphic(t,s));
    }

}
