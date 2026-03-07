import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left, right;
    
    TreeNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class RecoverBST {
    private TreeNode first, second, prev;
    
    // Inorder traversal to find swapped nodes
    private void inorder(TreeNode root) {
        if (root == null) return;
        
        inorder(root.left);
        
        // Find violations in sorted order
        if (prev != null && prev.data > root.data) {
            if (first == null) {
                first = prev;  // First violation
                second = root;
            } else {
                second = root;  // Second violation
            }
        }
        prev = root;
        
        inorder(root.right);
    }
    
    // Recover the BST by swapping values
    public void recoverTree(TreeNode root) {
        first = second = prev = null;
        inorder(root);
        
        // Swap the values of two nodes
        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
    }
    
    // Insert node in BST (for building tree)
    private TreeNode insert(TreeNode root, int data) {
        if (root == null) return new TreeNode(data);
        
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        
        return root;
    }
    
    // Inorder traversal to display tree
    private void displayInorder(TreeNode root) {
        if (root != null) {
            displayInorder(root.left);
            System.out.print(root.data + " ");
            displayInorder(root.right);
        }
    }
    
    // Manually swap two nodes to create corrupted BST
    private void corruptBST(TreeNode root, int val1, int val2) {
        TreeNode node1 = findNode(root, val1);
        TreeNode node2 = findNode(root, val2);
        
        if (node1 != null && node2 != null) {
            int temp = node1.data;
            node1.data = node2.data;
            node2.data = temp;
        }
    }
    
    // Find a node with given value
    private TreeNode findNode(TreeNode root, int val) {
        if (root == null || root.data == val) return root;
        
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        
        return findNode(root.right, val);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RecoverBST tree = new RecoverBST();
        TreeNode root = null;
        
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        
        System.out.print("Enter node values: ");
        for (int i = 0; i < n; i++) {
            root = tree.insert(root, sc.nextInt());
        }
        
        System.out.println("\nOriginal BST (Inorder):");
        tree.displayInorder(root);
        
        System.out.print("\n\nEnter two values to swap (corrupt BST): ");
        int val1 = sc.nextInt();
        int val2 = sc.nextInt();
        tree.corruptBST(root, val1, val2);
        
        System.out.println("\nCorrupted BST (Inorder):");
        tree.displayInorder(root);
        
        tree.recoverTree(root);
        
        System.out.println("\n\nRecovered BST (Inorder):");
        tree.displayInorder(root);
        
        sc.close();
    }
}
