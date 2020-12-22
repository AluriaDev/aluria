package io.github.aluria.game.menu.skills;

import io.github.aluria.engine.inventory.Menu;
import io.github.aluria.engine.inventory.MenuItem;
import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.player.Gamer;
import io.github.aluria.game.player.skill.category.SkillCategory;
import io.github.aluria.game.player.skill.type.SkillType;
import org.bukkit.entity.Player;

import java.util.List;

import static io.github.aluria.engine.inventory.MenuItem.of;

public final class SkillCategoryMenu extends Menu {

  private final SkillCategory category;

  public SkillCategoryMenu(SkillCategory category) {
    super(category.getName(), 5);

    this.category = category;
  }

  @Override
  public void open(Player player) {
    Gamer gamer = GamePlugin.getInstance().getGamerCache().getGamer(player);
    if (gamer == null) {
      return;
    }

    int firstSlot = 10;

    List<SkillType> types = SkillType.getSkillTypesFromCategory(category);
    for (int i = 0, typesSize = types.size(); i < typesSize; i++) {
      SkillType type = types.get(i);
      this.setItem(firstSlot++, of(type.toItemStack(gamer)));
    }

    super.open(player);
  }
}
