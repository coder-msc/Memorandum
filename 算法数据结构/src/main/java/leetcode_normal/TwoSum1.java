package leetcode_normal;

/*
* 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target
* 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
* 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
*
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
* */
public class TwoSum1 {
    public static void main(String[] args) {
        int[] arr = {0,2,3,5, 7, 11, 15};// 9
        int[] ints = twoSum1(arr, 9);
        System.out.println(ints);
    }

    public static int[] twoSum1(int[] arr, int tar) {
        for (int i = 0; i < arr.length; i++) {
            int t = tar - arr[i];
            int m=BinarySearch(arr,0,arr.length-1,t);
            if(m!=-1){
                return new int[]{i,m};
            }
        }
        return null;
    }

    public static int BinarySearch(int[] arr, int l, int r, int tar) {

        while (r >= l) {
            int mid = l + (r - l) / 2;
            if(arr[mid]==tar){
                return mid;
            }
            if(arr[mid]>tar){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return -1;
    }
}
