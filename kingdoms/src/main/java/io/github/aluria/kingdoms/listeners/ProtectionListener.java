package io.github.aluria.kingdoms.listeners;

import io.github.aluria.kingdoms.KingdomsPlugin;
import io.github.aluria.kingdoms.enums.role.RolePermission;
import io.github.aluria.kingdoms.models.kingdom.Kingdom;
import io.github.aluria.kingdoms.models.kingdom.KingdomMember;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ProtectionListener implements Listener {

    private final KingdomsPlugin plugin;

    public ProtectionListener(KingdomsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        event.setCancelled(cannot(
          player,
          block.getLocation(),
          RolePermission.BREAK_BLOCKS
        ));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();

       event.setCancelled(cannot(
         player,
         block.getLocation(),
         RolePermission.PLACE_BLOCKS
       ));
    }

    private boolean cannot(Player player, Location location, RolePermission permission) {
        final Kingdom kingdom = plugin.getKingdomRegistry().getByArea(location);
        if(kingdom == null) return false;

        final KingdomMember member = kingdom.getMember(player);
        if(member == null) return true;

        return !member.hasPermission(permission);
    }
}
