package io.github.aluria.engine.metadata.value;

public class ExpiringValue<T> implements MetadataValue<T> {

  private final T value;
  private final long expiringTime;

  ExpiringValue(T value, long expiringTime) {
    this.value = value;
    this.expiringTime = System.currentTimeMillis() + expiringTime;
  }

  @Override
  public T get() {
    if (this.canExpire()) {
      return null;
    }

    return value;
  }

  @Override
  public boolean canExpire() {
    return System.currentTimeMillis() > expiringTime;
  }
}
