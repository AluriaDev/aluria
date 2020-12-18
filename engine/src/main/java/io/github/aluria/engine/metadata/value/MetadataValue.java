package io.github.aluria.engine.metadata.value;

import java.util.concurrent.TimeUnit;

public interface MetadataValue<T> {

  static <T> MetadataValue<T> newWeakValue(T object) {
    return new WeakValue<>(object);
  }

  static <T> MetadataValue<T> newFixedValue(T object) {
    return new FixedValue<>(object);
  }

  static <T> MetadataValue<T> newExpiringValue(T object, long time, TimeUnit unit) {
    return new ExpiringValue<>(object, unit.toMillis(time));
  }


  T get();

  boolean canExpire();
}
