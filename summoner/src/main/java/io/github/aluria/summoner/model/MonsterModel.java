package io.github.aluria.summoner.model;

import com.google.common.base.Preconditions;
import io.github.aluria.engine.metadata.MetadataEngine;
import io.github.aluria.engine.metadata.key.MetadataKey;
import io.github.aluria.engine.metadata.map.MetadataMap;
import io.github.aluria.engine.metadata.value.MetadataValue;
import io.github.aluria.summoner.model.area.SpawnRange;
import io.github.aluria.summoner.model.behavior.BehaviorType;
import io.github.aluria.summoner.model.inventory.MonsterModelInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Getter
@Builder
public class MonsterModel {

  private static final MetadataKey<MonsterModel> MODEL_KEY = MetadataKey.newKey("monster-model", MonsterModel.class);

  private int id;
  private String displayName;
  private int minLevel, maxLevel;
  private BehaviorType behaviorType;
  private EntityType entityType;
  private MonsterModelInventory inventory;
  private SpawnRange spawnRange;

  public LivingEntity createNewEntity(Location location) {
    Preconditions.checkArgument(spawnRange.isInRange(location) , "Entity spawn location its outside the spawn range.");
    Preconditions.checkArgument(entityType.isAlive(), "Entity type must be a living entity type.");

    LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
    entity.setCustomName(ChatColor.translateAlternateColorCodes('&', displayName));

    inventory.equip(entity);

    MetadataMap metadataMap = MetadataEngine.getEntityMetadataMap(entity);
    metadataMap.put(MODEL_KEY, MetadataValue.newWeakValue(this));

    return entity;
  }
}
