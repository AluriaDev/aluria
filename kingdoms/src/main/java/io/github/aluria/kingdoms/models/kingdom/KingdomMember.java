package io.github.aluria.kingdoms.models.kingdom;

import io.github.aluria.kingdoms.enums.role.Role;
import io.github.aluria.kingdoms.enums.role.RolePermission;
import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "playerName")
@ToString
public class KingdomMember {

    private final String playerName;
    private final List<RolePermission> permissions = new LinkedList<>();

    private Role role;
    private int reputation;

    public KingdomMember(String playerName) {
        this.playerName = playerName;
    }

    public KingdomMember(String playerName, Role role, int reputation) {
        this.playerName = playerName;
        this.role = role;
        this.reputation = reputation;

        this.calculatePermissions();
    }

    public boolean hasPermission(RolePermission permission) {
        return permissions.contains(permission);
    }

    public void calculatePermissions() {
        permissions.clear();

        if(role != null) {
            permissions.addAll(role.getApplicablePermissions());
        }
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerName);
    }

    public boolean isOnline() {
        return getPlayer() != null;
    }
}
