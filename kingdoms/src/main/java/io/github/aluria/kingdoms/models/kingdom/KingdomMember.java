package io.github.aluria.kingdoms.models.kingdom;

import io.github.aluria.kingdoms.enums.role.Role;
import io.github.aluria.kingdoms.enums.role.RolePermission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;

@Data
@EqualsAndHashCode(of = "playerName")
public class KingdomMember {

    public static KingdomMember of(Player player) {
        return new KingdomMember(player.getName());
    }

    public static KingdomMember of(Player player, Role role) {
        final KingdomMember member = new KingdomMember(player.getName());
        member.setRole(role);

        return member;
    }

    public static KingdomMember of(String playerName) {
        return new KingdomMember(playerName);
    }

    public static KingdomMember of(String playerName, Role role) {
        final KingdomMember member = new KingdomMember(playerName);
        member.setRole(role);

        return member;
    }

    private final String playerName;

    private Role role = Role.RECRUIT;

    public boolean hasPermission(RolePermission permission) {
        return role.hasPermission(permission);
    }
}
