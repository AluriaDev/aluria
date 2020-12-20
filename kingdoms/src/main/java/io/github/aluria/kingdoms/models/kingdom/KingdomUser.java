package io.github.aluria.kingdoms.models.kingdom;

import io.github.aluria.kingdoms.enums.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "playerName")
public class KingdomUser {

    private final String playerName;
    private final long joinDate;

    private int reputation;

    private UUID kingdomId;
    private Role role;

    public KingdomUser(String playerName, long joinDate, int reputation, UUID kingdomId, Role role) {
        this.playerName = playerName;
        this.joinDate = joinDate;
        this.reputation = reputation;
        this.kingdomId = kingdomId;
        this.role = role;
    }

    public KingdomUser(String playerName) {
        this(playerName, System.currentTimeMillis(), 1, null, null);
    }

    public KingdomUser(Player player) {
        this(player.getName());
    }

    public boolean hasKingdom() {
        return kingdomId != null;
    }
}
