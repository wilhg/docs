package moe.cuebyte.lib.Tree;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Tree<T> {
  private TreeNode<T> root;
  private T data;

  public TreeNode<T> getRoot() {
    return root;
  }

  public Tree(T data) {
    this.data = data;
    root = new TreeNode<T>(data);
  }

  @Override
  public String toString() {
    return toJSON();
  }

  public String toJSON() {
    ObjectMapper map = new ObjectMapper();
    ArrayNode mapRoot = map.createArrayNode();

    for (TreeNode<T> node : this.root.children()) {
      mapRoot.add(node.getData().toString());
      if (node.children().size() != 0) {
        ArrayNode arrNode = mapRoot.addArray();
        for (TreeNode<T> subNode : node.children()) {
          arrNode.add(subNode.getData().toString());
        }
      }
    }
    return mapRoot.toString();
  }
}