package tree;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P98 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    private boolean dfs(TreeNode root, Long min, Long max) {
        if (root == null) return true;
        int curVal = root.val;
        return curVal > min && curVal < max && dfs(root.left, min, (long) curVal) && dfs(root.right, (long) curVal, max);
    }

    public static void main(String[] args) {
        TreeNode seven = new TreeNode(7);
        TreeNode three = new TreeNode(3);
        TreeNode six = new TreeNode(6);
        TreeNode four = new TreeNode(4);
        TreeNode root = new TreeNode(5);
        root.left = four;
        root.right = six;
        six.left = three;
        six.right = seven;
        System.out.println(new P98().isValidBST(root));
    }
}
