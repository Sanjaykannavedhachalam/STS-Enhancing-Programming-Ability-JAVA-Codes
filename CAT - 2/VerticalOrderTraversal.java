import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;
    TreeNode(int data) { this.data = data; }
}

public class VerticalOrderTraversal {
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            map.computeIfAbsent(p.hd, k -> new ArrayList<>()).add(p.node.data);
            if (p.node.left != null) q.add(new Pair(p.node.left, p.hd - 1));
            if (p.node.right != null) q.add(new Pair(p.node.right, p.hd + 1));
        }
        
        result.addAll(map.values());
        return result;
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
    
    class Pair {
        TreeNode node;
        int hd;
        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VerticalOrderTraversal vot = new VerticalOrderTraversal();
        
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        
        System.out.print("Enter level order (null for empty): ");
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            arr[i] = input.equals("null") ? null : Integer.parseInt(input);
        }
        
        TreeNode root = vot.buildTree(arr);
        List<List<Integer>> result = vot.verticalOrder(root);
        
        System.out.println("\nVertical Order Traversal:");
        for (List<Integer> column : result) {
            System.out.println(column);
        }
        
        sc.close();
    }
}
