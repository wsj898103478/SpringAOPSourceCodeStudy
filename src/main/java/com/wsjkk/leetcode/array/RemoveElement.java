package com.wsjkk.leetcode.array;

import org.junit.Test;

//给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
//
// 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
//
// 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
//
//
//
// 示例 1:
//
// 给定 nums = [3,2,2,3], val = 3,
//
//函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
//
//你不需要考虑数组中超出新长度后面的元素。
//
//
// 示例 2:
//
// 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
//
//函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
//
//注意这五个元素可为任意顺序。
//
//你不需要考虑数组中超出新长度后面的元素。
//
//
//
//
// 说明:
//
// 为什么返回数值是整数，但输出的答案是数组呢?
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
//
// 你可以想象内部操作如下:
//
// // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
//int len = removeElement(nums, val);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
public class RemoveElement {
    /**
     * 拷贝覆盖
     * 主要思路是遍历数组nums，每次取出的数字变量为num，同时设置一个下标ans
     * 在遍历过程中如果出现数字与需要移除的值不相同时，则进行拷贝覆盖nums[ans] = num,ans自增1
     * 如果相同的时候，则跳过该数字不进行拷贝覆盖，最后ans即为新的数组长度
     * 这种思路在移除元素较多时更适合使用，最极端的情况是全部元素都需要移除，遍历一遍即可
     * 时间复杂度为O(n)，空间复杂度为O(1)
     * @param nums
     * @param val
     * @return
     */
    public int removeElementSolutionOne(int[] nums, int val) {
        int ans = 0;
        for(int num:nums){
            if(num != val){
                nums[ans] = num;
                ans++;
            }
        }
        return ans;
    }

    public int removeElementSolutionTwo(int[] nums, int val) {
        int ans = nums.length;
        for(int i = 0;i < ans;){
            if(nums[i] == val){
                System.out.println(i);
                nums[i] = nums[ans - 1];
                ans--;
            }else {
                i++;
            }
        }
        return ans;
    }

    @Test
    public void testSolutionOne(){
        int[] nums = {2,4,5,3};
        RemoveElement re = new RemoveElement();
        re.removeElementSolutionOne(nums,3);
    }

    @Test
    public void testSolutionTwo(){
        int[] nums = {3,2,2,3};
        RemoveElement re = new RemoveElement();
        re.removeElementSolutionTwo(nums,3);
    }

    @Test
    public void testSolutionThree(){
        for(int i = 0;i <10;){
            System.out.println(i);
        }
    }
}
