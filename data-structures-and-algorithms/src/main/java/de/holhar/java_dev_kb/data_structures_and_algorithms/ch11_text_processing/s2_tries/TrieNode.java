package de.holhar.java_dev_kb.data_structures_and_algorithms.ch11_text_processing.s2_tries;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
  private final Map<Character, TrieNode> children = new HashMap<>();
  private boolean endOfWord;

  Map<Character, TrieNode> getChildren() {
    return children;
  }

  boolean isEndOfWord() {
    return endOfWord;
  }

  void setEndOfWord(boolean endOfWord) {
    this.endOfWord = endOfWord;
  }
}
