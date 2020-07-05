package com.wsjkk.leetcode.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
 *
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 *
 *输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 *
 *
 */
public class FindRestaurant {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<Integer,List<String>> map = new HashMap<>();
        for(int i = 0;i<list1.length;i++){
            for(int j  = 0;j<list2.length;j++){
                if(list1[i].equals(list2[j])){
                    if(!map.containsKey(i+j)){
                        map.put(i+j,new ArrayList<>());
                    }
                    map.get(i+j).add(list1[i]);
                }
            }
        }
        int min_index_sum = Integer.MAX_VALUE;
        for(int key:map.keySet()){
            min_index_sum = Math.min(min_index_sum,key);
        }
        String [] res = new String[map.get(min_index_sum).size()];

        return map.get(min_index_sum).toArray(res);
    }

    @Test
    public void test1(){
        String[] resultList = {"Shogun"};
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        Assert.assertEquals(resultList,findRestaurant(list1,list2));
    }

    @Test
    public void test2(){
        String[] resultList = {"Shogun"};
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"KFC", "Shogun", "Burger King"};
        Assert.assertEquals(resultList,findRestaurant(list1,list2));
    }



}
