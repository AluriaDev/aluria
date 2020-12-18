package io.github.aluria.kingdoms.models.kingdom;

import com.google.common.collect.Maps;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

@Data
public class Kingdom {

    private final UUID id;

    private final String tag;
    private final String name;

    private final Location centerLocation;

    private final Map<String, KingdomMember> members = Maps.newConcurrentMap();

    public void addMember(Player player) {
        members.put(player.getName(), new KingdomMember(player.getName()));
    }

    public void addMember(KingdomMember member) {
        members.put(member.getPlayerName(), member);
    }
}