package leetcode_normal.数组;

/*
 * 一个矩阵中只有0和1  每个位置都可以和自己的上下左右 四个位置相连
 * 如果一片1 连在一起，这个部分叫做一个岛  求一个矩阵中有几个岛
 *
 * 举例
 *   0 0 1 0 1 0
 *   1 1 1 0 1 0
 *   1 0 0 1 0 0
 *   0 0 0 0 0 0
 *
 * 这个矩阵中有三个岛
 * */
//并查集 求法  多CPU处理 并行
/*
将二维数组区域划分成多个区域 对每个区域求感染区域 将区域特殊标做标记 不同区域标记不同
多个区域算出来有几个岛之后  对边界进行合并  相同标记的合并只保留一个区域
多CUP并行处理，能加快计算速度
* */


//使用常规解法  不使用并查集
public class 岛问题88 {
    public static void main(String[] args) {
        String  AA="12432432";
        String[] split = AA.split("");
//boolean Boolean
        char[] chars = AA.toCharArray();
        System.out.println(chars);
        int[][] m1 = {
                {0, 0, 1, 0, 1, 0},
                {1, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int alent = alent(m1);
        System.out.println(alent);
    }

    public static int alent(int[][] m) {
        if (m.length == 0) {
            return 0;
        }
        int M = m.length;
        int N = m[0].length;
        int res = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {  //循环所有
                if (m[i][j] == 1) { //如果有岛屿 那么数量++  然后将这一片都给感染掉  下次遍历的时候 就不进行岛屿数量++操作了
                    res++;
                    infect(m, i, j, M, N); //感染函数
                }
            }
        }
        return res;
    }

    private static void infect(int[][] m, int i, int j, int M, int N) {
        if (i < 0 || i >= M || j < 0 || j >= N || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        infect(m, i + 1, j, M, N);
        infect(m, i - 1, j, M, N);
        infect(m, i, j + 1, M, N);
        infect(m, i, j - 1, M, N);
    }

}
