package io.github.aluria.engine.scheduler.executor;

import org.bukkit.plugin.Plugin;

public abstract class AbstractBukkitExecutor implements BukkitExecutor {

  protected final Plugin plugin;

  public AbstractBukkitExecutor(Plugin plugin) {
    this.plugin = plugin;
  }

  public Plugin getPlugin() {
    return plugin;
  }
}
