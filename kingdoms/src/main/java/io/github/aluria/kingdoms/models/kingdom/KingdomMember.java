package io.github.aluria.kingdoms.models.kingdom;

import io.github.aluria.kingdoms.enums.role.Role;
import io.github.aluria.kingdoms.enums.role.RolePermission;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "playerName")
public class KingdomMember {

    private final String playerName;

    private UUID kingdomId;
    private Role role;
    private int reputation = 1;

    private final long joinDate = System.currentTimeMillis();

    public boolean hasPermission(RolePermission permission) {
        if(role == null) return false;

        return role.hasPermission(permission);
    }

    public boolean hasKingdom() {
        return kingdomId != null;
    }


}
