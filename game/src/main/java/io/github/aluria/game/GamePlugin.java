package io.github.aluria.game;

import io.github.aluria.engine.database.type.sqlite.SQLiteConnectionProvider;
import io.github.aluria.engine.plugin.BasePlugin;
import io.github.aluria.game.player.GamerLocalCache;
import io.github.aluria.game.player.storage.GamerStorage;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Getter
public final class GamePlugin extends BasePlugin {

  // Localização padrão do rpg.
  public static final Location FALLBACK_LOCATION = Bukkit.getWorlds()
    .get(0)
    .getSpawnLocation();


  private GamerStorage gamerStorage;
  private GamerLocalCache gamerCache;

  @Override
  public void onEnable() {
    // test only
    this.gamerStorage = new GamerStorage(new SQLiteConnectionProvider(this, "gamers"));
    this.gamerCache = new GamerLocalCache(this);
  }

  @Override
  public void onDisable() {

  }

  public static GamePlugin getInstance() {
    return getPlugin(GamePlugin.class);
  }
}
