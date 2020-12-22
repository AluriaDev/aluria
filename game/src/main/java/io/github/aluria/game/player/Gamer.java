package io.github.aluria.game.player;

import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.player.skill.SkillMap;
import io.github.aluria.game.player.status.StatusMap;
import io.github.aluria.game.player.upgrade.UpgradeMap;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

@Data
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Gamer {

  public static Gamer create(String playerName){
    return new Gamer(playerName);
  }

  private final String playerName;

  private int level = 1;
  private int experience = 0;

  private ItemStack[] inventory = new ItemStack[0];
  private Location lastLocation = GamePlugin.FALLBACK_LOCATION;

  private StatusMap statusMap = new StatusMap();
  private UpgradeMap upgradeMap = new UpgradeMap();
  private SkillMap skillMap = new SkillMap();
}
