package io.github.aluria.kingdoms.models.kingdom;

import io.github.aluria.kingdoms.enums.role.Role;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class KingdomBroadcaster {

    private final Kingdom kingdom;

    public void broadcast(String... messages) {
        this.broadcast(Role.RECRUIT, messages);
    }

    public void broadcast(Role role, String... messages) {
        for (KingdomMember member : kingdom.getMembers().values()) {
            if(member.getRole().ordinal() < role.ordinal()) continue;

            final Player player = Bukkit.getPlayer(member.getPlayerName());
            if(player == null) continue;

            player.sendMessage(messages);
        }
    }
}
