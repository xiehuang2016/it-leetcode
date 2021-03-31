package hhh;

import java.util.*;

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
//        hw.isValidBSTTest2();

        // 不相邻约束条件求最值 等同于 leetcode 打家劫舍问题 https://leetcode-cn.com/problems/house-robber/
//        hw.touristTest();

        // 和为0的最长子数组长度， https://zhuanlan.zhihu.com/p/84708840
        // 类似题目 https://leetcode-cn.com/problems/subarray-sum-equals-k/
//        hw.subArraySumZeroTest();

        hw.subArraySumTest();

    }

    // 稍微通用一点的题目，k为其他值
    private void subArraySumTest() {
        int[] nums0 = {1,-1,5,-2,3};
        System.out.println(4 == subArraySum(nums0, 3));
    }

    private void subArraySumZeroTest() {
        int[] nums0 = {1,1,1,1};
        System.out.println(0 == subArraySumZero(nums0));
        int[] nums1 = {1,0,-1,1};
        System.out.println(3 == subArraySumZero(nums1));
        int[] nums2 = {1,0,1,0,-1,-1,1,-1,1};
        System.out.println(8 == subArraySumZero(nums2));
        int[] nums3 = {1,0,1,1};
        System.out.println(1 == subArraySumZero(nums3));
        int[] nums4 = {0,0,0,0};
        System.out.println(4 == subArraySumZero(nums4));
    }

    public int subArraySumZero(int[] nums) {
        return subArraySum(nums, 0);
    }

    public int subArraySum(int[] nums, int k) {
        // 思路： 子连续数据之和 遍历方式，可以得到暴力破解法
        // 好的数据结构，数据和，哈希表优化, 含义按现实的含义（不从0算起，只是计算数组取值时从0取）
        // key-元素个数，0表示没有元素个数，1代表第一个（不从0算起）
        // value-目标函数，最长子数组长度，Math.max更新
        HashMap<Integer, Integer> res = new HashMap<>();
        res.put(0, 0);
        int ans = 0;
        int preSum = 0;
        int diffSum;
        for(int i=1; i<nums.length + 1; i++) {
            preSum += nums[i-1];
            if(!res.containsKey(preSum)) {
                res.put(preSum, i);
            }
            diffSum = preSum - k;
            if(res.containsKey(diffSum)) {
                ans = Math.max(ans, i - res.get(diffSum));
            }
        }
        return ans;
    }

    public int subArraySum2(int[] nums, int k) {
        // 思路： 子连续数据之和 遍历方式，可以得到暴力破解法
        // 好的数据结构，数据和，哈希表优化, 含义按程序的含义（从0算起），不是很好理解
        // key-元素个数，1代表第0个（不从0算起），需要虚构一个0的值，顺推得到0,-1
        // value-目标函数，最长子数组长度，Math.max更新
        HashMap<Integer, Integer> res = new HashMap<>();
        res.put(0, -1);
        int ans = 0;
        int preSum = 0;
        int diffSum;
        for(int i=0; i<nums.length; i++) {
            preSum += nums[i];
            if(!res.containsKey(preSum)) {
                res.put(preSum, i);
            }
            diffSum = preSum - k;
            if(res.containsKey(diffSum)) {
                ans = Math.max(ans, i - res.get(diffSum));
            }
        }
        return ans;
    }

    public void touristTest() {
        int[] nums = {20, 2, 16, 18, 1};
        int res = tourist(nums);
        System.out.println(38 == res);
    }

    /**
     * 不相邻，求接收最多人数
     * @param nums 每个景区人数
     * @return
     */
    public int tourist(int[] nums) {
        int n = nums.length;
        if(n == 1) {
//            if(nums[0] <= 0 || nums[0] > 20) {
//                System.err.println("景区人数参数不在规定区间(0,20]");
//            }
            return nums[0];
        }
        if(n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 动态规划 缓存数组 思路: 设置目标函数，列出递归函数
        int[] dp = {nums[0], Math.max(nums[0], nums[1])};
        for(int i=2; i<n; i++) {
            dp[(i%2)] = Math.max(dp[(i-1)%2], dp[(i-2)%2] + nums[i]);
        }
        return dp[((n-1)%2)];
    }


    public long pre = Long.MIN_VALUE;
    /**
     * 二叉搜索树判定
     * 题目要看清楚：左子树只包含小于当前节点的数，只包含，意味着主语是集合
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     */
    public boolean isValidBST(TreeNode root) {
        // 遍历，树的遍历
        if(root == null) {
            return true;
        }
        if(!isValidBST(root.left)) {
            return false;
        }
        if(root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
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
        System.out.println(isValidBST2(root1));
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
