package work.interview.tree;

import java.util.Stack;
import work.interview.tree.TreeNode;
/**
 * Given a binary tree, return the tilt of the whole tree.
 *
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 *
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * Example:
 * Input:
 *          1
 *        /   \
 *       2     3
 * Output: 1
 * Explanation:
 * Tilt of node 2 : 0
 * Tilt of node 3 : 0
 * Tilt of node 1 : |2-3| = 1
 * Tilt of binary tree : 0 + 0 + 1 = 1
 * Note:
 *
 * The sum of node values in any subtree won't exceed the range of 32-bit integer.
 * All the tilt values won't exceed the range of 32-bit integer.
 */

public class TreeTilt {

    public int findTilt(TreeNode root) {
        if (root == null) return 0;
        int leftSum = getSum(root.left);
        int rightSum = getSum(root.right);
        int curTilt = Math.abs(leftSum - rightSum);
        return curTilt + findTilt(root.left) + findTilt(root.right);
    }

    public int getSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.val + getSum(node.left) + getSum(node.right);
    }




    public int findTilt2(TreeNode root) {
        int tiltResult = 0;
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            int calValue = calTilt(node);
            tiltResult += calValue;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return tiltResult;
    }

    public int calTilt(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftValue = 0;
        if (node.left != null) {
            leftValue = node.left.val;
        }
        int rightValue = 0;
        if (node.right != null) {
            rightValue = node.right.val;
        }
        return Math.abs(leftValue - rightValue);
    }
}
