package offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
  @author xiehuang 11108901
  @create 2021/3/23 9:57
 */
public class Solution {

    public static void main(String[] args) {

    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null) return false;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int row = 0;
        int column = columns - 1;
        while(row < rows && column >= 0) {
            int val = matrix[row][column];
            if(val > target) {
                column--;
            } else if(val == target){
                return true;
            } else {
                row++;
            }
        }
        return false;
    }

    public TreeNode bstToGst(TreeNode root) {
        Integer c = 0;
        traverse(root, c);
        return root;
    }

    public void traverse(TreeNode root, Integer c) {
        if(root == null) return;
        if(root.right == null && root.left == null) {
            c += root.val;
            root.val = c;
            return;
        }
        traverse(root.right, c);
        // 中序处理
        c += root.val;
        root.val = c;
        traverse(root.left, c);
    }


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

}
