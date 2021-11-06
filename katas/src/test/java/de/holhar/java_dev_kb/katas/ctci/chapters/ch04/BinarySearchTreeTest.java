package de.holhar.java_dev_kb.katas.ctci.chapters.ch04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTreeTest {

    @Test
    void testCreateMinimalBST() {
        // given the following array
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        // when creating a minimal bst with the array
        BinarySearchTree bst = new BinarySearchTree();
        bst.createMinimalBST(array);

        // then
        assertEquals(4, bst.getRoot().getData());
        assertEquals(3, bst.getRoot().getLeft().getRight().getData());
        assertEquals(6, bst.getRoot().getRight().getData());
    }
}
