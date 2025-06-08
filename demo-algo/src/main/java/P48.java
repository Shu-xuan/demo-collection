/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) continue;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 逐行翻转
        for (int i = 0; i < n; i++) {
            doReverse(matrix[i], 0, n-1);
        }
    }

    private void doReverse(int[] matrix, int left, int right) {
        while (left < right) {
            int temp = matrix[left];
            matrix[left++] = matrix[right];
            matrix[right--] = temp;
        }
    }

    public static void main(String[] args) {
        new P48().rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
