public class ProductionLineManager {

    // Method to calculate the minimum moves required to equalize the number of dresses
    public int minMovesToEqualize(int[] dresses) {
        int n = dresses.length;
        int totalDresses = 0;

        // Calculate the total number of dresses
        for (int dress : dresses) {
            totalDresses += dress;
        }

        // Check if the total number of dresses can be evenly distributed among the machines
        if (totalDresses % n != 0) {
            return -1; // Cannot be equally distributed
        }

        // Calculate the target number of dresses for each machine
        int target = totalDresses / n;
        int moves = 0;
        int[] prefixSum = new int[n + 1];

        // Calculate prefix sums for efficient computation
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + dresses[i - 1];
        }

        // Calculate the moves required to equalize the number of dresses
        for (int i = 0; i < n; i++) {
            int expected = target * (i + 1);
            int actual = prefixSum[i + 1];
            moves += Math.abs(expected - actual);
        }

        // Return half of the total moves as each move involves two dresses
        return moves / 2;
    }

    public static void main(String[] args) {
        ProductionLineManager equalizer = new ProductionLineManager();
        int[] dresses = {1, 0, 5};
        System.out.println("Minimum moves required: " + equalizer.minMovesToEqualize(dresses));
    }
}
