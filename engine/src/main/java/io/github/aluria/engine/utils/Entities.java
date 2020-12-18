package io.github.aluria.engine.utils;

import net.minecraft.server.v1_8_R3.MathHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;

import java.util.*;

public class Entities {

  public static Entity getEntity(World world, UUID uniqueId) {
    return ((CraftWorld) world).getHandle()
      .getEntity(uniqueId)
      .getBukkitEntity();
  }

  public static Entity getEntity(UUID uniqueId) {
    for (World world : Bukkit.getWorlds()) {
      Entity entity = getEntity(world, uniqueId);
      if (entity != null) {
        return entity;
      }
    }

    return null;
  }

  public static List<Entity> getEntitiesAroundPoint(Location location, double radius) {
    List<Entity> entities = new ArrayList<>();
    World world = location.getWorld();

    int smallX = MathHelper.floor((location.getX() - radius) / 16.0D);
    int bigX = MathHelper.floor((location.getX() + radius) / 16.0D);
    int smallZ = MathHelper.floor((location.getZ() - radius) / 16.0D);
    int bigZ = MathHelper.floor((location.getZ() + radius) / 16.0D);

    for (int x = smallX; x <= bigX; x++) {
      for (int z = smallZ; z <= bigZ; z++) {
        if (world.isChunkLoaded(x, z)) {
          entities.addAll(Arrays.asList(world.getChunkAt(x, z).getEntities())); // Add all entities from this chunk to the list
        }
      }
    }


    Iterator<Entity> entityIterator = entities.iterator(); // Create an iterator so we can loop through the list while removing entries
    while (entityIterator.hasNext()) {
      if (entityIterator.next().getLocation().distanceSquared(location) > radius * radius) { // If the entity is outside of the sphere...
        entityIterator.remove(); // Remove it
      }
    }
    return entities;
  }
}
