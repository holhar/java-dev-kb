package de.holhar.java_dev_kb.data_structures_and_algorithms.ch09_search_trees.s1_binary_search_trees;

/**
 * POI: https://www.softwaretestinghelp.com/binary-search-tree-in-java/
 */
class BinarySearchTree {

  // node class that defines BST node
  private static class BSTNode {

    int key;
    BSTNode left, right;

    public BSTNode(int data) {
      key = data;
      left = right = null;
    }
  }

  // BST root node
  BSTNode root;

  // constructor for BST => initial empty tree
  BinarySearchTree() {
    root = null;
  }

  // delete a node from BST
  void delete(int key) {
    root = delete(root, key);
  }

  // recursive delete function
  BSTNode delete(BSTNode root, int key) {
    // tree is empty
    if (root == null) {
      return root;
    }

    //traverse the tree
    if (key < root.key) {                     // traverse left subtree
      root.left = delete(root.left, key);
    } else if (key > root.key) {              // traverse right subtree
      root.right = delete(root.right, key);
    } else {                                  // node contains only one child
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      }
      // node has two children
      // get inorder successor (min value in the right subtree)
      root.key = minValue(root.right);
      // Delete the inorder successor
      root.right = delete(root.right, root.key);
    }
    return root;
  }

  int minValue(BSTNode root) {
    // initially minval = root
    int minval = root.key;
    // find minval
    while (root.left != null) {
      minval = root.left.key;
      root = root.left;
    }
    return minval;
  }

  // insert a node in BST
  void insert(int key) {
    root = insert(root, key);
  }

  // recursive insert function
  BSTNode insert(BSTNode root, int key) {
    // tree is empty
    if (root == null) {
      root = new BSTNode(key);
      return root;
    }
    // traverse the tree
    if (key < root.key)                     // insert in the left subtree
    {
      root.left = insert(root.left, key);
    } else if (key > root.key)              // insert in the right subtree
    {
      root.right = insert(root.right, key);
    }
    // return pointer
    return root;
  }

  // method for inorder traversal of BST
  void inorder() {
    inorder(root);
  }

  // recursively traverse the BST
  void inorder(BSTNode root) {
    if (root != null) {
      inorder(root.left);
      System.out.print(root.key + " ");
      inorder(root.right);
    }
  }

  boolean search(int key) {
    root = search(root, key);
    if (root != null) {
      return true;
    } else {
      return false;
    }
  }

  //recursive insert function
  BSTNode search(BSTNode root, int key) {
    // Base Cases: root is null or key is present at root
    if (root == null || root.key == key) {
      return root;
    }
    // val is greater than root's key
    if (root.key > key) {
      return search(root.left, key);
    }
    // val is less than root's key
    return search(root.right, key);
  }
}
