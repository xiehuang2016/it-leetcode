package hhh;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author xiehuang 11108901
 * @create 2021/3/29 10:06
 */
public class Hw {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Hw hw = new Hw();

//        // 最长子字符串
//        hw.maxSubStringLengthTest();

        // 二叉搜索树判定
//        hw.isValidBSTTest();
        hw.isValidBSTTest2();
    }

    /**
     * 二叉搜索树判定
     * 题目要看清楚：左子树只包含小于当前节点的数，只包含，意味着主语是集合
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     */
    public boolean isValidBST(TreeNode root) {
        // 遍历，树的遍历，独立树递进遍历 or 前中后序遍历，后者内存占用较小
        // 遍历树，尤其是有规律的数，优先x序遍历
        // x序遍历，第一步，先打印出来
        // 第二步，设置遍历结束条件
        return traverse(root);
    }

    public boolean traverse(TreeNode root) {
        if(root == null) return true;
        if(root.left != null) {
            traverse(root.left);
        }
        System.out.println(root.val);
        if(root.right != null) {
            traverse(root.right);
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST2(TreeNode root, long min, long max) {
        if(root == null) {
            return true;
        }
        if(root.val <= min || root.val >= max) {
            return false;
        }
        // 递归确实好理解，但是耗内存，不是最佳方案
        return isValidBST2(root.left, min, root.val) && isValidBST2(root.right, root.val, max);
    }

    public void isValidBSTTest() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode root1 = two;
        two.left = one;
        two.right = three;
        System.out.println(isValidBST(root1));
    }

    public void isValidBSTTest2() {
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode root1 = five;
        five.left = four;
        five.right = six;
        six.left = three;
        six.right = seven;
        System.out.println(isValidBST(root1));
    }

    /**
     * 完全匹配的最长子字符串长度
     */
    public int maxSubStringLength(String str1, String str2) {
        // 遍历思路，滑动窗口，过程复杂，暂时不用
        // 递归思路，完全匹配的最长子串，必定满足其顺序子串也满足完全匹配，完全匹配的特点是包含
        String minStr, maxStr;
        if(str1.length() < str2.length()) {
            minStr = str1;
            maxStr = str2;
        } else {
            minStr = str2;
            maxStr = str1;
        }
        HashSet<String> maybeRes = new HashSet<>();
        maybeRes.add(minStr);
        int res = calcSubLength(maybeRes, maxStr);
        return res;
    }

    private int calcSubLength(HashSet<String> maybeRes, String maxStr) {
        for(String maybe : maybeRes) {
            if(maxStr.contains(maybe)) {
                return maybe.length();
            }
        }
        if(maybeRes.iterator().next().length() == 1) {
            return 0;
        }

        HashSet<String> newMaybeRes = new HashSet<>();
        for(String maybe : maybeRes) {
            newMaybeRes.add(maybe.substring(0, maybe.length() - 1));
            newMaybeRes.add(maybe.substring(1));
        }
        return calcSubLength(newMaybeRes, maxStr);
    }

    public void maxSubStringLengthTest() {
        String str1 = "asccdef";
        String str2 = "ccde";
        System.out.println(maxSubStringLength(str1, str2) == 4);
        str1 = "ascdcdef";
        str2 = "GGcd";
        System.out.println(maxSubStringLength(str1, str2) == 2);
        str1 = "asccdef";
        str2 = "GH";
        System.out.println(maxSubStringLength(str1, str2) == 0);
    }

}
