package de.holhar.java_dev_kb.katas.ctci.chapters.ch04;

public class BinarySearchTree {

    private TreeNode root;

    // Exercise 3
    public TreeNode createMinimalBST(int[] array, int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(array[mid]);
        n.left = createMinimalBST(array, start, mid - 1);
        n.right = createMinimalBST(array, mid + 1, end);
        return n;
    }

    public void createMinimalBST(int[] array) {
        root = createMinimalBST(array, 0, array.length - 1);
    }

    public TreeNode getRoot() {
        return root;
    }

    static class TreeNode {

        private final int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }
    }
}