import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class P54 {
    // 上右下左
    public static final int[][] DIR = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int m;
    public static int n;
    public static final int 已访问 = 101;

    public List<Integer> spiralOrder(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int total = m * n;
        int dir = 1;
        List<Integer> ans = new ArrayList<>();
        int row = 0;
        int col = 0;
        while (total > 0) {
            if (goOn(matrix, row, col, dir) || total == 1) {
                ans.add(matrix[row][col]);
                matrix[row][col] = 已访问;
                row += DIR[dir][0];
                col += DIR[dir][1];
                total--;
            } else {
                dir = (dir + 1) % DIR.length;
            }
        }
        return ans;
    }

    public boolean goOn(int[][] matrix, int row, int col, int dir) {
        int nextRow = row + DIR[dir][0];
        int nextCol = col + DIR[dir][1];
        return (nextRow < m && nextRow >= 0 && nextCol < n && nextCol >= 0) && matrix[nextRow][nextCol] != 已访问;
    }

    public static void main(String[] args) {
        List<Integer> list = new P54().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        list.forEach(System.out::println);
    }
}
