package io.github.aluria.engine.metadata.registry.type;

import io.github.aluria.engine.metadata.map.MetadataMap;
import io.github.aluria.engine.metadata.registry.MetadataRegistry;
import org.bukkit.entity.Entity;

import java.util.UUID;

public interface EntityMetadataRegistry extends MetadataRegistry<UUID> {

  MetadataMap getMetadataMap(Entity entity);

  void clear(Entity entity);
}
