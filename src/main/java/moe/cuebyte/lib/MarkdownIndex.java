package moe.cuebyte.lib;

import moe.cuebyte.lib.Tree.Tree;
import moe.cuebyte.lib.Tree.TreeNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarkdownIndex {

  @Value("${my.path}")
  private String path;

  private List<String> files;

  public MarkdownIndex init() {
    try {
      this.files = Files.walk(Paths.get(path), 2)
              .map(Path::toString)
              .filter(x -> !x.contains(File.separator+'.') && x.endsWith(".md"))
              .collect(Collectors.toList());
      return this;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String toJSON() {
    Tree<String> tree = new Tree<>("root");

    files.stream()
            .map(s -> s.substring(path.length()))
            .forEach(s -> {
              String[] x = s.split(File.separator);
              TreeNode<String> node = tree.getRoot().find(x[0]);
              if (node == null) {
                node = new TreeNode<>(x[0]);
                tree.getRoot().children().add(node);
              }
              if (x.length == 2) {
                node.children().add(new TreeNode<>(x[1]));
              }
            });
    return tree.toJSON();
  }
}
