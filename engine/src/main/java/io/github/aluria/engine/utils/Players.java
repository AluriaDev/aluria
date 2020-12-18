package io.github.aluria.engine.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public class Players {

  public static Player getPlayer(UUID uniqueId) {
    return Bukkit.getPlayer(uniqueId);
  }

  public static Player getPlayer(String playerName) {
    return Bukkit.getPlayer(playerName);
  }

  public static Collection<? extends Player> getOnlinePlayers() {
    return Bukkit.getOnlinePlayers();
  }

  public static OfflinePlayer[] getOfflinePlayer(){
    return Bukkit.getOfflinePlayers();
  }

  public static OfflinePlayer getOfflinePlayer(UUID uniqueId){
    return Bukkit.getOfflinePlayer(uniqueId);
  }

  public static OfflinePlayer getOfflinePlayer(String playerName){
    for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
      if(offlinePlayer != null && offlinePlayer.getName().equals(playerName)){
        return offlinePlayer;
      }
    }


    return null;
  }
}
