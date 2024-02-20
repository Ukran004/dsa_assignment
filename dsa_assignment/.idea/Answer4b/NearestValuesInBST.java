import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int value;
    TreeNode leftChild, rightChild;

    public TreeNode(int value) {
        this.value = value;
        this.leftChild = this.rightChild = null;
    }
}

public class NearestValuesInBST {

    public static List<Integer> findClosestValues(TreeNode rootNode, double targetValue, int numberOfClosestValues) {
        List<Integer> closestValuesList = new ArrayList<>();
        if (rootNode == null || numberOfClosestValues == 0)
            return closestValuesList;

        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode currentNode = rootNode;

        while (currentNode != null || !nodeStack.isEmpty()) {
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.leftChild;
            }

            currentNode = nodeStack.pop();
            if (closestValuesList.size() < numberOfClosestValues) {
                closestValuesList.add(currentNode.value);
            } else {
                double currentDifference = Math.abs(currentNode.value - targetValue);
                double maxDifference = Math.abs(closestValuesList.get(0) - targetValue);

                if (currentDifference < maxDifference) {
                    closestValuesList.remove(0);
                    closestValuesList.add(currentNode.value);
                } else {
                    break; 
                }
            }

            currentNode = currentNode.rightChild;
        }

        return closestValuesList;
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(4);
        rootNode.leftChild = new TreeNode(2);
        rootNode.rightChild = new TreeNode(5);
        rootNode.leftChild.leftChild = new TreeNode(1);
        rootNode.leftChild.rightChild = new TreeNode(3);

        double targetValue = 3.8;
        int numberOfClosestValues = 2;

        List<Integer> closestValues = findClosestValues(rootNode, targetValue, numberOfClosestValues);
        System.out.println("Closest values to " + targetValue + " are: " + closestValues); // Output: [4, 5]
    }
}
