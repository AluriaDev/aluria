package io.github.aluria.engine.scheduler.executor.impl;

import io.github.aluria.engine.EnginePlugin;
import io.github.aluria.engine.scheduler.executor.AbstractBukkitExecutor;
import org.bukkit.plugin.Plugin;

public final class AsynchronousBukkitExecutor extends AbstractBukkitExecutor {

  public AsynchronousBukkitExecutor(Plugin plugin) {
    super(plugin);
  }

  public AsynchronousBukkitExecutor() {
    this(EnginePlugin.getInstance());
  }

  @Override
  public void runTaskTimer(Runnable runnable, long delay, long period) {

  }

  @Override
  public void runTaskLater(Runnable runnable, long delay) {

  }

  @Override
  public void runTask(Runnable runnable) {

  }
}
