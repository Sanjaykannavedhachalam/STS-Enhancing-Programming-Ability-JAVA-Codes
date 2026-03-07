import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;
    TreeNode(int data) { this.data = data; }
}

public class TreeViews {
    // Top View
    public void topView(TreeNode root) {
        if (root == null) return;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            map.putIfAbsent(p.hd, p.node.data);
            if (p.node.left != null) q.add(new Pair(p.node.left, p.hd - 1));
            if (p.node.right != null) q.add(new Pair(p.node.right, p.hd + 1));
        }
        System.out.println("Top View: " + map.values());
    }
    
    // Bottom View
    public void bottomView(TreeNode root) {
        if (root == null) return;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            map.put(p.hd, p.node.data);
            if (p.node.left != null) q.add(new Pair(p.node.left, p.hd - 1));
            if (p.node.right != null) q.add(new Pair(p.node.right, p.hd + 1));
        }
        System.out.println("Bottom View: " + map.values());
    }
    
    // Left View
    public void leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        leftViewUtil(root, 0, result);
        System.out.println("Left View: " + result);
    }
    
    private void leftViewUtil(TreeNode node, int level, List<Integer> result) {
        if (node == null) return;
        if (level == result.size()) result.add(node.data);
        leftViewUtil(node.left, level + 1, result);
        leftViewUtil(node.right, level + 1, result);
    }
    
    // Right View
    public void rightView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightViewUtil(root, 0, result);
        System.out.println("Right View: " + result);
    }
    
    private void rightViewUtil(TreeNode node, int level, List<Integer> result) {
        if (node == null) return;
        if (level == result.size()) result.add(node.data);
        rightViewUtil(node.right, level + 1, result);
        rightViewUtil(node.left, level + 1, result);
    }
    
    // Build tree from level order
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
        TreeViews tv = new TreeViews();
        
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        
        System.out.print("Enter level order (null for empty): ");
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            arr[i] = input.equals("null") ? null : Integer.parseInt(input);
        }
        
        TreeNode root = tv.buildTree(arr);
        System.out.println();
        tv.topView(root);
        tv.bottomView(root);
        tv.leftView(root);
        tv.rightView(root);
        
        sc.close();
    }
}
