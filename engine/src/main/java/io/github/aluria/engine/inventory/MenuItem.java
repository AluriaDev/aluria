package io.github.aluria.engine.inventory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuItem {


  /**
   * Consumer cancelado para re-utilização.
   */
  private static final Consumer<InventoryClickEvent> EMPTY_CONSUMER = event -> event.setCancelled(true);

  /**
   * Menu item vazio pra alocar items para re-utilização de memoria.
   */
  private static final MenuItem EMPTY_ITEM = new MenuItem(null, null);

  public static MenuItem getEmptyItem() {
    return EMPTY_ITEM;
  }

  public static MenuItem of(ItemStack itemStack){
    return new MenuItem(itemStack , EMPTY_CONSUMER);
  }

  public static MenuItem of(ItemStack itemStack , Consumer<InventoryClickEvent> consumer){
    return new MenuItem(itemStack , EMPTY_CONSUMER.andThen(consumer));
  }


  private ItemStack itemStack;
  private Consumer<InventoryClickEvent> consumer;
}
