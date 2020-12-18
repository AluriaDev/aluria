package io.github.aluria.engine.metadata.value;

public class FixedValue<T> implements MetadataValue<T> {

  private final T value;

  FixedValue(T value) {
    this.value = value;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public boolean canExpire() {
    return value == null;
  }
}
