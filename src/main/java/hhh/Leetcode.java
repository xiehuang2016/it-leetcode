package hhh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xiehuang 11108901
 * @create 2021/4/2 9:47
 */
public class Leetcode {

    public static void main(String[] args) {
        Leetcode leetcode = new Leetcode();
        // 全排测试
//        leetcode.permuteTest();

        

    }

    private void permuteTest() {
        int[] nums = {1, 2, 3};
        permute(nums);
        System.err.println(res);
    }

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 全排
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> track = new ArrayList<>();
        allPermute(nums, track);
        return res;
    }

    public void allPermute(int[] nums, List<Integer> track) {
        // 触发结束条件
        if(track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            track = new ArrayList<>();
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            allPermute(nums, track);
            track.remove(track.size() - 1);

        }
    }
}
