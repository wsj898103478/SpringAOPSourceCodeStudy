package com.wsjkk.leetcode.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 *
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 */
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        Object object = new Object();
        Map<Integer,Object> map = new HashMap<>();
        for(int i = 0;i<nums1.length;i++){
            for(int j = 0;j < nums2.length;j++){
                if(nums1[i] == nums2[j]){
                    map.put(nums1[i],object);
                }
            }
        }
        List list = new ArrayList<>();
        for(int key:map.keySet()){
            list.add(key);
        }
        Integer[] result = {};
        return null;
    }

    @Test
    public void test1(){
        int[] resultArray = {4,9};
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        Assert.assertEquals(resultArray,intersect(nums1,nums2));
    }

}
