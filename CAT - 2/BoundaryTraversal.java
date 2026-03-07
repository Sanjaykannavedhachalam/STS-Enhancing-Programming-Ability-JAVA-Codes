import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;
    TreeNode(int data) { this.data = data; }
}

public class BoundaryTraversal {
    
    public List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        if (!isLeaf(root)) result.add(root.data);
        addLeftBoundary(root.left, result);
        addLeaves(root, result);
        addRightBoundary(root.right, result);
        
        return result;
    }
    
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
    
    private void addLeftBoundary(TreeNode node, List<Integer> result) {
        while (node != null) {
            if (!isLeaf(node)) result.add(node.data);
            node = node.left != null ? node.left : node.right;
        }
    }
    
    private void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) return;
        if (isLeaf(node)) result.add(node.data);
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }
    
    private void addRightBoundary(TreeNode node, List<Integer> result) {
        List<Integer> temp = new ArrayList<>();
        while (node != null) {
            if (!isLeaf(node)) temp.add(node.data);
            node = node.right != null ? node.right : node.left;
        }
        Collections.reverse(temp);
        result.addAll(temp);
    }
    
    private TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        
        while (!q.isEmpty() && i < arr.length) {
            TreeNode curr = q.poll();
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BoundaryTraversal bt = new BoundaryTraversal();
        
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        
        System.out.print("Enter level order (null for empty): ");
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            arr[i] = input.equals("null") ? null : Integer.parseInt(input);
        }
        
        TreeNode root = bt.buildTree(arr);
        List<Integer> boundary = bt.boundaryTraversal(root);
        
        System.out.println("\nBoundary Traversal: " + boundary);
        
        sc.close();
    }
}
