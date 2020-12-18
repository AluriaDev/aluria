package io.github.aluria.engine;

import io.github.aluria.engine.event.EventAwaiter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class EnginePlugin extends JavaPlugin {

  public void onEnable() {

  }

  public void onDisable() {

  }

  public static EnginePlugin getInstance() {
    return getPlugin(EnginePlugin.class);
  }
}
