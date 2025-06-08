/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < n && col >= 0) {
            if (matrix[row][col] == target) return true;
            while (matrix[row][col] > target && col > 0) {
                col--;
            }
            while (row < n && matrix[row][col] < target) {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P240().searchMatrix(new
                int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20));
    }
}
