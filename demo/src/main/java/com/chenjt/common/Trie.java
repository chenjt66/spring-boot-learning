package com.chenjt.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
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


    TrieNode() {
      this.content = null;
      this.isLeaf = false;
      this.children = new HashMap<>();
    }

    TrieNode(Character content) {
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

  private List<FilterResultElement> filterLongest(String inputString) {
    char[] inputs = inputString.toCharArray();
    List<FilterResultElement> ret = new ArrayList<>();
    int p2 = 0;
    int p3 = 0;
    while (p3 < inputs.length) {
      filterLongest(inputs, p2, ret);
      p3++;
      p2 = p3;
    }
    return ret;
  }

  private void filterLongest(char[] inputs, int startIndex, List<FilterResultElement> ret) {
    FilterResultElement element = filterLongest(root, inputs, startIndex, startIndex, new FilterResultElement());
    if (element.content != null && element.position != null)
      ret.add(element);
  }

  private FilterResultElement filterLongest(TrieNode curNode, char[] inputs, int originalIndex, int startIndex, FilterResultElement element) {
    if (startIndex >= inputs.length)
      return element;
    HashMap<Character, TrieNode> children = curNode.getChildren();
    Character character = inputs[startIndex];
    if (!children.containsKey(character))
      return element;
    TrieNode node = children.get(character);
    if (node.isLeaf()) {
      String word = String.copyValueOf(inputs, originalIndex, startIndex - originalIndex + 1);
      Integer[] positionIndex = new Integer[]{originalIndex, startIndex};
      element = new FilterResultElement(word, positionIndex);
    }
    return filterLongest(node, inputs, originalIndex, ++startIndex, element);
  }

  public String filterString(String content) {
    List<FilterResultElement> filterResult = filterLongest(content);
    if (filterResult.size() == 0)
      return content;
    List<Integer[]> intervals = new ArrayList<>(filterResult.size());
    for (FilterResultElement element : filterResult) {
      intervals.add(element.position);
    }
    List<Integer[]> mergeRet = mergeIntervals(intervals);
    mergeRet = negateIntervals(mergeRet, content.length());
    if (mergeRet == null)
      return null;
    StringBuilder sb = new StringBuilder();
    for (Integer[] interval : mergeRet) {
      String tmpStr = content.substring(interval[0], interval[1] + 1);
      sb.append(tmpStr);
    }
    return sb.toString();
  }

  private List<Integer[]> negateIntervals(List<Integer[]> intervals, int length) {
    List<Integer[]> ret = new ArrayList<>();
    if (intervals.size() == 1) {
      Integer[] arr = intervals.get(0);
      if (arr[0] == 0 && arr[1] == length - 1)
        return null;
      if (arr[0] == 0) {
        Integer[] element = new Integer[]{arr[1] + 1, length - 1};
        ret.add(element);
        return ret;
      }
      if (arr[1] == length - 1) {
        Integer[] element = new Integer[]{0, arr[0] - 1};
        ret.add(element);
        return ret;
      }
      Integer[] element1 = new Integer[]{0, arr[0] - 1};
      Integer[] element2 = new Integer[]{arr[1] + 1, length - 1};
      ret.add(element1);
      ret.add(element2);
      return ret;
    }
    for (int i = 0; i < intervals.size(); i++) {
      Integer[] arr = new Integer[2];
      Integer[] interval = intervals.get(i);
      if (i == 0) {
        if (interval[0] == 0)
          continue;
        else {
          arr[0] = 0;
          arr[1] = interval[0] - 1;
        }
      } else if (i == intervals.size() - 1) {
        Integer[] preInterval = intervals.get(i - 1);
        if (!preInterval[1].equals(interval[0])) {
          Integer[] tmp = new Integer[2];
          tmp[0] = preInterval[1] + 1;
          tmp[1] = interval[0] - 1;
          ret.add(tmp);
        }
        if (interval[1] == length - 1) continue;
        arr[0] = interval[1] + 1;
        arr[1] = length - 1;
      } else {
        Integer[] preInterval = intervals.get(i - 1);
        if (preInterval[1].equals(interval[0])) continue;
        arr[0] = preInterval[1] + 1;
        arr[1] = interval[0] - 1;
      }
      ret.add(arr);
    }
    return ret;
  }

  private List<Integer[]> mergeIntervals(List<Integer[]> intervals) {
    return merge(intervals);
  }

  private List<Integer[]> merge(List<Integer[]> intervals) {
    if (intervals.size() == 0)
      return intervals;
    intervals.sort(Comparator.comparingInt(o -> o[0]));
    Integer[] ii, jj;
    for (int i = 0; i < intervals.size(); i++) {
      ii = intervals.get(i);
      for (int j = i + 1; j < intervals.size(); j++) {
        jj = intervals.get(j);
        if (ii[1] >= jj[0]) {
          ii[1] = Math.max(jj[1], ii[1]);
          intervals.remove(j);
          j--;
        } else break;
      }
    }
    return intervals;
  }

//  public int[][] merge(int[][] intervals) {
//    List<Integer[]> list = new ArrayList<>();
//    for (int i = 0; i < intervals.length; i++){
//      Integer[] tmp = new Integer[2];
//      tmp[0] = intervals[i][0];
//      tmp[1] = intervals[i][1];
//      list.add(tmp);
//    }
//    List<Integer[]> ret = merge(list);
//    int[][] retarr = new int[ret.size()][2];
//    for (int i = 0; i < ret.size(); i++){
//      for (int j = 0; j < 2; j++){
//        retarr[i][j] = ret.get(i)[j];
//      }
//    }
//    return retarr;
//  }


//    public int[][] merge1(int[][] intervals) {
//    if (intervals.length == 0)
//      return intervals;
//    int rows = intervals.length;
//    Arrays.sort(intervals, new Comparator<int[]>() {
//      @Override
//      public int compare(int[] o1, int[] o2) {
//        return o1[0] - o2[0];
//      }
//    });
//
//    int[] ii, jj;
//    for (int i = 0; i < rows; i++){
//      ii = intervals[i];
//      for (int j = i + 1; j < rows; j++){
//        jj = intervals[j];
//        if (ii[])
//      }
//    }
//  }
}
