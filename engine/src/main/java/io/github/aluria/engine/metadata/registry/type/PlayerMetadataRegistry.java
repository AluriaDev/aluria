package io.github.aluria.engine.metadata.registry.type;

import io.github.aluria.engine.metadata.key.MetadataKey;
import io.github.aluria.engine.metadata.map.MetadataMap;
import io.github.aluria.engine.metadata.registry.MetadataRegistry;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface PlayerMetadataRegistry extends MetadataRegistry<UUID> {

  MetadataMap getMetadataMap(Player player);

  void clear(Player player);

  <V> Map<Player, V> getAllWithKey(MetadataKey<V> key);
}
