package de.holhar.java_dev_kb.data_structures_and_algorithms._deprecated.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryTree {

    TreeNode root;

    public BinaryTree() {
    }

    public boolean add(int element) {
        if (root == null) {
            root = new TreeNode(element);
            return true;
        } else {
            return root.add(element);
        }
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(TreeNode node) {
        if (root == null) {
            return 0;
        } else {
            int count = 1;
            count += countNodes(root.right);
            count += countNodes(root.left);
            return count;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean search(int value) {
        return root.search(value);
    }

    public void inOrder() {
        if (root != null) {
            root.inOrder();
        }
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        }
    }
}

class TreeNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeNode.class);

    int element;
    TreeNode left;
    TreeNode right;

    public TreeNode(int element) {
        this.element = element;
    }

    public boolean add(int element) {
        if (right == null) {
            right = new TreeNode(element);
            return true;
        } else {
            if (left == null) {
                left = new TreeNode(element);
                return true;
            } else {
                return right.add(element);
            }
        }
    }

    public boolean search(int value) {
        if (element == value) {
            return true;
        }
        if (right != null) {
            if (right.element == value) {
                return true;
            } else {
                return right.search(value);
            }
        }
        if (left != null) {
            if (left.element == value) {
                return true;
            } else {
                return left.search(value);
            }
        }
        return false;
    }

    // each node is processed between subtrees
    public void inOrder() {
        if (left != null)
            left.inOrder();
        LOGGER.debug("{}", element);
        if (right != null)
            right.inOrder();
    }

    // each node is processed before either of its sub-trees
    public void preOrder() {
        LOGGER.debug("{}", element);
        if (left != null)
            left.preOrder();
        if (right != null)
            right.preOrder();
    }

    // each node is processed after subtrees traversal
    public void postOrder() {
        if (left != null)
            left.postOrder();
        if (right != null)
            right.postOrder();
        LOGGER.debug("{}", element);
    }
}
