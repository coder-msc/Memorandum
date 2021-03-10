package leetcode_normal.数学数字;

public class 跳台阶 {
    public static void main(String[] args) {
        System.out.println(Jump2(6));
        System.out.println(Jump2(6));
        System.out.println(Jump3(6));
    }


    public static int Jump(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return Jump(n - 1) + Jump(n - 2);
    }

    public static int Jump2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int sum = 0;
        int one = 1, two = 2;
        for (int i = 3; i <= n; i++) {
            sum = one + two;
            one = two;
            two = sum;
        }
        return sum;
    }

    public static int Jump3(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
