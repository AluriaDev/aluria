package io.github.aluria.engine.scheduler.executor.impl;

import io.github.aluria.engine.EnginePlugin;
import io.github.aluria.engine.scheduler.executor.AbstractBukkitExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class PrimaryThreadBukkitExecutor extends AbstractBukkitExecutor {

  public PrimaryThreadBukkitExecutor(Plugin plugin) {
    super(plugin);
  }

  public PrimaryThreadBukkitExecutor() {
    this(EnginePlugin.getInstance());
  }

  @Override
  public void runTaskTimer(Runnable runnable, long delay, long period) {
    Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, period);
  }

  @Override
  public void runTaskLater(Runnable runnable, long delay) {
    Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
  }

  @Override
  public void runTask(Runnable runnable) {
    Bukkit.getScheduler().runTask(plugin, runnable);
  }
}
