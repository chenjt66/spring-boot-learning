package com.chenjt.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chen jianting on 2019/5/23.
 */
public class Trie {
  private TrieNode root;

  @Data
  private static class TrieNode{
    private Character content;
    private boolean isLeaf;
    private HashMap<Character, TrieNode> children;


    public TrieNode() {
      this.content = null;
      this.isLeaf = false;
      this.children = new HashMap<>();
    }

    public TrieNode(Character content) {
      this.content = content;
      this.children = new HashMap<>();
      this.isLeaf = false;
    }

    public TrieNode(Character content, boolean isLeaf) {
      this.content = content;
      this.isLeaf = isLeaf;
    }

    public TrieNode(Character content, boolean isLeaf, HashMap<Character, TrieNode> children) {
      this.content = content;
      this.isLeaf = isLeaf;
      this.children = children;
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class FilterResultElement {
    private String content;
    private Integer[] position;
  }

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word){
    insert(this.root, word.toCharArray(), 0);
  }

  private void insert(TrieNode cur, char[] word, int startIndex) {
    if (startIndex == word.length){
      cur.setLeaf(true);
      return;
    }
    HashMap<Character, TrieNode> children = cur.getChildren();
    Character curChar = word[startIndex];
    if (children.containsKey(curChar)){
      insert(children.get(curChar), word, ++startIndex);
    }else {
      TrieNode newChildrenNode = new TrieNode(word[startIndex]);
      children.put(word[startIndex], newChildrenNode);
      insert(newChildrenNode, word, ++startIndex);
    }
  }

  public List<FilterResultElement> filter(String inputString){
    char[] inputs = inputString.toCharArray();
    List<FilterResultElement> ret = new ArrayList<>();
    int p2 = 0;
    int p3 = 0;
    while (p3 < inputs.length){
      filter(inputs, p2, ret);
      p3++;
      p2 = p3;
    }
    return ret;
  }
  private void filter(char[] inputs, int startIndex, List<FilterResultElement> ret){
    filter(root, inputs, startIndex, startIndex, ret);
  }

  private void filter(TrieNode curNode, char[] inputs, int originalIndex, int startIndex, List<FilterResultElement> ret) {
    if (startIndex >= inputs.length)
      return;
    HashMap<Character, TrieNode> children = curNode.getChildren();
    Character character = inputs[startIndex];
    if (!children.containsKey(character))
      return;
    TrieNode node = children.get(character);
    if (node.isLeaf()){
      String word = String.copyValueOf(inputs, originalIndex, startIndex - originalIndex + 1);
      Integer[] positionIndex = new Integer[]{originalIndex, startIndex};
      FilterResultElement element = new FilterResultElement(word, positionIndex);
      ret.add(element);
    }
    filter(node, inputs, originalIndex, ++startIndex, ret);
  }



}
