package work.interview.tree;

import java.util.*;

public class BinaryTreeSort {

    //前序遍历 根，左，右
    //递归
    public void beforeSort1(TreeNode root) {
        while (root != null) {
            System.out.println(root.val);
            beforeSort1(root.left);
            beforeSort1(root.right);
        }
    }
    //前序遍历 根，左，右
    //非递归
    public List<Integer> leftSort2(TreeNode root) {
        List<Integer> integers = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                System.out.println(node.val);
                integers.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return integers;
    }
    //中序遍历
    //非递归 左，根，右
    public List<Integer> middleSort2(TreeNode root) {
        List<Integer> integers = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> sortedTree = new HashSet<>();
        if (root == null) {
            return integers;
        }
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null && !sortedTree.contains(node.left)) {
//                if (node.right != null) {
//                    stack.push(node.right)
//                }
                stack.push(node);
                stack.push(node.left);
                continue;
            } else {
                System.out.println(node.val);
                integers.add(node.val);
                sortedTree.add(node);
                if (node.right != null) {
                    stack.push(node.right);
                } else {

                }
            }
        }
        return integers;

    }

    //后序遍历
    //非递归 左,右,根
    public List<Integer>  backOrder2(TreeNode root) {
        List<Integer> integers = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> sortedTree = new HashSet<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            boolean putLeft = false;
            if (node.left != null && !sortedTree.contains(node.left)) {
                putLeft = true;
            }
            boolean putRight = false;
            if (node.right != null && !sortedTree.contains(node.right)) {
                putRight = true;
            }
            if (!putLeft && !putRight) {
                integers.add(node.val);
                System.out.println(node.val);
                sortedTree.add(node);
            } else {
                stack.push(node);
            }
            if (putRight) {
                sortedTree.add(node.right);
                stack.push(node.right);
            }
            if (putLeft) {
                sortedTree.add(node.left);
                stack.push(node.left);
            }
        }
        return integers;

    }
    //层次遍历
    //非递归
    public void levelOrder(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            List<TreeNode> treeNodes = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode tempNode = queue.poll();
                System.out.println(tempNode.val);
                if (tempNode.left != null) {
                    treeNodes.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    treeNodes.add(tempNode.right);
                }
            }
            queue.addAll(treeNodes);
        }
    }


    public static void main(String args[]) {
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode6.left = treeNode2;
        TreeNode treeNode8 = new TreeNode(8);
        treeNode6.right = treeNode8;
        TreeNode treeNode0 = new TreeNode(0);
        treeNode2.left = treeNode0;
        TreeNode treeNode4 = new TreeNode(4);
        treeNode2.right = treeNode4;
        TreeNode treeNode9 = new TreeNode(9);
        treeNode8.right = treeNode9;
        TreeNode treeNode1 = new TreeNode(1);
        treeNode0.right = treeNode1;
        TreeNode treeNode5 = new TreeNode(5);
        treeNode4.right = treeNode5;

//        new BinaryTreeSort().middleSort2(treeNode6);
//        new BinaryTreeSort().backOrder2(treeNode6);
        new BinaryTreeSort().levelOrder(treeNode6);

    }
}
