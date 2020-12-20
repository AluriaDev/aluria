package io.github.aluria.engine;

import io.github.aluria.engine.inventory.MenuListener;
import io.github.aluria.engine.plugin.BasePlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnginePlugin extends BasePlugin {

  public void onEnable() {
    this.registerListeners(new MenuListener());
  }

  public void onDisable() {

  }

  public static EnginePlugin getInstance() {
    return getPlugin(EnginePlugin.class);
  }
}
