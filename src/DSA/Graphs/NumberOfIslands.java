package DSA.Graphs;


// https://leetcode.com/problems/number-of-islands/description/
public class NumberOfIslands {

    static int numOfIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        int rows = grid.length;
        int columns = grid[0].length;

        for (int r=0; r<rows; ++r) {
            for (int c=0; c<columns; ++c) {
                if (grid[r][c] == '1') {
                    ++ count;
                    dfs(grid, r, c);
                }
            }
        }

        return count;
    }

    static void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int columns = grid[0].length;

        if (r < 0 || r >= rows || c < 0 || c >= columns || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';

        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);
    }

    static void main() {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(numOfIslands(grid));

        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(numOfIslands(grid1));
    }

}
