package io.github.aluria.engine.metadata.map;

import com.google.common.collect.Maps;
import io.github.aluria.engine.metadata.key.MetadataKey;
import io.github.aluria.engine.metadata.value.MetadataValue;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MetadataMap {

  public static MetadataMap create(){
    return new MetadataMap();
  }

  private final Map<MetadataKey<?>, MetadataValue<?>> metadataMap;

  private MetadataMap(){
    metadataMap = Maps.newConcurrentMap();
  }

  public <T> boolean put(MetadataKey<T> key, MetadataValue<T> value) {
    return metadataMap.put(key, value) != null;
  }

  public <T> T getValue(MetadataKey<T> key) {
    return this.getValue(key, null);
  }

  public <T> T getValue(MetadataKey<T> key, T defaultValue) {
    MetadataValue<?> metadataValue = this.metadataMap.get(key);
    if (metadataValue == null) {
      return defaultValue;
    }

    Object rawValue = metadataValue.get();
    if (rawValue == null || !key.isInstance(rawValue)) {
      return defaultValue;
    }

    return key.cast(rawValue);
  }

  public Set<MetadataKey<?>> getKeys() {
    return metadataMap.keySet();
  }

  public Collection<MetadataValue<?>> getValues() {
    return metadataMap.values();
  }

  public boolean isEmpty(){
    metadataMap.values().removeIf(MetadataValue::canExpire);

    return metadataMap.isEmpty();
  }
}
