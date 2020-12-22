package io.github.aluria.engine.scoreboard;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class PlayerScoreboardHandler {

  private static final PlayerScoreboardHandler INSTANCE = new PlayerScoreboardHandler();

  public static PlayerScoreboardHandler getInstance() {
    return INSTANCE;
  }

  private final Map<UUID, PlayerScoreboard> scoreboardMap = new ConcurrentHashMap<>();

  public final PlayerScoreboard getPlayerScoreboard(Player player) {
    PlayerScoreboard playerScoreboard = scoreboardMap.get(player.getUniqueId());
    if (playerScoreboard == null) {
      playerScoreboard = new PlayerScoreboard(player);
      scoreboardMap.put(player.getUniqueId(), playerScoreboard);
    }

    return playerScoreboard;
  }

  public final boolean removeScoreboard(Player player) {
    PlayerScoreboard playerScoreboard = this.scoreboardMap.get(player.getUniqueId());
    if (playerScoreboard == null) {
      return false;
    }

    playerScoreboard.delete();
    return scoreboardMap.remove(player.getUniqueId(), playerScoreboard);
  }

  public Map<UUID, PlayerScoreboard> getScoreboardMap() {
    return scoreboardMap;
  }
}
