package io.github.aluria.engine.metadata.value;

import com.google.common.base.FinalizableWeakReference;

import java.lang.ref.WeakReference;

public class WeakValue<T> implements MetadataValue<T> {

  private final WeakReference<T> value;

  public WeakValue(T value) {
    this.value = new WeakReference<>(value);
  }

  @Override
  public T get() {
    return value.get();
  }

  @Override
  public boolean canExpire() {
    return value.get() == null;
  }
}
