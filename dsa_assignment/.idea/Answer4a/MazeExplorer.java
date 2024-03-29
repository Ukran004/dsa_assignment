import java.util.LinkedList;
import java.util.Queue;

public class MazeExplorer {
    public static int findMinimumMovesToCollectAllKeys(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();

        // Bitmask to represent collected keys
        int targetKeys = 0;

        // Starting coordinates
        int startX = 0, startY = 0;

        // Find the starting point and determine the target keys
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = grid[i].charAt(j);
                if (cell == 'S') {
                    startX = i;
                    startY = j;
                } else if (cell == 'E') {
                    // Set the bit for the exit door
                    targetKeys |= (1 << ('f' - 'a'));
                } else if (cell >= 'a' && cell <= 'f') {
                    // Set the bit for the key
                    targetKeys |= (1 << (cell - 'a'));
                }
            }
        }

        // Perform BFS to explore the maze
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[rows][cols][1 << 6]; // 1 << 6 represents the keys bitmask
        queue.offer(new int[] { startX, startY, 0, 0 }); // {x, y, keys, steps}

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int keys = current[2];
            int steps = current[3];

            // If all keys collected, return the steps
            if (keys == targetKeys) {
                return steps;
            }

            // Explore neighboring cells
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // Check bounds and wall
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX].charAt(newY) != 'W') {
                    char cell = grid[newX].charAt(newY);

                    // Check if the cell is accessible
                    if (cell == 'E' || cell == 'P' || (cell >= 'a' && cell <= 'f')
                            || (cell >= 'A' && cell <= 'F' && (keys & (1 << (cell - 'A'))) != 0)) {
                        int newKeys = keys;
                        // Collect the key if present
                        if (cell >= 'a' && cell <= 'f') {
                            newKeys |= (1 << (cell - 'a'));
                        }

                        // Explore the cell if not visited
                        if (!visited[newX][newY][newKeys]) {
                            visited[newX][newY][newKeys] = true;
                            queue.offer(new int[] { newX, newY, newKeys, steps + 1 });
                        }
                    }
                }
            }
        }

        // All possible moves explored and keys not collected
        return -1;
    }

    public static void main(String[] args) {
        String[] grid = { "SPaPP", "WWWPW", "bPAPB" };
        int result = findMinimumMovesToCollectAllKeys(grid);
        System.out.println("Minimum number of moves: " + result); // Output: 8
    }
}
