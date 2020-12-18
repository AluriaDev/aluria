package io.github.aluria.kingdoms.enums.role;

import static io.github.aluria.kingdoms.enums.role.RolePermission.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@AllArgsConstructor
public enum Role {

    RECRUIT("Recruta", new RolePermission[0]),

    OFFICER("Capitão", new RolePermission[]{
      INVITE_MEMBER,
      KICK_MEMBER,
      PROMOTE_MEMBER
    }),

    KING("Rei", new RolePermission[]{
      DELETE_KINGDOM
    });

    private final String name;
    private final RolePermission[] permissions;

    public boolean hasPermission(RolePermission permission) {
        for (int i = 0; i <= this.ordinal(); i++) {
            final Role role = values()[i];
            final boolean hasPermission = ArrayUtils.contains(role.getPermissions(), permission);

            if(hasPermission) {
                return true;
            }
        }

        return false;
    }
}
