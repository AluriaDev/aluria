package io.github.aluria.engine.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MenuHolder implements InventoryHolder {

  private final UUID viewerId;
  private final Menu menu;

  @Deprecated
  @Override
  public Inventory getInventory() {
    return null;
  }
}
