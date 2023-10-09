import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Kutumbh {

    class TreeNode {

        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
    }

    private List<List<Integer>> getLevelOrderTraversal(TreeNode root) {

        if(root == null) {
            return null;
        }

        List<List<Integer>> output = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentLevel = new ArrayList<>();

            while(size >= 0) {
                TreeNode current = q.poll();

                currentLevel.add(current.val);

                if(current.left != null) {
                    q.add(current.left);
                }

                if(current.right != null) {
                    q.add(current.right);
                }

                size -= 1;
            }

            output.add(currentLevel);
        }


        return output;
    }
}
