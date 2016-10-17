package moe.cuebyte.lib.Tree;

import java.util.ArrayList;

public class TreeNode<T> {
  private T data;
  private ArrayList<TreeNode<T>> children;

  public TreeNode(T data) {
    this.data = data;
    this.children = new ArrayList<>();
  }

  public T getData() {
    return data;
  }

  public ArrayList<TreeNode<T>> children() {
    return children;
  }

  public TreeNode<T> find(T x) {
    for (TreeNode node : children) {
      if (node.data.equals(x)) return node;
    }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(String.format("[%s", data.toString()));

    for (TreeNode node : children) {
      sb.append(node.toString());
    }
    sb.append("]");

    return sb.toString();
  }
}
