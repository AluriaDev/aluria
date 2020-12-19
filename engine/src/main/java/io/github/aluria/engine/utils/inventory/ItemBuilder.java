package io.github.aluria.engine.utils.inventory;

import io.github.aluria.engine.utils.text.Lore;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public final class ItemBuilder {

  private ItemStack item;

  public ItemBuilder(ItemStack item) {
    this.item = item;
  }

  public ItemBuilder(Material material, byte data, int amount) {
    this(new MaterialData(material, data).toItemStack(amount));
  }

  public ItemBuilder(Material material, int amount) {
    this(new ItemStack(material, amount));
  }

  public ItemBuilder applyItemStack(Consumer<ItemStack> consumer) {
    consumer.accept(item);
    return this;
  }

  public ItemBuilder applyItemMeta(Consumer<ItemMeta> consumer) {
    ItemMeta itemMeta = item.getItemMeta();

    consumer.accept(itemMeta);

    item.setItemMeta(itemMeta);
    return this;
  }

  public ItemBuilder name(String name) {
    return applyItemMeta(itemMeta -> itemMeta.setDisplayName(name));
  }

  public ItemBuilder lore(List<String> lore) {
    return applyItemMeta(itemMeta -> itemMeta.setLore(lore));
  }

  public ItemBuilder lore(String... lore) {
    return lore(Arrays.asList(lore));
  }

  public ItemBuilder lore(Lore lore) {
    return lore(lore.toStringArray());
  }

  public ItemStack build() {
    return item;
  }
}
