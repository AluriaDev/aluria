package io.github.aluria.game.player;


import io.github.aluria.engine.event.EventSubscription;
import io.github.aluria.engine.scheduler.Scheduler;
import io.github.aluria.game.GamePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class GamerLocalCache {

  private final GamePlugin plugin;
  private final Map<String, Gamer> gamerMap;

  public GamerLocalCache(GamePlugin plugin) {
    this.plugin = plugin;
    this.gamerMap = new ConcurrentHashMap<>();

    EventSubscription.of(PlayerJoinEvent.class)
      .handler(this::handleJoinEvent)
      .listen(plugin);

    EventSubscription.of(PlayerQuitEvent.class)
      .handler(this::handleQuitEvent)
      .listen(plugin);
  }

  public final Gamer getGamer(String playerName) {
    return gamerMap.get(playerName.toLowerCase());
  }

  public final Gamer getGamer(Player player) {
    return getGamer(player.getName());
  }

  public void registerGamer(Gamer gamer) {
    gamerMap.put(gamer.getPlayerName().toLowerCase(), gamer);
  }

  public void clearGamer(Gamer gamer) {
    gamerMap.remove(gamer.getPlayerName().toLowerCase());
  }

  public Map<String, Gamer> getGamerMap() {
    return gamerMap;
  }

  private final Gamer loadOrCreate(String playerName) {
    try {
      Gamer gamer = plugin.getGamerStorage().selectGamer(playerName);
      if (gamer == null) {
        gamer = Gamer.create(playerName);
      }

      return gamer;
    } catch (SQLException e) {
      return Gamer.create(playerName);
    }
  }

  private final void handleJoinEvent(PlayerJoinEvent event) {
    Player player = event.getPlayer();

    Scheduler.async().runTask(() -> {
      Gamer gamer = getGamer(player);
      if (gamer != null) {
        return;
      }

      gamer = loadOrCreate(player.getName());
      registerGamer(gamer);
    });
  }


  private final void handleQuitEvent(PlayerQuitEvent event) {
    Player player = event.getPlayer();

    Scheduler.async().runTask(() -> {
      Gamer gamer = getGamer(player);
      if (gamer == null) {
        return;
      }

      plugin.getGamerStorage().updateGamer(gamer);
    });
  }
}
