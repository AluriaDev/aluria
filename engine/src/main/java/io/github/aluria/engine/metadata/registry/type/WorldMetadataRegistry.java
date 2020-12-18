package io.github.aluria.engine.metadata.registry.type;

import io.github.aluria.engine.metadata.map.MetadataMap;
import io.github.aluria.engine.metadata.registry.MetadataRegistry;
import org.bukkit.World;

import java.util.UUID;

public interface WorldMetadataRegistry extends MetadataRegistry<UUID> {

  MetadataMap getMetadata(World world);

  void clear(World world);

}
