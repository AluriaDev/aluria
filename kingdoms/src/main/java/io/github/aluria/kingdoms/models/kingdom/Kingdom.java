package io.github.aluria.kingdoms.models.kingdom;

import com.google.common.collect.Maps;
import io.github.aluria.kingdoms.enums.role.Role;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class Kingdom {

    private final UUID id;

    private final String tag;
    private final String name;

    private final Location centerLocation;

    private final Map<String, KingdomMember> members = Maps.newConcurrentMap();
    private final KingdomBroadcaster broadcaster = new KingdomBroadcaster(this);

    private int radius = 10;
    private int level = 1;
    private int maxMembers = 10;
    private int totalReputation = 0;

    public void addMember(KingdomMember member) {
        members.put(member.getPlayerName(), member);
    }

    public KingdomMember getMember(Player player) {
        return members.get(player.getName());
    }

    public KingdomMember getMember(String playerName) {
        return members.get(playerName);
    }

    public Set<KingdomMember> getOnlineMembers() {
        return members.values().stream()
          .filter(KingdomMember::isOnline)
          .collect(Collectors.toSet());
    }

    public boolean canInvite() {
        return members.size() < maxMembers;
    }

    public boolean isInside(Location location) {
        final double minX = centerLocation.getX() - radius;
        final double maxX = centerLocation.getX() + radius;

        final double maxZ = centerLocation.getZ() + radius;
        final double minZ = centerLocation.getZ() - radius;

        final double x = location.getX();
        final double z = location.getZ();

        return x >= minX && x <= maxX && z >= minZ && z <= maxZ;
    }

    public void calculateTotalReputation() {
        totalReputation = 0;

        for (KingdomMember value : members.values()) {
            totalReputation += value.getReputation();
        }
    }
}
