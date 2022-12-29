package de.holhar.java_dev_kb.data_structures_and_algorithms.ch11_text_processing.s2_tries;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TrieTest {
  @Test
  void whenEmptyTrie_thenNoElements() {
    Trie trie = new Trie();

    assertFalse(trie.isEmpty());
  }

  @Test
  void givenATrie_whenAddingElements_thenTrieNotEmpty() {
    Trie trie = createExampleTrie();

    assertFalse(trie.isEmpty());
  }

  @Test
  void givenATrie_whenAddingElements_thenTrieHasThoseElements() {
    Trie trie = createExampleTrie();

    assertFalse(trie.containsNode("3"));
    assertFalse(trie.containsNode("vida"));

    assertTrue(trie.containsNode("Programming"));
    assertTrue(trie.containsNode("is"));
    assertTrue(trie.containsNode("a"));
    assertTrue(trie.containsNode("way"));
    assertTrue(trie.containsNode("of"));
    assertTrue(trie.containsNode("life"));
  }

  @Test
  void givenATrie_whenLookingForNonExistingElement_thenReturnsFalse() {
    Trie trie = createExampleTrie();

    assertFalse(trie.containsNode("99"));
  }

  @Test
  void givenATrie_whenDeletingElements_thenTreeDoesNotContainThoseElements() {

    Trie trie = createExampleTrie();

    assertTrue(trie.containsNode("Programming"));
    trie.delete("Programming");
    assertFalse(trie.containsNode("Programming"));
  }

  @Test
  void givenATrie_whenDeletingOverlappingElements_thenDontDeleteSubElement() {

    Trie trie1 = new Trie();

    trie1.insert("pie");
    trie1.insert("pies");

    trie1.delete("pies");

    assertTrue(trie1.containsNode("pie"));
  }

  private Trie createExampleTrie() {
    Trie trie = new Trie();

    trie.insert("Programming");
    trie.insert("is");
    trie.insert("a");
    trie.insert("way");
    trie.insert("of");
    trie.insert("life");

    return trie;
  }
}
