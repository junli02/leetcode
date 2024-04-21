package hot100.二叉树;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: jun
 * @Date: 2024/04/18/17:04
 */
public class Main {
    public static void main(String[] args) {
        //3,9,20,null,null,15,7
        TreeNode treeNode = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(5);
        treeNode.left = left;
        treeNode.right = right;
        left.left = new TreeNode(3);
        left.right = new TreeNode(4);
        //right.left = new TreeNode(15);
        right.right = new TreeNode(6);
        Main main = new Main();
        main.flatten(treeNode);
        System.out.println(treeNode);
        //System.out.println(main.levelOrder(treeNode));
    }

    //判断是否为二叉搜索树, 错误还要判断节点的是否大于右子树的最小值
    public boolean isValidBST(TreeNode root) {
        return isBST(root, root.val);
    }


    public boolean isBST(TreeNode root, int value) {
        if (root == null) return true;
        boolean left = root.left == null || root.val > root.left.val || root.left.val < value;
        boolean right = root.right == null || root.val < root.right.val || root.right.val > value;
        if (left && right) {
            boolean validBST = isValidBST(root.left);
            boolean validBST1 = isValidBST(root.right);
            if (validBST && validBST1) return true;
        } else {
            return false;
        }
        return false;
    }


    //230 二叉搜索树中的第k小的元素, 简单做法 迭代,中序遍历
    public int kthSmallest(TreeNode root, int k) {
        TreeNode result = null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        while (root != null ||!queue.isEmpty()) {
            //先遍历左边,到达左边最低点
            while (root != null) {
                queue.push(root);
                root = root.left;
            }
            //获取节点
            TreeNode node = queue.pop();
            k --;
            if(k == 0) {
                result = node;
                break;
            }
            //遍历右节点
            root = node.right;
        }
        return result.val;
    }

    //199 二叉树的右视图
    // 思考 层次遍历, 第一个右边的值,就是右视图
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeLast();
                if(i == 0) result.add(node.val);
                if(node.right != null) queue.addFirst(node.right);
                if(node.left != null) queue.addFirst(node.left);
            }
        }
        return result;
    }

    //114 将二叉树展开为链表
    //先序遍历的顺序 , 先根节点再左右节点

    List<Integer> list = new ArrayList<>();

    public void flatten(TreeNode root) {
        if(root  == null) return ;
        list.add(root.val);
        list(root.left);
        list(root.right);
        for (int i = 1; i < list.size(); i++) {
            root.left = null;
            root.right = new TreeNode(list.get(i));
            root = root.right;
        }
    }



    public void list(TreeNode node){
        if(node == null) return;
        list.add(node.val);
        list(node.left);
        list(node.right);
    }

}


