package 树;

public class 重建二叉树 {

    public static void main(String[] args) {
        int[]  pre={1,2,3,4,5,6,7};
        int[]   in={3,2,4,1,6,5,7};
//        TreeNode treeNode = dfs(pre, 0, pre.length-1, in, 0, in.length-1);
//        System.out.print(dfs(pre, 0, pre.length-1, in, 0, in.length-1));
        TreeNode treeNode = myDfs(pre, 0, pre.length-1, in, 0, in.length-1);

        System.out.print(treeNode);
    }

    public static TreeNode myDfs(int[] p,int pl,int pr,int[] in,int il,int ir){
        if (pl>pr || il >ir){
            return  null;
        }
        TreeNode tree = new TreeNode(p[pl]);
        int mid =0;
        for(int i=0;i<=ir;i++ ){
            if(tree.val==in[i]){
                mid =i;
                break;
            }
        }
        int leftCount = mid -il;
        int rightCount =ir -mid;

        tree.left=myDfs(p,pl+1,pl+leftCount,in,il,mid-1);
        tree.right=myDfs(p,pr-rightCount+1,pr,in,mid+1,ir);
        return  tree;
    }


    //
    public static TreeNode dfs(int[] p, int pl, int pr, int[] in, int il, int ir) {
        if (pr < pl || ir < il) {
            return null;
        }
        TreeNode root = new TreeNode(p[pl]);
        int count=0; //左孩子个数
        for(int i = il;i<=ir;i++){
            if(root.val == in[i]){
                count =i;
                break;
            }
        }

        int leftCount = count-il ;
        int rightCount = ir-count;
        root.left =dfs(p,pl+1,pl+leftCount,in,il,count-1);
        root.right=dfs(p,pr-rightCount+1,pr,in,count+1,ir);
        return root;
    }



    /**递归法---找出*/
    public  static TreeNode dfs1(int[] pre, int[] in, int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) { // 无左子树或右子树
            return null;
        }
        int val = pre[preL]; // 前序遍历序列中的第一个数即为根节点
        TreeNode tree = new TreeNode(val);
        int mid = 0; // 找到根节点在中序遍历中的位置
        for (int i = inL; i <= inR; i++) {
            if (in[i] == val) {
                mid = i;
                break;
            }
        }
        // 在中序遍历中，根节点把序列分为左右子树
        int leftCount = mid - inL;  // 左子树节点个数
        int rightCount = inR - mid; // 右子树节点个数
        // 分别递归构造左右子树
        tree.left = dfs1(pre, in, preL + 1, preL + leftCount, inL, mid - 1);
        tree.right = dfs1(pre, in, preR - rightCount + 1, preR, mid + 1, inR);
        return tree;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}