package 递归和动态规划;

public class coins1 {
    public static void main(String[] args) {
        int[] arr = {5, 10, 25, 1};
        int aim = 1000;
        int coins = coins(arr, aim);
        System.out.println(coins);
    }


    public static int coins(int[] arr, int aim) {

        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i < aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }
}
