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

import javax.annotation.Nullable;
import java.util.Map;

@Getter
@Builder
public class MonsterModel {

  /**
   * Modelo do nome do monstro.
   */
  private static final String ENTITY_NAME_PATTERN = "&e%name% %behavior_color%[Nível %entity_level%]";


  /**
   * Chave do id do modelo da entidade.
   */
  private static final MetadataKey<Integer> MODEL_ID_KEY = MetadataKey.newKey("monster-model-id", int.class);

  /**
   * Chave do nível gerado para a entidade invocada.
   */
  private static final MetadataKey<Integer> ENTITY_LEVEL_KEY = MetadataKey.newKey("monster-level-id", int.class);


  private int id;
  private String displayName;
  private int minLevel, maxLevel;
  private int spawnTime;
  private BehaviorType behaviorType;
  private EntityType entityType;
  private MonsterModelInventory inventory;
  private SpawnRange spawnRange;

  public LivingEntity createNewEntity(Location location) {
    Preconditions.checkArgument(spawnRange.isInRange(location), "Entity spawn location its outside the spawn range.");
    Preconditions.checkArgument(entityType.isAlive(), "Entity type must be a living entity type.");

    LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
    entity.setCustomName(ChatColor.translateAlternateColorCodes('&', displayName));

    inventory.equip(entity);

    MetadataMap metadataMap = MetadataEngine.getEntityMetadataMap(entity);

    return entity;
  }
}
