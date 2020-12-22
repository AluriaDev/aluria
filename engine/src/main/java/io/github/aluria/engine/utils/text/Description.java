package io.github.aluria.engine.utils.text;

import com.google.common.base.Strings;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public final class Description implements Cloneable {

  private static final Description EMPTY_DESCRIPTION = of();

  public static Description emptyLore() {
    return EMPTY_DESCRIPTION;
  }

  private String[] content;

  public static Description of(String... content) {
    return new Description(content);
  }

  public static Description of(List<String> content) {
    return new Description(content.toArray(new String[content.size()]));
  }

  private Description(String[] content) {
    this.content = content;
  }

  private Description transform(Function<String, String> function) {
    for (int i = 0; i < content.length; i++) {
      content[i] = function.apply(content[i]);
    }

    return this;
  }

  public Description translateColors() {
    return transform(string -> translateAlternateColorCodes('&', string));
  }

  public Description setDefaultColor(String color) {
    return transform(string -> color + string);
  }

  public Description setDefaultColor(ChatColor color) {
    return this.setDefaultColor(color.toString());
  }

  public Description ident(int spaces) {
    final String space = Strings.repeat(" ", spaces);

    return transform(string -> space + string + space);
  }

  public Description replace(String placeHolder, String value) {
    return transform(string -> string.replace(placeHolder, value));
  }

  public Description replace(String placeHolder, Object value) {
    return replace(placeHolder, String.valueOf(value));
  }

  public Description append(String... lore) {
    content = ArrayUtils.addAll(content, lore);
    return this;
  }

  public Description append(Description description) {
    return append(description.toStringArray());
  }

  public String[] toStringArray() {
    return content;
  }

  public List<String> toStringList() {
    return Arrays.asList(content);
  }

  public Stream<String> stream() {
    return Arrays.stream(content);
  }

  @Override
  public Description clone() {
    return new Description(content);
  }
}
