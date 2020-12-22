package io.github.aluria.engine.utils.function;

import java.util.function.Function;

@FunctionalInterface
public interface UncheckedFunction<I, O> extends Function<I, O> {

  O transform(I input);

  @Override
  default O apply(I i) {
    try {
      return transform(i);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
