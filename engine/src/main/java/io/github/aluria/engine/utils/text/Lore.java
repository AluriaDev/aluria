package io.github.aluria.engine.utils.text;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public final class Lore implements Cloneable {

  private static final Lore EMPTY_LORE = of();

  public static Lore emptyLore() {
    return EMPTY_LORE;
  }

  private String[] content;

  public static Lore of(String... content) {
    return new Lore(content);
  }

  public static Lore of(List<String> content) {
    return new Lore(content.toArray(new String[content.size()]));
  }

  private Lore(String[] content) {
    this.content = content;
  }

  private Lore transform(Function<String, String> function) {
    for (int i = 0; i < content.length; i++) {
      content[i] = function.apply(content[i]);
    }

    return this;
  }

  public Lore translateColors() {
    return transform(string -> translateAlternateColorCodes('&', string));
  }

  public Lore setDefaultColor(String color) {
    return transform(string -> color + string);
  }

  public Lore setDefaultColor(ChatColor color) {
    return this.setDefaultColor(color.toString());
  }

  public Lore ident(int spaces) {
    final String space = Strings.repeat(" ", spaces);

    return transform(string -> space + string + space);
  }

  public Lore replace(String placeHolder, String value) {
    return transform(string -> string.replace(placeHolder, value));
  }

  public Lore replace(String placeHolder, Object value) {
    return replace(placeHolder, String.valueOf(value));
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
  public Lore clone() {
    return new Lore(content);
  }
}
