package io.github.aluria.game.menu.skills;

import io.github.aluria.engine.inventory.Menu;
import io.github.aluria.engine.inventory.MenuItem;
import io.github.aluria.game.player.skill.category.SkillCategory;
import org.bukkit.entity.Player;

public final class SkillCategoryListMenu extends Menu {
  public SkillCategoryListMenu() {
    super("Habiliddades - Categorias.", 3);
  }

  @Override
  public void open(Player player) {

    this.setSkillItem(11, SkillCategory.COMBAT);
    this.setSkillItem(13, SkillCategory.MANUAL_JOB);
    this.setSkillItem(15, SkillCategory.MANUFACTURING);

    super.open(player);
  }

  private void setSkillItem(int slot, SkillCategory category) {
    setItem(slot, MenuItem.of(category.asItemStack(), event -> {
      Menu menu = new SkillCategoryMenu(category);
      menu.open((Player) event.getWhoClicked());
    }));
  }
}
