package io.github.aluria.engine.inventory;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.bukkit.Bukkit.createInventory;

@Getter
@EqualsAndHashCode
public class Menu {

  public static Menu getMenuFromInventory(Inventory inventory) {
    InventoryHolder holder = inventory.getHolder();
    if (!(holder instanceof MenuHolder)) {
      return null;
    }

    return ((MenuHolder) holder).getMenu();
  }

  private String title;
  private int lines;
  private Map<Integer, MenuItem> items;

  public Menu(String title, int lines) {
    checkNotNull(title, "Titulo não pode ser nulo!");
    checkArgument(lines > 6, "Menu com mais de 6 linhas não são suportaveis.");

    this.title = title;
    this.lines = lines;

    this.items = new WeakHashMap<>();
  }

  public void setItem(int slot, MenuItem menuItem) {
    items.put(slot, menuItem);
  }

  public void removeItem(int slot) {
    items.remove(slot);
  }

  public MenuItem getItem(int slot) {
    return items.get(slot);
  }

  private Inventory createPlayerView(Player player) {
    Inventory inventory = createInventory(new MenuHolder(player.getUniqueId(), this), 9 * lines, title);

    items.forEach((slot, item) -> inventory.setItem(slot, item.getItemStack()));

    return inventory;
  }

  public void open(Player player) {
    player.openInventory(createPlayerView(player));
  }
}
