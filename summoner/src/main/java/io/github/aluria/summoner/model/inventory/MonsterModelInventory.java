package io.github.aluria.summoner.model.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Data
@AllArgsConstructor
public final class MonsterModelInventory {

  private Map<EquipmentSlot, ItemStack> armorItems;
  private Map<ItemStack, Double> itemDropMap;

  public MonsterModelInventory() {
    this.armorItems = new HashMap<>();
    this.itemDropMap = new HashMap<>();
  }

  /**
   * Sorteia (de forma bem básica) um drop a partir dos
   * items que estão na map de drops.
   *
   * @return
   */
  public ItemStack getRandomDrop() {
    ItemStack found = null;

    for (Entry<ItemStack, Double> entry : itemDropMap.entrySet()) {
      ItemStack item = entry.getKey();
      Double chance = entry.getValue();

      if ((Math.random() * 100) <= chance) {
        return item;
      }
    }


    return found;
  }


  public void equip(LivingEntity entity) {
    EntityEquipment equipment = entity.getEquipment();

    ItemStack helmet = armorItems.get(EquipmentSlot.HEAD);
    if (helmet != null) {
      equipment.setHelmet(helmet);
    }

    ItemStack chestplate = armorItems.get(EquipmentSlot.CHEST);
    if (chestplate != null) {
      equipment.setChestplate(chestplate);
    }

    ItemStack leggings = armorItems.get(EquipmentSlot.LEGS);
    if (leggings != null) {
      equipment.setLeggings(leggings);
    }

    ItemStack boots = armorItems.get(EquipmentSlot.FEET);
    if (boots != null) {
      equipment.setBoots(boots);
    }

    ItemStack itemInHand = armorItems.get(EquipmentSlot.HAND);
    if (itemInHand != null) {
      equipment.setItemInHand(itemInHand);
    }
  }
}
