package io.github.aluria.kingdoms.listeners;

import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import io.github.aluria.kingdoms.models.kingdom.KingdomUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private final KingdomsPlugin plugin;

    public ConnectionListener(KingdomsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getUserRegistry().put(new KingdomUser(event.getPlayer()));
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getUserRegistry().delete(event.getPlayer().getName());
    }
}
