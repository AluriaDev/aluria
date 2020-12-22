package io.github.aluria.game.menu.upgrades;

import io.github.aluria.engine.inventory.Menu;
import org.bukkit.entity.Player;

public final class UpgradeListMenu extends Menu {
  public UpgradeListMenu(String title, int lines) {
    super(title, lines);
  }

  @Override
  public void open(Player player) {
    super.open(player);
  }
}
