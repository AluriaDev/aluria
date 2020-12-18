package io.github.aluria.engine.utils.function;

import java.util.function.Consumer;

@FunctionalInterface
public interface UncheckedConsumer<T> extends Consumer<T> {

  void uncheckedAccept(T t);

  @Override
  default void accept(T t) {
    try {
      uncheckedAccept(t);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
