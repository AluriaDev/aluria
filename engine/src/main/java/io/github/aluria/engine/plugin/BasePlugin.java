package io.github.aluria.engine.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.Arrays;

public abstract class BasePlugin extends JavaPlugin {

  protected void registerListeners(Listener... listeners) {
    for (Listener listener : listeners) {
      Bukkit.getPluginManager().registerEvents(listener, this);
    }
  }

  protected void registerCommands(Command... command) {
    ((CraftServer) getServer()).getCommandMap().registerAll(getName().toLowerCase(), Arrays.asList(command));
  }

  @Nullable
  public <T> RegisteredServiceProvider<T> getProviderFor(Class<T> clazz) {
    return getServer().getServicesManager().getRegistration(clazz);
  }
}
