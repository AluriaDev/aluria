package io.github.aluria.engine.metadata.registry;

import io.github.aluria.engine.metadata.map.MetadataMap;

public interface MetadataRegistry<T> {

  MetadataMap getMetadataMap(T key);

  void clear(T key);

  void cleanup();

}
