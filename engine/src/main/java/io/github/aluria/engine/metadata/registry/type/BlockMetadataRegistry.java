package io.github.aluria.engine.metadata.registry.type;

import io.github.aluria.engine.metadata.map.MetadataMap;
import io.github.aluria.engine.metadata.registry.MetadataRegistry;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Vector;

public interface BlockMetadataRegistry extends MetadataRegistry<Location> {

  MetadataMap getMetadataMap(Block block);

  void clear(Block block);

}
