package test;

public class ValidateBST {
	private static int ret;
    private static boolean flag;
    
    public static boolean isValidBST(TreeNode root) {
        flag = false;
        return testBST(root);
    }
    
    private static boolean testBST(TreeNode root) {
        if(root==null)
            return true;
        
        if(root.left != null && !testBST(root.left)) 
            return false;
        
        if(root.val > ret || !flag) {
            ret = root.val;
            flag = true;
        } else 
            return false;
        
        if(root.right != null && !testBST(root.right)) 
            return false;
        
        return true;
    }
    
    public static void main(String[] args) {
    	//[10,-2147483643,14,-2147483648,-2147483641,12]
    	TreeNode t = new TreeNode(10);
    	t.left = new TreeNode(-2147483643);
    	t.right = new TreeNode(14);
    	t.left.left = new TreeNode(-2147483648);
    	t.left.right = new TreeNode(-2147483641);
    	t.right.left = new TreeNode(12);
    	
    	System.out.println(isValidBST(t));
    }
}

class TreeNode {
	  int val;
	  TreeNode left;
	  TreeNode right;
	  TreeNode(int x) { val = x; }
}