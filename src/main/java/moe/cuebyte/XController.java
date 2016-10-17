package moe.cuebyte;

import moe.cuebyte.lib.MarkdownIndex;
import moe.cuebyte.lib.MarkdownParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Paths;

@Controller
public class XController {

  @Autowired
  MarkdownParser parser;

  @Autowired
  MarkdownIndex index;

  @RequestMapping("/{path}.md")
  public String page(@PathVariable("path") String path, Model model) {
    model.addAttribute("index", index.init().toJSON());
    model.addAttribute("content", parser.read(path).toHtml());
    return "page";
  }

  @RequestMapping("/{dir}/{filename}.md")
  public String page(
          @PathVariable("dir") String dir,
          @PathVariable("filename") String filename,
          Model model) {
    model.addAttribute("index", index.init().toJSON());
    model.addAttribute("content", parser.read(Paths.get(dir, filename).toString()).toHtml());
    return "page";
  }
}
