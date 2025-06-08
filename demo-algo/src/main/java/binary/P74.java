package binary;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m;) {
            int left = 0;
            int right = n - 1;
            if (i > 0 && matrix[i][0] > target) {
                return false;
            }
            while (i < m && matrix[i][right] < target) {
                i++;
            }
            if (i >= m) break;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else if (matrix[i][mid] > target) {
                    right = mid - 1;
                } else {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P74().searchMatrix(new int[][]{{1}}, 2));
    }
}
