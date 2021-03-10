package leetcode_normal.数组;

/*

给你一个整数数组 nums ，
请你找出数组中乘积最大的连续子数组
（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

https://leetcode-cn.com/problems/maximum-product-subarray/submissions/
* */
public class 乘积最大子数组76 {

    public static void main(String[] args) {
        int[] nums = {2, -1, -1, -4, 2, 3};
        int[] my ={4,0,3,2};
        int[] nums2 = {5, 6, -3, 4, -3};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF, mn = minF;
//           maxF = Math.max(maxF * nums[i], nums[i]); //不考虑负数情况
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));//维护最大的值
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));//维护最小的值
            ans = Math.max(maxF, ans);
        }

        return ans;
    }

}
