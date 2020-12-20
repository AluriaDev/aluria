package io.github.aluria.kingdoms.enums.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static io.github.aluria.kingdoms.enums.role.RolePermission.*;

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

    public Collection<RolePermission> getApplicablePermissions() {
        List<RolePermission> permissions = new LinkedList<>();

        for (Role role : Role.values()) {
            if(role.ordinal() > ordinal()) break;

            permissions.addAll(Arrays.asList(role.getPermissions()));
        }

        return permissions;
    }

}
