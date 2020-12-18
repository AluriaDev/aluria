package io.github.aluria.engine.metadata;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import io.github.aluria.engine.metadata.key.MetadataKey;
import io.github.aluria.engine.metadata.map.MetadataMap;
import io.github.aluria.engine.metadata.registry.AbstractMetadataRegistry;
import io.github.aluria.engine.metadata.registry.MetadataRegistry;
import io.github.aluria.engine.metadata.registry.type.BlockMetadataRegistry;
import io.github.aluria.engine.metadata.registry.type.EntityMetadataRegistry;
import io.github.aluria.engine.metadata.registry.type.PlayerMetadataRegistry;
import io.github.aluria.engine.metadata.registry.type.WorldMetadataRegistry;
import io.github.aluria.engine.utils.Players;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MetadataEngine {

  private static final PlayerMetadataRegistry PLAYER_REGISTRY = new PlayerRegistry();
  private static final EntityMetadataRegistry ENTITY_REGISTRY = new EntityRegistry();
  private static final WorldMetadataRegistry WORLD_REGISTRY = new WorldRegistry();
  private static final BlockMetadataRegistry BLOCK_REGISTRY = new BlockRegistry();


  public static MetadataMap getPlayerMetadataMap(Player player) {
    return PLAYER_REGISTRY.getMetadataMap(player);
  }

  public static MetadataMap getEntityMetadataMap(Entity entity) {
    return ENTITY_REGISTRY.getMetadataMap(entity.getUniqueId());
  }

  public static MetadataMap getWorldMetadataMap(World world) {
    return WORLD_REGISTRY.getMetadata(world);
  }

  public static MetadataMap getBlockMetadataMap(Block block) {
    return BLOCK_REGISTRY.getMetadataMap(block);
  }


  public static PlayerMetadataRegistry getPlayerRegistry() {
    return PLAYER_REGISTRY;
  }

  public static EntityMetadataRegistry getEntityRegistry() {
    return ENTITY_REGISTRY;
  }

  public static WorldMetadataRegistry getWorldRegistry() {
    return WORLD_REGISTRY;
  }

  public static BlockMetadataRegistry getBlockRegistry() {
    return BLOCK_REGISTRY;
  }

  static final class BlockRegistry extends AbstractMetadataRegistry<Location> implements BlockMetadataRegistry {
    @Override
    public MetadataMap getMetadataMap(Block block) {
      return super.getMetadataMap(block.getLocation());
    }

    @Override
    public void clear(Block block) {
      super.clear(block.getLocation());
    }
  }

  static final class WorldRegistry extends AbstractMetadataRegistry<UUID> implements WorldMetadataRegistry {
    @Override
    public MetadataMap getMetadata(World world) {
      return super.getMetadataMap(world.getUID());
    }

    @Override
    public void clear(World world) {
      super.clear(world.getUID());
    }
  }

  static final class EntityRegistry extends AbstractMetadataRegistry<UUID> implements EntityMetadataRegistry {
    @Override
    public MetadataMap getMetadataMap(Entity entity) {
      return getMetadataMap(entity.getUniqueId());
    }

    @Override
    public void clear(Entity entity) {
      clear(entity.getUniqueId());
    }
  }

  static final class PlayerRegistry extends AbstractMetadataRegistry<UUID> implements PlayerMetadataRegistry {
    @Override
    public MetadataMap getMetadataMap(Player player) {
      return getMetadataMap(player.getUniqueId());
    }

    @Override
    public void clear(Player player) {
      clear(player.getUniqueId());
    }

    @Override
    public <V> Map<Player, V> getAllWithKey(MetadataKey<V> key) {
      Builder<Player, V> builder = ImmutableMap.builder();

      cache.asMap().forEach((id, map) -> {
        V value = map.getValue(key);
        if (value == null) {
          return;
        }

        Player player = Players.getPlayer(id);
        if (player != null) {
          builder.put(player, value);
        }
      });

      return builder.build();
    }
  }


}
