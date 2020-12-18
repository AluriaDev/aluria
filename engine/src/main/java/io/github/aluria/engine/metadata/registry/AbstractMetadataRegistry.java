package io.github.aluria.engine.metadata.registry;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.github.aluria.engine.metadata.map.MetadataMap;

public abstract class AbstractMetadataRegistry<K> implements MetadataRegistry<K> {

  protected final LoadingCache<K, MetadataMap> cache = CacheBuilder.newBuilder()
    .build(this.getLoader());

  @Override
  public MetadataMap getMetadataMap(K key) {
    return cache.getUnchecked(key);
  }

  @Override
  public void clear(K key) {
    cache.invalidate(key);
  }

  @Override
  public void cleanup() {
    cache.asMap().values().removeIf(MetadataMap::isEmpty);
  }

  private <K> CacheLoader<K, MetadataMap> getLoader() {
    return new CacheLoader<K, MetadataMap>() {
      @Override
      public MetadataMap load(K k) throws Exception {
        return MetadataMap.create();
      }
    };
  }
}
