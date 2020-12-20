package io.github.aluria.engine.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public final class MenuListener implements Listener {

  @EventHandler(priority = EventPriority.LOWEST)
  public final void onPlayer(InventoryClickEvent event){
    Player player = (Player) event.getWhoClicked();
    Inventory inventory = event.getInventory();

    Menu menu = Menu.getMenuFromInventory(inventory);
    if(menu == null){
      return;
    }

    if(event.getCurrentItem() == null){
      return;
    }

    MenuItem item = menu.getItem(event.getRawSlot());
    if (item == null) {
      return;
    }

    item.getConsumer().accept(event);
  }
}
