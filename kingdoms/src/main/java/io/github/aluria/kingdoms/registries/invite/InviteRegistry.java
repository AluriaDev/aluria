package io.github.aluria.kingdoms.registries.invite;

import com.google.common.cache.*;
import io.github.aluria.kingdoms.models.invite.Invite;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class InviteRegistry {

    private final Cache<UUID, Invite> cache;

    public InviteRegistry() {
        this.cache = CacheBuilder.newBuilder()
          .removalListener(removalListener())
          .expireAfterWrite(5, TimeUnit.MINUTES)
          .build();
    }

    private RemovalListener<UUID, Invite> removalListener() {
        return notification -> {
            if (notification.getCause() != RemovalCause.EXPIRED) return;

            final Player inviter = Bukkit.getPlayer(notification.getValue().getInviterName());
            if(inviter == null) return;

            inviter.sendMessage("§aO convite para o jogador " + notification.getValue().getInvitedName() + " expirou.");
        };
    }

    public void put(Invite invite) {
        cache.put(invite.getId(), invite);
    }

    public Invite getById(UUID id) {
        return cache.getIfPresent(id);
    }

    public Invite getByInvitedName(String name, UUID kingdomId) {
        for (Invite invite : getAll()) {
            if(invite.getInvitedName().equals(name) && invite.getKingdomId().equals(kingdomId)) {
                return invite;
            }
        }

        return null;
    }

    public Set<Invite> getByKingdomId(UUID kingdomId) {
        Set<Invite> copy = new HashSet<>();

        for (Invite invite : getAll()) {
            if(invite.getKingdomId().equals(kingdomId)) {
                copy.add(invite);
            }
        }

        return copy;
    }

    public Collection<Invite> getAll() {
        return cache.asMap().values();
    }

    public void remove(UUID id) {
        cache.invalidate(id);
    }
}
