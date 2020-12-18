package io.github.aluria.engine.metadata.key;

import com.google.gson.reflect.TypeToken;

public class MetadataKey<T> {

  private final String id;
  private final TypeToken<T> type;

  public static <T> MetadataKey<T> newKey(String id, TypeToken<T> type) {
    return new MetadataKey<>(id, type);
  }

  public static <T> MetadataKey<T> newKey(String id, Class<T> clazz) {
    return new MetadataKey<>(id, clazz);
  }

  private MetadataKey(String id, TypeToken<T> type) {
    this.id = id;
    this.type = type;
  }

  private MetadataKey(String id, Class<T> clazz) {
    this(id, TypeToken.get(clazz));
  }

  public String getId() {
    return id;
  }

  public TypeToken<T> getType() {
    return type;
  }

  public T cast(Object object) {
    return (T) type.getRawType().cast(object);
  }

  public boolean isInstance(Object object) {
    return type.getRawType().isInstance(object);
  }
}
