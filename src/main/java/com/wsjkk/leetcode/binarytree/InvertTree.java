package com.wsjkk.leetcode.binarytree;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}

public class InvertTree {
    TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        //前序遍历位置
        //root 节点需要交换它的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        //让左右子节点继续翻转他们的子节点
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}
