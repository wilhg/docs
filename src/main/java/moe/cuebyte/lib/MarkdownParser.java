package moe.cuebyte.lib;

import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class MarkdownParser {

  @Value("${my.path}")
  private String path;

  private String content;

  private PegDownProcessor processor = new PegDownProcessor();

  public MarkdownParser read(String filename) {
    try {
      content = new String(Files.readAllBytes(Paths.get(path, filename+".md")));
      return this;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String toHtml() {
    if (content == null) {
      throw new NullPointerException("Please read file first.");
    }
    return processor.markdownToHtml(content);
  }

//  public static void main(String[] args) {
//    MarkdownParser m = new MarkdownParser();
//    System.out.println(m.read("test").toHtml());
//  }
}
