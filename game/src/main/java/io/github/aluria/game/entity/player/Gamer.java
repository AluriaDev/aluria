package io.github.aluria.game.entity.player;

import io.github.aluria.game.entity.player.status.GamerStatusMap;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public final class Gamer {

  private String playerName;
  private int level;
  private int experience;
  private ItemStack[] inventoryContents;
  private Location lastLocation;
  private GamerStatusMap statusMap;


}
