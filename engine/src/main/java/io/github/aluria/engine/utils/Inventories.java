package io.github.aluria.engine.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class Inventories {

  public static int getEmptySlots(Inventory inventory) {
    int count = 0;

    ItemStack[] contents = inventory.getContents();
    for (int i = 0, contentsLength = contents.length; i < contentsLength; i++) {
      ItemStack content = contents[i];
      if (content == null) {
        count++;
      }
    }

    return count;
  }

}
