package de.holhar.java_dev_kb.data_structures_and_algorithms._deprecated.tree;

public class BinarySearchTree {

    public BinaryTreeNode root;

    public BinarySearchTree() {
    }

    public boolean add(int element) {
        if (root == null) {
            root = new BinaryTreeNode(element);
            return true;
        } else {
            return root.add(element);
        }
    }
}

class BinaryTreeNode {

    public int element;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int element) {
        this.element = element;
    }

    public boolean add(int element) {
        if (element == this.element) {
            return false;
        } else if (element < this.element) {
            if (this.left == null) {
                left = new BinaryTreeNode(element);
                return true;
            } else {
                return left.add(element);
            }
        } else if (element > this.element) {
            if (this.right == null) {
                right = new BinaryTreeNode(element);
                return true;
            } else {
                return right.add(element);
            }
        }
        return false;
    }
}
